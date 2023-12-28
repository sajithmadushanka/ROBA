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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupForm {
    public AnchorPane context;
    public  TextField textEmail;
    public TextField textPassword;

    public void btnRegisterNowOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/layered_pos", "root", "12345");

            // Use placeholders in the query to prevent SQL injection
            String query = "INSERT INTO user VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, textEmail.getText());
            statement.setString(2, PasswordManager.encryptedPassword(textPassword.getText(), 12));

            // Use executeUpdate to perform INSERT, UPDATE, or DELETE operations
            if (statement.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }

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
