package WekaApi;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import java.io.File;

import java.io.File;
import java.io.IOException;

public class CSV2ARFF {
    public static void main(String[] args) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("C:/Users/USER/OneDrive/Desktop/weka/first.csv"));

        Instances data = loader.getDataSet();

        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File("C:/Users/USER/OneDrive/Desktop/weka/last.arff"));
        saver.writeBatch();



    }
}
