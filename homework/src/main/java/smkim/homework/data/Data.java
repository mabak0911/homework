package smkim.homework.data;

import Jama.Matrix;

public class Data {

	String[] header;
	Matrix dataMatx;
	int rowSize;
	int colSize;
	Matrix trainingDataM;
	Matrix testDataM;
	
	double[][] trX;
	double[] trY;
	double[][] tsX;
	double[] tsY;
	
	public Matrix getDataMatx() {
		return dataMatx;
	}
	public void setDataMatx(Matrix dataMatx) {
		this.dataMatx = dataMatx;
	}
	public Matrix getTrainingDataM() {
		return trainingDataM;
	}
	public void setTrainingDataM(Matrix trainingDataM) {
		this.trainingDataM = trainingDataM;
	}
	public Matrix getTestDataM() {
		return testDataM;
	}
	public void setTestDataM(Matrix testDataM) {
		this.testDataM = testDataM;
	}
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	public int getRowSize() {
		return rowSize;
	}
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	public int getColSize() {
		return colSize;
	}
	public void setColSize(int colSize) {
		this.colSize = colSize;
	}
	
	public double[][] getTrX() {
		return trX;
	}
	
	public void setTrX(double[][] x) {
		this.trX = x;
	}
	
	public double[] getTrY() {
		return trY;
	}
	
	public void setTrY(double[] y) {
		this.trY = y;
	}
	
	public double[][] getTsX() {
		return tsX;
	}
	
	public void setTsX(double[][] tsX) {
		this.tsX = tsX;
	}
	
	public double[] getTsY() {
		return tsY;
	}
	
	public void setTsY(double[] tsY) {
		this.tsY = tsY;
	}
	
}
