package com.asgard.hasher.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    private String[] typesForEncoding = {"eMails", "Phones"};



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Encoder");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    @FXML
    public void initialize() {
        typeOfData.setItems(FXCollections.observableArrayList(typesForEncoding));
    }

    @FXML
    private void encodeData() {
        String dataToEncodeText = dataToEncode.getText();
        if (dataToEncodeText.isEmpty()) {
            System.out.println("empty");
        }
        System.out.println(typeOfData.getSelectionModel().getSelectedItem());



    }


    @FXML
    private void checkValidation() {
        System.out.println(withValidation.isSelected());
    }

    @FXML
    private void copyAll() {


    }


    private TextArea getTextAreaFromSelectedTab() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        return (TextArea) selectedTab.getContent();
    }


}
