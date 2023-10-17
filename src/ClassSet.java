import java.util.*;
import java.io.*;
import java.util.Scanner;

public class ClassSet {
    List<Double> dataPointsX;
    List<Double> dataPointsY;
    int size;
    GaussianNaiveBayes p;
    DataPoint variance;
    DataPoint average;

    public ClassSet(GaussianNaiveBayes p)
    {
        this.p = p;
        dataPointsX = new ArrayList<Double>();
        dataPointsY = new ArrayList<Double>();
        variance = new DataPoint(0,0);
        average = new DataPoint(0,0);
    }
    public int DataPoints() {
        return size;
    }
    double CalculateAverage(List<Double> dataPoints) {
        double x = 0;
        double average = 0;

        for(int i = 0; i < size; i++)
        {   //System.out.println("x is " + dataPoints.get(i));

            x += dataPoints.get(i);
            //System.out.println("sum is " + x);

        }
    //    System.out.println("size is " + size);

        average = x/size;
        return average;
    }

     void StoreVarianceAndAverage() {
        average.x = CalculateAverage(dataPointsX);
        average.y = CalculateAverage(dataPointsY);
        variance.x = CalculateVariance(dataPointsX);
        variance.y = CalculateVariance(dataPointsY);

    }
    double CalculateVariance(List<Double> dataPoint) {
        double x = 0;
        double variance = 0;
        double avg = CalculateAverage(dataPoint);
        for(int i = 0; i < size; i++)
        {
            x += Math.pow((dataPoint.get(i) - avg), 2);
        }
        variance = x/(size);

        return variance;

    }
    public void AddData(double x, double y) {

        dataPointsX.add(x);
        dataPointsY.add(y);
        size++;

    }
    double ChanceOfDimension(double tryValue, double variance, double average)
    {  double result = 0;

        result = (1/(Math.pow(2*variance*Math.PI,0.5))) * Math.exp(-(Math.pow(tryValue-average,2)/(2*variance)));
        return result;

    }

    DataPoint GetChanceForAllDimensions(DataPoint tryValue) {
        StoreVarianceAndAverage();
        DataPoint point = new DataPoint(ChanceOfDimension(tryValue.x, variance.x, average.x), ChanceOfDimension(tryValue.y, variance.y, average.y));
        return point;
    }


}

