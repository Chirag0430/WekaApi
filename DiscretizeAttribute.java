package WekaApi;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

import java.io.File;

public class DiscretizeAttribute {
    public static void main(String[] args) throws Exception
    {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("C:\\Users\\patil\\OneDrive\\Desktop\\check.arff");
        Instances dataset = source.getDataSet();
        String[] options = new String[5];
        options[0] = "-B";
        options[1] = "4";
        options[2] = "-R";
        options[3] = "1-3";
        options[4] = "-V";

        Discretize discretize = new Discretize();
        discretize.setOptions(options);
        discretize.setInputFormat(dataset);
        Instances newData = Filter.useFilter(dataset,discretize);

        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("C:\\Users\\patil\\OneDrive\\Desktop\\weka\\DiscretizeAttribute.arff"));
        saver.writeBatch();


    }
}
