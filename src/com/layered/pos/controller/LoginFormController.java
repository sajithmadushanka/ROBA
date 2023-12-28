package com.layered.pos.controller;

import com.layered.pos.util.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public AnchorPane context;
    public TextField textEmail;
    public TextField textPassword;

    public void btnSignInOnAction(ActionEvent actionEvent) throws SQLException , ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/layered_pos", "root", "12345");

            // Use placeholders in the query to prevent SQL injection
            String query = "SELECT * FROM user WHERE email_= ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, textEmail.getText());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                    if(PasswordManager.checkPassword(textPassword.getText(),resultSet.getString("password_"))){
                        System.out.println("completed");
                    }else{
                        new Alert(Alert.AlertType.WARNING, "password does not match!").show();
                    }

            } else {
                new Alert(Alert.AlertType.WARNING, "email not found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
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
