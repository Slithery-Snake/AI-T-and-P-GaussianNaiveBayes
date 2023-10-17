import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Main {

    static GaussianNaiveBayes monke;
    public static void main(String[] args) {
        monke = new GaussianNaiveBayes();
        monke.ReadData();
    }
}
