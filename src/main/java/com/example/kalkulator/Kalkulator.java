package com.example.kalkulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Kalkulator extends Application {

    private Label inputLabel;

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5));


        inputLabel = new Label();
        inputLabel.setPrefHeight(40);
        inputLabel.setStyle("-fx-background-color: #00ff00;");
        gridPane.add(inputLabel, 0, 0, 4, 1);


        gridPane.add(createButton("%"), 0, 1);
        gridPane.add(createButton("/"), 1, 1);
        gridPane.add(createButton("x"), 2, 1);
        Button minusButton = createButton("-");
        gridPane.add(minusButton, 3, 1);


        gridPane.add(createButton("7"), 0, 2);
        gridPane.add(createButton("8"), 1, 2);
        gridPane.add(createButton("9"), 2, 2);


        gridPane.add(createButton("4"), 0, 3);
        gridPane.add(createButton("5"), 1, 3);
        gridPane.add(createButton("6"), 2, 3);


        Button plusButton = createButton("+");
        plusButton.setPrefHeight(60);
        gridPane.add(plusButton, 3, 2, 1, 2);


        gridPane.add(createButton("1"), 0, 4);
        gridPane.add(createButton("2"), 1, 4);
        gridPane.add(createButton("3"), 2, 4);


        Button zeroButton = createButton("0");
        gridPane.add(zeroButton, 0, 5, 2, 1);

        Button decimalButton = createButton(".");
        gridPane.add(decimalButton, 2, 5);


        Button equalsButton = createButton("=");
        equalsButton.setPrefHeight(60);
        equalsButton.setStyle("-fx-background-color: #ff0000;");
        gridPane.add(equalsButton, 3, 4, 1, 2);

        Scene scene = new Scene(gridPane, 300, 400);
        stage.setScene(scene);

        stage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> handleButtonClick(text));


        GridPane.setHgrow(button, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setVgrow(button, javafx.scene.layout.Priority.ALWAYS);

        return button;
    }

    private void handleButtonClick(String value) {
        if (value.equals("=")) {

            String expression = inputLabel.getText();
            try {
                double result = evaluateExpression(expression);
                inputLabel.setText(String.valueOf(result));
            } catch (Exception ex) {
                inputLabel.setText("Greska");
            }
        } else {

            String currentText = inputLabel.getText();
            inputLabel.setText(currentText + value);
        }
    }

    private double evaluateExpression(String expression) {

        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");
        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length-1; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "x":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Nepodrzan operator: " + operator);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
