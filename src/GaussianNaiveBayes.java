import java.util.Hashtable;
import java.io.*;

import java.util.Scanner;
import java.util.Enumeration;

public class GaussianNaiveBayes {
    int totalDataPoints = 0;

    public GaussianNaiveBayes()
    {
        classToData = new Hashtable<Integer, ClassSet>();

    }

    Hashtable<Integer, ClassSet> classToData;
     void AddDataToClass(int clss, double x, double y) {
        try
        {
            classToData.get(clss).AddData(x,y);
        }
        catch(NullPointerException e) {

            classToData.put(clss, new ClassSet(this));
            classToData.get(clss).AddData(x,y);

        }
        totalDataPoints ++;
    }
    double ProbabilityOfClass(int clss) {

        return (double)classToData.get(clss).DataPoints()/totalDataPoints;
    }

    DataPoint ProbabilityForClassDimensions(double tryX, double tryY, int clss) {
           ClassSet set = classToData.get(clss);
        DataPoint p = set.GetChanceForAllDimensions(new DataPoint(tryX,tryY));
        return p;

    }
   public void ReadData()
    {
        Scanner scan;
        try {
            scan = new Scanner(new BufferedReader(new FileReader("data.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scan.hasNext()) {
            int clss = scan.nextInt();
            double x = scan.nextDouble();
            double y = scan.nextDouble();
            System.out.println(clss + " " + x + " " + y);
            AddDataToClass(clss, x, y);
        }
        scan = new Scanner(System.in);
        System.out.println("Enter X:");
        double xInput = scan.nextDouble();
        System.out.println("Enter Y:");
        double yInput = scan.nextDouble();
        Enumeration<Integer> keys = classToData.keys();
        while(keys.hasMoreElements()) {
        int classKey = keys.nextElement();
        double chance = 0;
        DataPoint probabilityPoints =  ProbabilityForClassDimensions(xInput, yInput, classKey);
        //System.out.println("probability of class " + classKey +" is "+ ProbabilityOfClass(classKey));
        //System.out.println("conditional prob for x point is " + probabilityPoints.x);
       // System.out.println("conditional prob for y point is " + probabilityPoints.y);
        chance = ProbabilityOfClass(classKey) * probabilityPoints.x * probabilityPoints.y;
        System.out.println("Class " + classKey + " probability: "+  chance);
        }
    }

    public int ReturnTotalDataPoints() {return totalDataPoints;}
}