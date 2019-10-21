package com.asgard.hasher.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.asgard.hasher.logic.Encoder.encodeListToMd5;
import static com.asgard.hasher.logic.Encoder.encodeListToSHA256;
import static com.asgard.hasher.logic.FormatChecker.validateMailsArray;
import static com.asgard.hasher.logic.Preparator.stringToArray;

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
        List dataL = stringToArray(dataToEncodeText);
        List<String> readyToEncode = new ArrayList();
        if(withValidation.isSelected() && typeOfData.getSelectionModel().getSelectedItem()!=null){
            if(typeOfData.getSelectionModel().getSelectedItem().equals("eMails")) {
                readyToEncode = validateMailsArray(dataL);
            }
            if(typeOfData.getSelectionModel().getSelectedItem().equals("Phones")) {
//                TODO  Create validation params
//                List readyToEncode = validateMailsArray(dataL);
                System.out.println("coming Soon");
            }
        }
        if (!readyToEncode.isEmpty()){
            StringBuilder sBuilder = new StringBuilder();
            for (String encItem :
                    encodeListToMd5(readyToEncode)) {
                sBuilder.append(encItem);
                sBuilder.append("\n");
            }
            md5TextArea.setText(sBuilder.toString());
            sBuilder = new StringBuilder();
            for (String encItem :
                    encodeListToSHA256(readyToEncode)) {
                sBuilder.append(encItem);
                sBuilder.append("\n");
            }
            sha256TextArea.setText(sBuilder.toString());
        }



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
