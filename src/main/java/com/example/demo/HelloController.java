package com.example.demo;

import com.example.demo.client.Message;
import com.example.demo.client.MqttClient;
import com.example.demo.client.MyMessage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class HelloController extends ClientEventHandler implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea textArea;
    @FXML
    private VBox vBoxMessages;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextFlow textFlowMessages;

    MqttClient client;

    @Override
    public void onMessageReceived(Message message) {
        Platform.runLater(() -> {
           textFlowMessages.getChildren().add(new Text(message.getContentAsString() + '\n'));
        });
    }

    @FXML
    protected void onHelloButtonClick() {
        String message = textArea.getText();
        client.sendMessage(new MyMessage(message.getBytes()));
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new MqttClient();
        client.connect();
        client.subscribe(this);
        vBoxMessages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scroll.setVvalue(newValue.doubleValue());
            }
        });
    }
}