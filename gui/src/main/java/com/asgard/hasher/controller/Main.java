package com.asgard.hasher.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.asgard.hasher.logic.Encoder.encodeListToMd5;
import static com.asgard.hasher.logic.Encoder.encodeListToSHA256;
import static com.asgard.hasher.logic.FormatChecker.validateMailsArray;
import static com.asgard.hasher.logic.TextPrepare.resultPrepForOutput;
import static com.asgard.hasher.logic.TextPrepare.stringToArray;

public class Main extends Application {
    @FXML
    private ComboBox<String> typeOfData;
    @FXML
    private CheckBox withValidation;
    @FXML
    private TextArea dataToEncode;
    @FXML
    private TextArea md5TextArea;
    @FXML
    private TextArea sha256TextArea;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab md5Tab;
    @FXML
    private Tab sha256Tab;
    @FXML
    private Button encodeButton;
    @FXML
    private Button copyAllButton;
    @FXML
    private Button saveToCsvButton;
    @FXML
    private Button clearResultsButton;

    private String[] typesForEncoding = {"eMails", "Phones"};


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Encoder");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    @FXML
    public void initialize() {
        typeOfData.setItems(FXCollections.observableArrayList(typesForEncoding));
        typeOfData.getSelectionModel().selectFirst();
    }

    @FXML
    private void encodeData() {
        String dataToEncodeText = dataToEncode.getText();
        if (dataToEncodeText.isEmpty()) {
            new SimpleAlert("NOTHING TO ENCODE");
            return;
        }

        List<String> dataL = stringToArray(dataToEncodeText);
        List<String> readyToEncode = new ArrayList<>();

        if (withValidation.isSelected()) {
            if (typeOfData.getSelectionModel().getSelectedItem().equals("eMails")) {
                readyToEncode = validateMailsArray(dataL);
            }
            if (typeOfData.getSelectionModel().getSelectedItem().equals("Phones")) {
//                TODO  Create validation params
                new SimpleAlert("THERE IS NOTHING YET");
            }
        } else
            readyToEncode = dataL;

        if (!readyToEncode.isEmpty()) {
            md5TextArea.setText(resultPrepForOutput(encodeListToMd5(readyToEncode)));
            sha256TextArea.setText(resultPrepForOutput(encodeListToSHA256(readyToEncode)));
            copyAllButton.disableProperty().set(false);
            saveToCsvButton.disableProperty().set(false);
            clearResultsButton.disableProperty().set(false);


        }
    }

    @FXML
    private void copyAll() {
        StringSelection stringSelection = new StringSelection(getTextAreaFromSelectedTab().getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @FXML
    private void saveToCsv() throws IOException {

        if (!md5TextArea.getText().isEmpty() && !sha256TextArea.getText().isEmpty()) {

            List<String> md5Strings = stringToArray(md5TextArea.getText());
            List<String> sha256Strings = stringToArray(sha256TextArea.getText());
            FileWriter md5CsvFile = createCSVFile(md5Strings);
            saveFile(null, md5CsvFile);

        }

    }

    @FXML
    private void clearResults() {
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab :
                tabs) {
            ((TextArea) ((AnchorPane) tab.getContent()).getChildren().get(0)).setText("");
        }
        copyAllButton.disableProperty().set(true);
        saveToCsvButton.disableProperty().set(true);
        clearResultsButton.disableProperty().set(true);

    }


    private TextArea getTextAreaFromSelectedTab() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        AnchorPane selectedAnchorPane = (AnchorPane) selectedTab.getContent();
        return (TextArea) selectedAnchorPane.getChildren().get(0);
    }

    private FileWriter createCSVFile(List<String> list) {
        FileWriter out = null;
        CSVPrinter printer = null;
        try {
            out = new FileWriter("csv.txt");
            printer = new CSVPrinter(out, CSVFormat.DEFAULT);
            for (String record :
                    list) {
                printer.printRecord(record);
                printer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out ;

    }

    private void saveFile(Stage primaryStage, FileWriter file) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save to CSV");
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.showSaveDialog(null);
    }


}
