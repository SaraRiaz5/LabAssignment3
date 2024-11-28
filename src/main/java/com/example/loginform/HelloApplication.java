package com.example.loginform;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label bannerText = new Label("Welcome to the Login System");
        bannerText.setFont(Font.font("Arial", 20));
        bannerText.setTextFill(Color.WHITE);

        StackPane banner = new StackPane();
        banner.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        banner.setPrefHeight(50);
        banner.getChildren().add(bannerText);


        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.TOP_LEFT);
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20, 20, 20, 20));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");


        formGrid.add(usernameLabel, 0, 0);
            formGrid.add(usernameField, 1, 0);
        formGrid.add(passwordLabel, 0, 1);
        formGrid.add(passwordField, 1, 1);

           HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(loginButton, exitButton);
        formGrid.add(buttonBox, 1, 2);

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.RED); // For error message
           formGrid.add(messageLabel, 1, 3);

        BorderPane layout = new BorderPane();
        layout.setTop(banner);
        layout.setCenter(formGrid);
        BorderPane.setAlignment(banner, Pos.CENTER);
        loginButton.setOnAction(e -> {
             String username = usernameField.getText();
                String password = passwordField.getText();
            if (isValidUsername(username)) {
                showWelcomeScreen(username);
                primaryStage.close();
               } else {

                messageLabel.setText("Invalid Username! Please try again.");
                       usernameField.clear();
                passwordField.clear();
            }
        });

        exitButton.setOnAction(e -> primaryStage.close());
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private boolean isValidUsername(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {

        launch(args);
    }

    public void showWelcomeScreen(String username) {
        Stage welcomeStage = new Stage();
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setFont(Font.font("Arial", 24));
        welcomeLabel.setTextFill(Color.GREEN);

        Scene welcomeScene = new Scene(welcomeLabel, 300, 200);

        welcomeStage.setTitle("Welcome");
        welcomeStage.setScene(welcomeScene);
        welcomeStage.show();
    }
}