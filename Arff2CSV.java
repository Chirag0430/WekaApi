package WekaApi;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import java.io.File;

import java.io.File;
import java.io.IOException;

public class Arff2CSV {
    public static void main(String[] args) throws IOException {
        ArffLoader loader = new ArffLoader();
        loader.setSource(new File("C:/Users/USER/OneDrive/Desktop/weka/new.arff"));

        Instances data = loader.getDataSet();

        CSVSaver saver = new CSVSaver();
        saver.setInstances(data);
        saver.setFile(new File("C:/Users/USER/OneDrive/Desktop/weka/first.csv"));
        saver.writeBatch();



    }
}
