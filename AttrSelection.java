package WekaApi;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.core.converters.ArffSaver;
import java.io.File;
import weka.core.converters.ConverterUtils.DataSource;

public class AttrSelection {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("C:\\Users\\patil\\OneDrive\\Desktop\\check.arff");
        Instances dataset = source.getDataSet();
        AttributeSelection filter = new AttributeSelection();
        CfsSubsetEval eval = new CfsSubsetEval();
        GreedyStepwise search = new GreedyStepwise();
        search.setSearchBackwards(true);
        filter.setEvaluator(eval);
        filter.setSearch(search);
        filter.setInputFormat(dataset);
        Instances newData = Filter.useFilter(dataset,filter);
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("C:\\Users\\patil\\OneDrive\\Desktop\\weka\\attrSel.arff"));
        saver.writeBatch();
    }
}
