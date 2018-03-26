package smkim.homework.model;

import Jama.Matrix;
import Jama.QRDecomposition;


/**
 * @author Robert Sedgewick and Kevin Wayne.
 * @Reference https://introcs.cs.princeton.edu/java/97data/
 * @Date 2018/03/26
 */

public class MultipleLinearRegression {
    private final int N;        // number of 
    private final Matrix beta;  // regression coefficients
    private final int p;        // number of dependent variables
    private double SSE;         // sum of squared
    private double SST;         // sum of squared
    private double mean;
    
    public MultipleLinearRegression(double[][] x, double[] y) {
        if (x.length != y.length){
        	throw new RuntimeException("dimensions don't agree");
        }
        
        N = y.length;
        p = x[0].length;
        Matrix X = new Matrix(x);

        // create matrix from vector
        Matrix Y = new Matrix(y, N);

        // find least squares solution
        QRDecomposition qr = new QRDecomposition(X);
        beta = qr.solve(Y);

        // mean of y[] values
        double sum = 0.0;
        for (int i = 0; i < N; i++){
        	sum += y[i];
        }
        
        mean = sum / N;

        // total variation to be accounted for
        for (int i = 0; i < N; i++) {
            double dev = y[i] - mean;
            SST += dev*dev;
        }

        // variation not accounted for
        Matrix residuals = X.times(beta).minus(Y);
        SSE = residuals.norm2() * residuals.norm2();

    }

    public double beta(int j) {
        return beta.get(j, 0);
    }

    public double R2() {
        return 1.0 - SSE/SST;
    }
    
    public double adjustR2(){
    	return 1.0 - (((1-R2())*(N-1))/(N-beta.getColumnDimension()-1));
    }
    public double mean(){
    	return mean;
    }

    public double[] getBeta(){
    	double[] betaArr = new double[beta.getRowDimension()];
    	for(int i=0;i<beta.getRowDimension();i++){
    		betaArr[i] = beta.get(i, 0);
    	}
    	return betaArr;
    }
    
}
