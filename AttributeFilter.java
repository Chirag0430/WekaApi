package WekaApi;
import weka.core.Instances;
import weka.estimators.IncrementalEstimator;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.core.converters.ArffSaver;
import java.io.File;
import weka.core.converters.ConverterUtils.DataSource;


public class AttributeFilter {
    public static void main(String[] args) throws Exception{

        DataSource source = new DataSource("C:/Users/USER/OneDrive/Desktop/weka/last.arff");
        Instances dataset = source.getDataSet();

        String[] opts = new String[]{"-R", "2"};
        Remove remove = new Remove();
        remove.setOptions(opts);
        remove.setInputFormat(dataset);
        Instances newData = Filter.useFilter(dataset, remove);
        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataset);
        saver.setFile(new File("C:/Users/USER/OneDrive/Desktop/weka/attribute.arff"));
        saver.writeBatch();


    }
}
