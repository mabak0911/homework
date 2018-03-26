package smkim.homework.predict;

public class ModelPrediction {
	double[] beta;
	double[][] x;
	double[] y;
	int col;
	
	public ModelPrediction(double[] beta, double[][] x, double[] y, int col){
	this.beta = beta;
	this.x = x;
	this.y = y;
	this.col = col;
	}
	
	public void predict(){
		double[] predictY = new double[x.length];
		for(int i=0;i<x.length;i++){
			double sumVal = 0.0;
			for(int j=0;j<col;j++){
				sumVal += x[i][j] * beta[j];
			}
			predictY[i] = sumVal;
		}
		System.out.println("테스트 데이터 사이즈 : "+predictY.length);
		for(int i=0;i<predictY.length;i++){
			System.out.println("보스톤 하우스 예측값 : "+predictY[i]+" 실제값 : "+y[i]);
		}
	}
	
}
