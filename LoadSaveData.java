package WekaApi;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;

import weka.core.converters.ConverterUtils.DataSource;
public class LoadSaveData{
    public static void main(String args[]) throws Exception{
        DataSource source = new DataSource("C:/Users/USER/OneDrive/Desktop/weka/new.arff");
        Instances dataset = source.getDataSet();

        System.out.println(dataset.toSummaryString());

        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataset);
        saver.setFile(new File("C:/Users/USER/OneDrive/Desktop/weka/new.arff"));
        saver.writeBatch();
    }
}