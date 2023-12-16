package com.layered.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupForm {
    public AnchorPane context;
    public TextField textEmail;
    public TextField textPassword;

    public void btnRegisterNowOnAction(ActionEvent actionEvent) {
    }

    public void btnAlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException{
        setUi("LoginForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml"))));
        stage.centerOnScreen();
    }
}
