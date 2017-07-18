package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HigherController implements Initializable {
    @FXML TheController theController;
    @FXML DetailController detailController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	theController.init(this);
    	detailController.init(this);
    }
    
    public void write_to_HTML(String s){
    	this.detailController.write_to_Html(s);
    }
}