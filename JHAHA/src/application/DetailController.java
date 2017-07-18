package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class DetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView tttt;

    @FXML
    void initialize() {
        assert tttt != null : "fx:id=\"tttt\" was not injected: check your FXML file 'Details.fxml'.";
        
    }
}

