package application;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;

public class Result_AnchorPane_Item extends AnchorPane{
	public Label part_Name = new Label();
	public Label information = new Label();
	public MenuButton detail = new MenuButton("detail");
	public int similarity_counts = 0;
	Map<String,File> Map_name_to_file = new HashMap<String,File>();
	
	public Result_AnchorPane_Item(int similarity_counts, float similarity_prob,String part_name, Map<String,File> map){
		
		this.part_Name.setText(part_name);

		this.information.setText("Similarities: "+similarity_counts+"   Probability: "+similarity_prob+"%");
		this.setLayoutX(118);
		this.setLayoutY(10);
		this.setLayoutX(475);
		this.setLayoutY(11);
		this.information.setPrefHeight(25);
		this.information.setPrefWidth(325);
		this.information.setLayoutX(200);
		this.information.setLayoutY(4);
		this.detail.setLayoutX(510);
		this.detail.setLayoutY(4);
		this.part_Name.setPrefHeight(25);
		this.part_Name.setPrefWidth(150);
		this.part_Name.setLayoutX(50);
		this.part_Name.setLayoutY(4);
		this.getChildren().add(this.part_Name);
		this.getChildren().add(this.information);
		this.getChildren().add(this.detail);

	}
	public void form(){
		
	}
}
