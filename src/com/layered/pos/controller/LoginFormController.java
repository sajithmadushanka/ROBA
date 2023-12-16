package com.layered.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane context;
    public TextField textEmail;
    public TextField textPassword;

    public void btnSignInOnAction(ActionEvent actionEvent) {

    }

    public void btnCreateAnAccountOnAction(ActionEvent actionEvent)  throws IOException{
        setUi("SignupForm");
    }

    //----------------
    private void setUi(String url) throws IOException{
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml"))));
        stage.centerOnScreen();
    }
}
