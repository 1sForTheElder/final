package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TanChuangController1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl;

    @FXML
    private Button btn_ok;
    
    @FXML
    private Button btn_cancel;
    
    public String close_or_not = null;

    @FXML
    void click_btn_ok(ActionEvent event) {
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        // do what you have to do
        
        stage.close();
        this.close_or_not = "Continue";
    }
    
    @FXML 
    void click_btn_cancel(ActionEvent event){
    	Stage stage = (Stage) btn_cancel.getScene().getWindow();
    	stage.close();
    	this.close_or_not = "Close";
    }

    @FXML
    void initialize() {
    	
        assert lbl != null : "fx:id=\"lbl\" was not injected: check your FXML file 'TanChuang.fxml'.";
        assert btn_ok != null : "fx:id=\"btn_ok\" was not injected: check your FXML file 'TanChuang.fxml'.";

    }
    
    public void ErrorInformation(String s){
    	this.lbl.setText(s);
    }
}
