package smkim.homework.exploration;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.StatUtils;

public class DataExploration {
	
	String columnName;
	double[] x;
	double[] y;
	
	public DataExploration(String colname, double[] x, double[] y){
		this.columnName = colname;
		this.x = x;
		this.y = y;
	}
	
	//상관계수
	public double correlation(double[] x, double[] y){
		return new PearsonsCorrelation().correlation(y, x);
	}
	
	//평균값
	public double mean(double[] x){
		return StatUtils.mean(x);
	}
	
	//최대값
	public double max(double[] x){
		return StatUtils.max(x);
	}
	
	//최소값
	public double min(double[] x){
		return StatUtils.min(x);
	}
	
	public String toString(){
		return "ColName : "+columnName+"\tCorrelation coeff : "+Math.round(correlation(x, y)*100d) / 100d+"\tMean : "+Math.round(mean(x)*100d) / 100d+"\tMin : "+Math.round(min(x)*100d) / 100d+"\tMax : "+Math.round(max(x)*100d) / 100d;
	}
	
}
