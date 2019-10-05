package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class Message {

    private Message(String title, String headerText, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.show();
    }

    public static void showError(String title, String headerText, String content) {
        new Message(title, headerText, content, Alert.AlertType.ERROR);
    }

    public static void showInfo(String title, String headerText, String content) {
        new Message(title, headerText, content, Alert.AlertType.INFORMATION);
    }

    public static void setLabelError(Label label, String text) {
        label.setText(text);
        label.setStyle("-fx-text-fill: tomato");
    }

    public static void setLabelInfo(Label label, String text) {
        label.setText(text);
        label.setStyle("-fx-text-fill: blue");
    }

    public static void setLabelSuccess(Label label, String text) {
        label.setText(text);
        label.setStyle("-fx-text-fill: green");
    }
}
