module com.weyyuh.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.weyyuh.currencyconverter to javafx.fxml;
    exports com.weyyuh.currencyconverter;
}