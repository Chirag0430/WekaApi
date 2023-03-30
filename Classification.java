package WekaApi;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
public class Classification {
    public static void main(String[] args) throws Exception{
        DataSource source = new DataSource("C:\\Users\\patil\\OneDrive\\Desktop\\check.arff");
        Instances dataset = source.getDataSet();

        dataset.setClassIndex(dataset.numAttributes()-1);

        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(dataset);

        System.out.println(nb.getCapabilities().toString());
        SMO svm =new SMO();
        svm.buildClassifier(dataset);
        System.out.println(svm.getCapabilities().toString());

        J48 tree = new J48();
        tree.buildClassifier(dataset);
        System.out.println(tree.getCapabilities().toString());
        System.out.println(tree.graph());
    }
}
