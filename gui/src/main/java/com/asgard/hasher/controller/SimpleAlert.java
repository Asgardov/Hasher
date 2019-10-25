package com.asgard.hasher.controller;

import javafx.scene.control.Alert;

class SimpleAlert {

    SimpleAlert(String errorText) {
        showSimpleAlertWithText(errorText);
    }

    private void showSimpleAlertWithText(String text){
        String errorHeader = "ERROR";
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(errorHeader);
        errorAlert.setContentText(text);
        errorAlert.showAndWait();

    }

}
