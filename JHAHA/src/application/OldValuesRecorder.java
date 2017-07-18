package application;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class OldValuesRecorder{
	public String Main_HTMLEditor = "";
	public Map<String,SourceFileTitledPane> Inner_Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
	public String change_Flag = "";
	
	public String getMain_HTMLEditor() {
		return Main_HTMLEditor;
	}
	public void setMain_HTMLEditor(String main_HTMLEditor) {
		Main_HTMLEditor = main_HTMLEditor;
	}
	public Map<String, SourceFileTitledPane> getInner_Map_SourcePane() {
		return Inner_Map_SourcePane;
	}
	public void setInner_Map_SourcePane(Map<String, SourceFileTitledPane> inner_Map_SourcePane) {
		Inner_Map_SourcePane = inner_Map_SourcePane;
	}
	public String getChange_Flag() {
		return change_Flag;
	}
	public void setChange_Flag(String change_Flag) {
		this.change_Flag = change_Flag;
	}
	OldValuesRecorder(){
		
	}
}
