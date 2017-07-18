package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.MenuItem;

public class Report_Menu_Item extends MenuItem {
	File file;
	String name;
	public Report_Menu_Item(String name,File file){
		this.file = file;
		this.name = name;
		this.setText(name);
		this.setOnAction(evt -> {});
	}
}
