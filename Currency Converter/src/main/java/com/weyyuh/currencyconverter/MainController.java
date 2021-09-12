package com.weyyuh.currencyconverter;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
//import yahoofinance.YahooFinance;
//import yahoofinance.quotes.fx.FxQuote;
//import yahoofinance.quotes.fx.FxSymbols;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane titlePane;

    @FXML
    private ImageView ivClose;

    @FXML
    private ImageView ivMinus;

    @FXML
    private ComboBox<String> originalCurrencyList;

    @FXML
    private TextField inputValue;

    @FXML
    private ImageView ivConvert;

    @FXML
    private ComboBox<String> convertCurrencyList;

    @FXML
    private Line focusLine;

    @FXML
    private Label promptTxtFloat;

    @FXML
    private Label convertValue;

    String originalCurrency = "";
    String convertCurrency = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
//            System.out.println(usdeur);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        titlePane.setOnMousePressed(press -> {
            titlePane.setOnMouseDragged(drag -> {
                Stage stage = Main.stage;
                stage.setX(drag.getScreenX() - press.getSceneX());
                stage.setY(drag.getScreenY() - press.getSceneY());
            });
        });

        originalCurrencyList.getItems().addAll("GBP","USD","EUR","MYR");
        originalCurrencyList.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            originalCurrency = newValue;
        });

        convertCurrencyList.getItems().addAll("GBP", "USD", "EUR", "MYR");
        convertCurrencyList.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            convertCurrency = newValue;
        });
    }

    @FXML
    void clickClose(MouseEvent event) {
        Stage stage = (Stage) ivClose.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    void clickConvert(MouseEvent event) {
        Timeline spin = new Timeline();
        spin.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(ivConvert.rotateProperty(), 0)),
                new KeyFrame(new Duration(800),new KeyValue(ivConvert.rotateProperty(),360))
        );
        spin.play();

        switch (originalCurrency) {
            case "GBP":
                switch (convertCurrency) {
                    case "GBP":
                        convertValue.setText(inputValue.getText());
                        break;
                    case "USD":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 1.38f));
                        break;
                    case "EUR":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 1.17f));
                        break;
                    case "MYR":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 5.74f));
                        break;
                }
                break;
            case "USD":
                switch (convertCurrency) {
                    case "GBP":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 0.72f));
                        break;
                    case "USD":
                        convertValue.setText(inputValue.getText());
                        break;
                    case "EUR":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 0.85f));
                        break;
                    case "MYR":
                        convertValue.setText(String.valueOf(Float.parseFloat(inputValue.getText()) * 4.15f));
                        break;
                }
                break;
            case "EUR":
                switch (convertCurrency) {
                    case "GBP":
                        break;
                    case "USD":
                        break;
                    case "EUR":
                        convertValue.setText(inputValue.getText());
                        break;
                }
                break;
            case "MYR":
                switch (convertCurrency) {
                    case "GBP":
                        break;
                    case "USD":
                        break;
                    case "EUR":
                        break;
                    case "MYR":
                        convertValue.setText(inputValue.getText());
                        break;
                }
                break;
        }

        System.out.println(originalCurrency + " to " + convertCurrency);
    }

    @FXML
    void clickMinus(MouseEvent event) {
        Stage stage = (Stage) ivMinus.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void exitClose(MouseEvent event) {
        File file = new File("src/main/resources/com/weyyuh/currencyconverter/img/close.png");
        Image image = new Image(file.toURI().toString());
        ivClose.setImage(image);
    }

    @FXML
    void exitMinus(MouseEvent event) {
        File file = new File("src/main/resources/com/weyyuh/currencyconverter/img/minus.png");
        Image image = new Image(file.toURI().toString());
        ivMinus.setImage(image);
    }

    @FXML
    void focus(MouseEvent event) {
        focusLine.setOpacity(1);
        promptTxtFloat.setVisible(true);
        inputValue.setPromptText("");
    }

    @FXML
    void hoverClose(MouseEvent event) {
        File file = new File("src/main/resources/com/weyyuh/currencyconverter/img/closeHover.png");
        Image image = new Image(file.toURI().toString());
        ivClose.setImage(image);
    }

    @FXML
    void hoverMinus(MouseEvent event) {
        File file = new File("src/main/resources/com/weyyuh/currencyconverter/img/minusHover.png");
        Image image = new Image(file.toURI().toString());
        ivMinus.setImage(image);
    }


}
