package smkim.homework.run;

import java.io.IOException;

import smkim.homework.common.Utils;
import smkim.homework.data.Data;
import smkim.homework.exploration.DataExploration;
import smkim.homework.model.MultipleLinearRegression;
import smkim.homework.predict.ModelPrediction;

public class BostonHouseEval{
	public static void main(String[] args) throws IOException {
		/*
		 * 데이터 로딩
		 * */
		String[] header = {"CRIM","ZN","INDUS","CHAS","NOX","RM","AGE","DIS","RAD","TAX","PTRATIO","B","LSTAT","MEDV"};
		String url = "https://archive.ics.uci.edu/ml/machine-learning-databases/housing/housing.data";
		Data data = Utils.load(url, header, 0.2, 14);
		/*
		 * Data Split Rate : 0.2
		 * Target Label : MEDV
		 * Total Size : 506
		 * Training Data Size : 404
		 * Test Data Size : 102
		 */
		System.out.println("================================[Data Exploration]================================");
		for(int i=0;i<header.length;i++){
			String colName = header[i];
			double[] x = Utils.getIdxArr(data.getTrainingDataM(), i);
			double[] y = data.getTrY();
			DataExploration de = new DataExploration(colName, x, y);
			System.out.println(i+" " +de.toString());
		}
		System.out.println("\n");
		
		System.out.println("================================[Model Generation]================================");
		
	    double[][] x = data.getTrX();
	    double[] y = data.getTrY();
	    
	    MultipleLinearRegression regression = new MultipleLinearRegression(x, y);
	    
	    System.out.println("Using All features...");
	    System.out.println("Intercept : "+Utils.format(regression.beta(0)));
	    System.out.println("B1 : "+Utils.format(regression.beta(1)));
	    System.out.println("B2 : "+Utils.format(regression.beta(2)));
	    System.out.println("B3 : "+Utils.format(regression.beta(3)));
	    System.out.println("B4 : "+Utils.format(regression.beta(4)));
	    System.out.println("B5 : "+Utils.format(regression.beta(5)));
	    System.out.println("B6 : "+Utils.format(regression.beta(6)));
	    System.out.println("B7 : "+Utils.format(regression.beta(7)));
	    System.out.println("B8 : "+Utils.format(regression.beta(8)));
	    System.out.println("B9 : "+Utils.format(regression.beta(9)));
	    System.out.println("B10 : "+Utils.format(regression.beta(10)));
	    System.out.println("B11 : "+Utils.format(regression.beta(11)));
	    System.out.println("B12 : "+Utils.format(regression.beta(12)));
	    System.out.println("B13 : "+Utils.format(regression.beta(13)));
	    System.out.println("R^2 : "+Utils.format(regression.R2()));
	    System.out.println("adJustR^2 : "+Utils.format(regression.adjustR2()));
	    
	    System.out.println("================================[Model Prediction]================================");
	    ModelPrediction mp = new ModelPrediction(regression.getBeta(),data.getTsX(),data.getTsY(), data.getColSize()-1);
	    mp.predict();
	}
}
