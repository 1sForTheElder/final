package application;
	
import java.io.File;
import java.net.URL;

import javax.swing.JTextPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			this.clean_total_files();

//			System.out.println(getClass().getResource("items/item0/match3-0.html"));
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Parent root = FXMLLoader.load(getClass()
                    .getResource("/application/tes.fxml"));
			Button btn = new Button();
			btn.setText("aaaaaa");
			btn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event){
					System.out.println("hello");
				}
			});
			Scene t = new Scene(root);
			primaryStage.setScene(t);
			primaryStage.resizableProperty().set(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	///////clean total files/////
	public void clean_total_files(){
		File a = new File("src/uploadFiles/");
		if(a.exists()){
			this.invokedelete(a.getPath());
		}
		File b = new File(System.getProperty("user.dir")+"/items/");
		if(b.exists()){
			this.invokedelete(b.getPath());
		}
	}
	
	public void invokedelete(String path){
	    File f=new File(path);
	    if(f.isDirectory()){//如果是目录，先递归删除
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	            invokedelete(path+"//"+list[i]);//先删除目录下的文件
	        }
	    }
	    if(f.getPath()!=path){
	    	f.delete();
	    }
	}

}
