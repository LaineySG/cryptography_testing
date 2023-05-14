package com.example;

import javax.crypto.SecretKey;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;
import javafx.scene.input.*;

public class MainWindow extends GridPane {
    // declare variables.
    private Text firstMessage = new Text(200, 350,
            "Welcome. Please enter your key, as well as a message to either encrypt or to be decrypted.");
    private TextField keyField = new TextField();
    private Text keyFieldDesc = new Text("Enter your key here:");
    private TextField clearTextField = new TextField();
    private Text clearTextDesc = new Text("Cleartext (to encrypt):");
    private TextField cipherTextField = new TextField();
    private Text cipherTextDesc = new Text("Ciphertext (to decrypt):");
    private TextArea outputText = new TextArea();
    private String secretKey;

    public MainWindow() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(200, 150);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(firstMessage, 0, 0, 3, 1);
        gridPane.add(keyField, 1, 1);
        gridPane.add(clearTextField, 1, 2);
        gridPane.add(cipherTextField, 1, 3);

        gridPane.add(keyFieldDesc, 0, 1);
        gridPane.add(clearTextDesc, 0, 2);
        gridPane.add(cipherTextDesc, 0, 3);
        gridPane.add(outputText, 0, 4, 3, 1);

        getChildren().addAll(gridPane);

        keyField.textProperty().addListener((observable, oldValue, newValue) -> {
            secretKey = newValue;
        });

        clearTextField.setOnAction(e -> {
            String msg = clearTextField.getText();
            String encryptedmessage = AESApp.Encrypt(msg, secretKey);
            outputText.setText(encryptedmessage);
        });

        cipherTextField.setOnAction(e -> {
            String msg = cipherTextField.getText();
            String decryptedmessage = AESApp.Decrypt(msg, secretKey);
            outputText.setText(decryptedmessage);
        });
    }
}