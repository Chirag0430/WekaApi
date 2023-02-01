package WekaApi;

import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;
public class Sparse {
    public static void main(String[] args) throws Exception{
        DataSource source = new DataSource("C:/Users/USER/OneDrive/Desktop/weka/sparsetest.arff");
        Instances dataset = source.getDataSet();

        NonSparseToSparse sp = new NonSparseToSparse();

        sp.setInputFormat(dataset);
        Instances newData = Filter.useFilter(dataset,sp);
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("C:/Users/USER/OneDrive/Desktop/weka/sparse1.arff"));
        saver.writeBatch();


    }
}
