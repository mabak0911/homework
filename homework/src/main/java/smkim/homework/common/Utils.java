package smkim.homework.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import Jama.Matrix;
import smkim.homework.data.Data;

public class Utils {
	
	/**
	 * 데이터 객체 생성 및 데이터 로딩
	 * @param aturl : 데이터 위치
	 * @param header : 컬럼
	 * @param rate : 데이터 분할 비율
	 * @param target : 목표변수
	 * @return
	 * @throws IOException
	 */
	public static Data load(String aturl, String[] header, double rate, int target) throws IOException{
		Data data = new Data();
		data.setHeader(header);
		URL url = new URL(aturl);
		URLConnection connection = url.openConnection();
		
		InputStream is = connection.getInputStream();
		byte[] buf = new byte[1024];
	    
		StringBuffer sb = new StringBuffer();
		
	    for (int n; (n = is.read(buf)) != -1;) {
	        sb.append(new String(buf, 0, n));
	    }
		
		String[] dataArr = sb.toString().split("\n");
		data.setRowSize(dataArr.length);
		int ncols = header.length;
		data.setColSize(ncols);
		Matrix mData = new Matrix(data.getRowSize(), ncols);
		
		for(int i=0;i<dataArr.length;i++){
			String str = dataArr[i];
			double[] returnVal = getDoubleVal(str, ncols);
			for(int j=0;j<ncols;j++){
				mData.set(i, j, returnVal[j]);
			}
		}
		data.setDataMatx(mData);
		split(data, rate);
		double[][] trX = Utils.getXArr(data.getTrainingDataM(), header.length);
	    data.setTrX(trX);
	    double[] trY = Utils.getYArr(data.getTrainingDataM(), 14);
	    data.setTrY(trY);
	    
	    double[][] tsX = Utils.getXArr(data.getTestDataM(), header.length);
	    data.setTsX(tsX);
	    double[] tsY = Utils.getYArr(data.getTestDataM(), 14);
	    data.setTsY(tsY);
	    
		return data;
	}
	
	/**
	 * 데이터 실수 변형
	 * @param str
	 * @param ncols
	 * @return
	 */
	public static double[] getDoubleVal(String str, int ncols){
		double[] returnVal = new double[ncols];
		int index = 0;
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<str.length();i++){
			char ch = str.charAt(i);
			if(ch == ' '){
				if(sb.length() != 0){
					returnVal[index] = Double.parseDouble(sb.toString());
					index++;
					sb = new StringBuffer();
				}
			}else{
				sb.append(ch);
			}
			
		}
		returnVal[ncols-1] = Double.parseDouble(sb.toString());
		return returnVal;
	}
	
	public static void toString(Matrix m){
		int r = m.getRowDimension();
		int c = m.getColumnDimension();
		System.out.println("Number Of Rows : "+r);
		System.out.println("Number Of Columns : "+c);
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				double val = m.get(i, j);
				System.out.println(i+" "+j+" "+val);
			}
		}
	}
	
	/**
	 * 트레이닝 데이터, 테스트 데이터 분리
	 * @param data
	 * @param rate
	 */
	public static void split(Data data, double rate){
		int orgSize = data.getRowSize();
		int cutRows = (int) (data.getRowSize()*(1-rate));
		Matrix trainingM = new Matrix(cutRows, data.getColSize());
		Matrix testM = new Matrix(orgSize-cutRows, data.getColSize());
		for(int i=0;i<cutRows;i++){
			for(int j=0;j<data.getColSize();j++){
				trainingM.set(i, j, data.getDataMatx().get(i, j));
			}
		}
		
		for(int i=cutRows;i<orgSize;i++){
			for(int j=0;j<data.getColSize();j++){
				testM.set(i-cutRows, j, data.getDataMatx().get(i, j));
			}
		}
		
		data.setTrainingDataM(trainingM);
		data.setTestDataM(testM);
	}
	
	/**
	 * X 데이터 반환
	 * @param m 독립변수
	 * @param cols 컬럼
	 * @return
	 */
	public static double[][] getXArr(Matrix m, int cols){
		double[][] xArr = new double[m.getRowDimension()][cols];
		for(int i=0;i<m.getRowDimension();i++){
			xArr[i][0] = 1.0;
			for(int j=1;j<cols;j++){
				xArr[i][j] = m.get(i, j-1);
			}
		}
		return xArr;
	}
	
	/**
	 * Y 데이터 반환
	 * @param m
	 * @param target
	 * @return
	 */
	public static double[] getYArr(Matrix m, int target){
		double[] yArr = new double[m.getRowDimension()];
		for(int i=0;i<m.getRowDimension();i++){
			yArr[i] = m.get(i, target-1); 
		}
		return yArr;
	}
	
	
	/**
	 * 인덱스 지정열 반환
	 * @param m
	 * @param idx
	 * @return
	 */
	public static double[] getIdxArr(Matrix m, int idx){
		double[] idxArr = new double[m.getRowDimension()];
		for(int i=0;i<m.getRowDimension();i++){
			idxArr[i] = m.get(i, idx); 
		}
		return idxArr;
	}
	
	/**
	 * 실수형 데이터 소수점이하 4자리 반환
	 * @param d
	 * @return
	 */
	public static double format(double d){
		return Math.round(d*10000d)/10000d;
	}
}
