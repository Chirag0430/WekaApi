import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import weka.clusterers.ClusterEvaluation;
import weka.core.*;
import weka.core.converters.*;
import weka.core.pmml.jaxbbindings.Application;
import weka.filters.*;
import weka.filters.unsupervised.attribute.Remove;
import weka.clusterers.SimpleKMeans;
import weka.gui.explorer.ClustererPanel;

public class Clustering_Application extends JFrame implements ActionListener {
    private JFileChooser fileChooser;
    private File inputFile;
    private File outputFile;
    private JButton btnLoadARFF;
    private JButton btnSaveARFF;
    private JButton btnLoadCSV;
    private JButton btnSaveCSV;
    private JButton btnFilterAttributes;
    private JButton btnClusterData;
    private JTextArea txtData;

    public Clustering_Application() {
        setTitle("Clustering Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // create file chooser
        fileChooser = new JFileChooser();

        // create buttons
        btnLoadARFF = new JButton("Load ARFF");
        btnSaveARFF = new JButton("Save ARFF");
        btnLoadCSV = new JButton("Load CSV");
        btnSaveCSV = new JButton("Save CSV");
        btnFilterAttributes = new JButton("Filter Attributes");
        btnClusterData = new JButton("Cluster Data");

        // add action listeners
        btnLoadARFF.addActionListener(this);
        btnSaveARFF.addActionListener(this);
        btnLoadCSV.addActionListener(this);
        btnSaveCSV.addActionListener(this);
        btnFilterAttributes.addActionListener(this);
        btnClusterData.addActionListener(this);

        // create text area
        txtData = new JTextArea();
        txtData.setEditable(false);

        // create scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(txtData);

        // create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));
        buttonPanel.add(btnLoadARFF);
        buttonPanel.add(btnSaveARFF);
        buttonPanel.add(btnLoadCSV);
        buttonPanel.add(btnSaveCSV);
        buttonPanel.add(btnFilterAttributes);
        buttonPanel.add(btnClusterData);

        // add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadARFF) {
            // load ARFF file
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    Instances data = new Instances(reader);
                    reader.close();
                    txtData.setText(data.toString());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Error: File not found.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error loading ARFF file: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == btnSaveARFF) {
            // save ARFF file
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                outputFile = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                    writer.write(txtData.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(this, "ARFF file saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving ARFF file: " + ex.getMessage());
                }
            }
        }
        else if (e.getSource() == btnLoadCSV) {
            // load CSV file
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile();
                try {
                    CSVLoader loader = new CSVLoader();
                    loader.setSource(inputFile);
                    Instances data = loader.getDataSet();
                    txtData.setText(data.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error loading CSV file: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == btnSaveCSV) {
            // save CSV file
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                outputFile = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                    writer.write(txtData.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(this, "CSV file saved successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving CSV file: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == btnFilterAttributes) {
            // filter attributes
            try {
                Instances data = getInstancesFromTextArea();

                // apply Remove filter to remove attributes 1 to 3
                Remove remove = new Remove();
                remove.setAttributeIndices("1-3");
                remove.setInputFormat(data);
                Instances filteredData = Filter.useFilter(data, remove);

                // display filter output
                txtData.setText("Filtered data:\n\n");
                txtData.append(filteredData.toString());

                // display filter summary
                String[] options = remove.getOptions();
                txtData.append("\n\nFilter summary:\n\n");
                txtData.append("Filter type: " + remove.getClass().getSimpleName() + "\n");
                txtData.append("Attribute indices to remove: " + options[1] + "\n");
                txtData.append("Number of attributes removed: " + filteredData.numAttributes() + "\n");
                txtData.append("Number of instances remaining: " + filteredData.numInstances() + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error filtering attributes: " + ex.getMessage());
            }
        }
        else if (e.getSource() == btnClusterData) {
            // cluster data
            try {
                SimpleKMeans kMeans = new SimpleKMeans();
                kMeans.setNumClusters(3);
                kMeans.buildClusterer(getInstancesFromTextArea());

                // evaluate the clusterer and display results
                ClusterEvaluation eval = new ClusterEvaluation();
                eval.setClusterer(kMeans);
                eval.evaluateClusterer(getInstancesFromTextArea());
                txtData.setText(eval.clusterResultsToString());

                revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error clustering data: " + ex.getMessage());
            }
        }

    }


    private Instances getInstancesFromTextArea() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(txtData.getText()));
        Instances data = new Instances(reader);
        reader.close();
        return data;
    }

    public static void main(String[] args) {
        new Clustering_Application();
    }
}
