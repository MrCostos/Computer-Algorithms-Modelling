/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author Andrew
 */
public class trapezoidalXValue {
    public double upperLimit;
    public double lowerLimit;
    public int noOfStrips;
    
    public trapezoidalXValue (double u, double l, int n){
        upperLimit =u;
        lowerLimit = l;
        noOfStrips = n;
    }
    
    public double getDeltaX(){
        return ((upperLimit - lowerLimit)/noOfStrips);
    }
    
    public double lowerLimitValue(){
        return lowerLimit;
    }
    
    public double upperLimitValue(){
        return upperLimit;
    }
    
    public void fOfx(){
        double[] inBetweenXValues = new double[noOfStrips];
        inBetweenXValues[0] = lowerLimitValue();
        inBetweenXValues[noOfStrips] = upperLimitValue();
        double newXvalue = lowerLimitValue();
        
        for(int i = 0; i <= noOfStrips; i++){
            inBetweenXValues[i] = newXvalue;
            newXvalue = newXvalue + getDeltaX();
        }
        
    }
}
