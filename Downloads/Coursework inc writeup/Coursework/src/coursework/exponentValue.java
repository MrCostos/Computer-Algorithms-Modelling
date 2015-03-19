/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author Andrew
 */

public class exponentValue {
private double xValue;
private double meanValue;
private double sdValue;

public exponentValue(double x, double m, double s){
    xValue = x;
    meanValue = m;
    sdValue = s;
}

private double firstCalc(){ //Calculates the numerator
    double returnValue;
    returnValue = xValue - meanValue;
    returnValue = returnValue * returnValue;
    return returnValue;
}

private double secondCalc(){ // Calculates the denominator
    double calc = sdValue;
    calc = (calc * calc)*2;
    return calc;
}

public double getCalculation(){ // Value of the exponent
    return (firstCalc()/secondCalc());
}


}
