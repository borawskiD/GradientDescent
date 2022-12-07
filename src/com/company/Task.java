package com.company;

public class Task {
    double k = 2;
    private final double ALPHA = 0.8;
    private final double EPSILON = 0.001;
    private double [] previousPoint = {1,1,1};
    boolean rangeExceeded = false;
    private double [] currentPoint = {1,1,1};
    private double currentF = f(currentPoint[0], currentPoint[1], currentPoint[2]);
    public void run(){
        do{
            double DevX1 = x1(currentPoint[0], currentPoint[1], currentPoint[2]);
            double devX2 = x2(currentPoint[0], currentPoint[1], currentPoint[2]);
            double devX3 = x3(currentPoint[0], currentPoint[1], currentPoint[2]);
            double[]vector = {-DevX1, -devX2, -devX3};
            System.out.println("\nAktualny punkt [" + currentPoint[0] + ", " +  currentPoint[1] + ", " +  currentPoint[2] + "] f(x) = " + f(currentPoint[0],  currentPoint[1],  currentPoint[2]));
            System.out.println("Wektor jest rowny: [" + vector[0] + ", " + vector[1] + ", " + vector[2] + "]");

            double x1 = currentPoint[0] + k * vector[0];
            double x2 = currentPoint[1] + k * vector[1];
            double x3 = currentPoint[2] + k * vector[2];

            System.out.println("Nowy punkt [" + x1 + ", " + x2 + ", " + x3 + "] f(x) = " + f(x1,x2,x3));
            if (f(x1,x2,x3) > currentF){
                System.out.println("Poprzedni punkt byl lepszy, zmieniam krok");
                k = ALPHA * k;
            }else{
                previousPoint = currentPoint;
                currentPoint = new double[]{x1,x2,x3};
                currentF = f(x1,x2,x3);
            }
            rangeExceeded = isNan(x1,x2,x3);
        }while(k > EPSILON && rangeExceeded);
    }
    public boolean isNan(double x1, double x2, double x3){
        return !Double.isNaN(x1) && !Double.isNaN(x2) && !Double.isNaN(x3);
    }
    private double x1(double x1, double x2, double x3){
        return 3 * Math.pow(x1,2) - 6 * x1 * Math.pow(x2,2) - 8 * x1 * Math.pow(x3,3);
    }
    private double x2(double x1, double x2, double x3){
        return -6 * Math.pow(x1,2) * x2 + 9 * Math.pow(x2,2) * x3;
    }
    private double x3(double x1, double x2, double x3){
        return 3 * Math.pow(x2,3) - 12 * Math.pow(x1,2) * Math.pow(x3,2);
    }
    private double f(double x1, double x2, double x3){
        return Math.pow(x1,3) - 3 * Math.pow(x1,2) * Math.pow(x2,2) + 3 * Math.pow(x2,3) * x3 - 4 * Math.pow(x1,2) * Math.pow(x3,3);
    }
}
