package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TheController{
	
	//////////private init/////////////
	
	
    //for select a line of text in editor
    public static final String SELECT_TEXT =
            "(function getSelectionText() {\n" +
            "    var text = \"\";\n" +
            "    if (window.getSelection) {\n" +
            "        text = window.getSelection().toString();\n" +
            "    } else if (document.selection && document.selection.type != \"Control\") {\n" +
            "        text = document.selection.createRange().text;\n" +
            "    }\n" +
            "    if (window.getSelection) {\n" +
            "      if (window.getSelection().empty) {  // Chrome\n" +
            "        window.getSelection().empty();\n" +
            "      } else if (window.getSelection().removeAllRanges) {  // Firefox\n" +
            "        window.getSelection().removeAllRanges();\n" +
            "      }\n" +
            "    } else if (document.selection) {  // IE?\n" +
            "      document.selection.empty();\n" +
            "    }" +
            "    return text;\n" +
            "})()";
    public GridPane back_Up_GridPane = new GridPane();
    public OldValuesRecorder oldValuesRecorder = new OldValuesRecorder();
    public String Language_Identifier = ".java";
    public boolean WhetherShowUploadFileReminder = false;
    public ColorChooser colorPicker = new ColorChooser();
    public String color_Scheme_Mode = "Auto";
    public String[] Report_Extract_String = new String[]{"-0","-1","-link","-top"};
    public String[] Report_Extract_String1 = new String[]{"back.gif","fields.js","forward.gif","logo.gif","result.xml"};
    public int pointer_for_export = 0;
    public Map<String,AnchorPane> Map_Result_Part_Num_to_its_AnchorPane = new HashMap<String,AnchorPane>();
    public Map<String,MenuButton> Map_Result_Part_Num_to_its_MenuButton = new HashMap<String,MenuButton>();
    public CMD_Executor CMD_executor = new CMD_Executor();
    public CMD_Writer CMD_writer = new CMD_Writer();
//    public CountsComputer countsComputer = new CountsComputer();
    public testjiansuo Check_Java = new testjiansuo();
//    public String[] Selected_Files_in_Directory;
    public String Selected_Directory;
    public int total_Number_of_Selected_files = 0;
    public Map<String,GridPaneItem> Map_GridPaneItem = new HashMap<String,GridPaneItem>();
    String LastFilesDirectory = System.getProperty("user.home");;
    String SourceFileSelectedPart = null;
    String[] TitledNames = new String[100];
    String[] Files_in_Directory;
    public Map<String,String> Map_color_titlename = new HashMap<String,String>();
    public Map<String,String> Map_Titlename_Color = new HashMap<String,String>();
    public Map<String,SourceFileTitledPane> Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
    public int number_of_parts=0;
    public HTMLEditor a = new HTMLEditor();
	public File SourceFile;
	public String LastSourceFilePath = System.getProperty("user.home");
	final FileChooser fileChooser = new FileChooser();
	public boolean PartsCanBeCreate = true;
	public int color_Pointer=0;
	SourceFileTitledPane Template;
	List<Color> color_LinkList = new LinkedList<Color>(){{add(Color.RED);add(Color.BLUE);add(Color.GREEN);add(Color.CHOCOLATE);add(Color.VIOLET);
	add(Color.AQUA);add(Color.DARKGOLDENROD);add(Color.MEDIUMPURPLE);
	}};
	List<String> color_Link_Names = new LinkedList<String>(){{
		add("RED");add("BLUE");add("GREEN");add("CHOCOLATE");add("VIOLET");add("AQUA");add("DARKGOLDENROD");add("MEDIUMPURPLE");
	}};

	//////////private init/////////////
	@FXML
	private Button btn_clean_All_Parts;
	
	@FXML
	private Button btn_select_All_Parts;
	
	@FXML
	private Button asda;
	
	@FXML
	void click_asda(ActionEvent event) throws MalformedURLException{

	}
	
	@FXML
	private MenuItem menuItem_Undo;
	
	@FXML
	private MenuItem menuItem_Redo;
	
	@FXML
	private ToolBar ToolBar_For_Radio_language_Button;
	
	@FXML
	private SplitPane Directory_SplitPane;
	
	@FXML
	private Button btn_Generate_Report_Directory;
	
	@FXML
	private Label TaskName_For_ProgressBar;
	
	@FXML
	private Label Done_For_ProgressBar;
	
	@FXML
	private ProgressBar TheProgressBar;
	
	@FXML
	private AnchorPane ColorPickerContainer;
	
	@FXML
	private MenuButton TheColorPicker;
	
	@FXML
	private MenuItem MenuButton_Color_2;
	
	@FXML
	private MenuItem MenuBar_btn_Source_File;
	
	@FXML
	private MenuItem MenuBar_btn_Directory;
	
	@FXML
	private MenuItem MenuButton_Color_1;
	
    @FXML
    private Label lbl_directory;

    @FXML
    public WebView WebView1;
    
    @FXML
    private WebView WebView2;
    
    @FXML
    private Button btn_Directory_ClearAll;

    @FXML
    private Button btn_CreateNewPart;

    @FXML
    private Button btn_UpLoadDirectory;

    @FXML
    private TitledPane AccMain_Pane_Directory_Files;

    @FXML
    private Accordion Accordion_SourceFileParts;

    @FXML
    private Button btn_Directory_SelectAll;

    @FXML
    private TextField txt_Directory_Search;

    @FXML
    private TitledPane AccMain_Pane_Source_File;

//    @FXML
//    private ColorPicker TheColorPicker;

    @FXML
    private Button btn_Directory_Search;

    @FXML
    private GridPane DirectoryGridPane;

    @FXML
    private TitledPane AccMain_Pane_Check_Report;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private Label lbl_SourceFile;

    @FXML
    private Button btn_Addtohighlight;

    @FXML
    private MenuButton Menu_ChooseAPart;

    @FXML
    private Button btn_SourceFileCheck;

    @FXML
    private Accordion Accordion_Main;

    @FXML
    private Button btn_UpLoadSourceFile;

    @FXML
    private Button bnt_TotalCheck;

    @FXML
    private MenuButton MenuButton_Color;

    @FXML
    private Button btn_Directory_Cancel;
    
    @FXML
    private Label lbl_Directory_number_of_files;
    
    @FXML
    private GridPane Report_GridPane;
    
    @FXML
    private AnchorPane AnchorPane_For_reports;
    
    @FXML
    private RadioButton Radio_Java;
    
    @FXML
    private RadioButton Radio_C_Cplus;
    
    @FXML
    private RadioButton Radio_Python;
    
    @FXML
    private Button btn_Clean_Language_Choose;
    
    @FXML
    void Click_menuItem_Undo(ActionEvent event){
    	System.out.println("aaaaa");
    	this.htmlEditor.setHtmlText(this.oldValuesRecorder.getMain_HTMLEditor());
    }
    
    @FXML
    void Click_menuItem_Redo(ActionEvent event){
    	
    }
    
    @FXML
    void Click_btn_clean_All_Parts(ActionEvent event){
    	for(SourceFileTitledPane SFTP:this.Map_SourcePane.values()){
    		SFTP.checkBox.setSelected(false);
    	}
    }
    
    @FXML
    void Click_btn_select_All_Parts(ActionEvent event){
    	for(SourceFileTitledPane SFTP:this.Map_SourcePane.values()){
    		SFTP.checkBox.setSelected(true);
    	}
    }
    
    @FXML 
    void click_btn_Clean_Language_Choose(ActionEvent event){
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	a.setTitle("Reset detecting language");
    	a.setContentText("This will unfreeze the toolbar and give you the"+"\n"+"oppotunity to choose an other language for detection"+"\n"+
    	"Also, the selected source file and parts will be removed"+"\n"+"If you want to continue, please press OK");
    	Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			this.ToolBar_For_Radio_language_Button.setDisable(false);
		} else {
		    // ... user chose CANCEL or closed the dialog
			return;
		}
    }
    
    @FXML
    void click_Radio_Java(ActionEvent event){
    	this.Radio_C_Cplus.setSelected(false);
    	this.Radio_Python.setSelected(false);
    	this.Language_Identifier = ".java";
    }
    
    @FXML
    void click_Radio_C_Cplus(ActionEvent event){
    	this.Radio_Java.setSelected(false);
    	this.Radio_Python.setSelected(false);
    	this.Language_Identifier = ".c";
    }
    
    @FXML 
    void click_Radio_Python(ActionEvent event){
    	this.Radio_C_Cplus.setSelected(false);
    	this.Radio_Java.setSelected(false);
    	this.Language_Identifier = ".py";
    }
    
    @FXML
    void Click_btn_Generate_Report_Directory(ActionEvent event){}
    
    @FXML
    void Click_MenuBar_btn_Source_File(ActionEvent event){
    	try{this.clean_item_files(this.SourceFile.getName());System.out.println(this.SourceFile.getName());}catch(Exception v){System.out.println("no file");}
    	if(this.WhetherShowUploadFileReminder==true){
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Confirmation Dialog");
    		a.setHeaderText("Current source file will be overlapped ");
    		a.setContentText("You are going to upload a new source file \n and this will cause overlapping of current source file,\nIn addition, all selected parts will be deleted and erased. \nDo you want to continue?");
    		Optional<ButtonType> result = a.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    			return;
    		}
//    		a.showAndWait();
    		
    	}
    	configureFileChooser(fileChooser, "Choose a source file", this.LastSourceFilePath);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            SourceFile = file;
            if(file.getName().indexOf(this.Language_Identifier)==-1){
            	Alert a1 = new Alert(AlertType.CONFIRMATION);
            	a1.setTitle("Confirmation Dialog");
            	a1.setHeaderText("The file you selected does not include "+this.Language_Identifier);
            	a1.setContentText("Do you want to change the kind of language or reselect a file?"+"\n"+"If you want to change the kind of language please press YES"+"\n"+"If you want to reselect a file, please choose cancel");
            	Optional<ButtonType> result = a1.showAndWait();
            	if (result.get() == ButtonType.OK){
        		    // ... user chose OK
            		if(file.getName().indexOf(".java")!=-1){
            			this.Language_Identifier = ".java";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Python.setSelected(false);
            			this.Radio_Java.setSelected(true);
            		}
            		if(file.getName().indexOf(".c")!=-1){
            			this.Language_Identifier = ".c";
            			this.Radio_C_Cplus.setSelected(true);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(false);
            		}
            		if(file.getName().indexOf(".py")!=-1){
            			this.Language_Identifier = ".py";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(true);
            		}
        		} else {
        		    // ... user chose CANCEL or closed the dialog
        			return;
        		}
            }
        }else{return;}
        
        
        String TextToInput=null;
        for(String s:this.readTxtFileIntoStringArrList(SourceFile.getPath())){
        	TextToInput+=(s+"\n");
        }
        TextToInput = TextToInput.replaceAll("\n", "<br>");
        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
        TextToInput = TextToInput.replaceAll(" ", "&nbsp");
        this.htmlEditor.setHtmlText(TextToInput);
        this.LastSourceFilePath = SourceFile.getPath().toString().replace(SourceFile.getName().toString(), "");
        this.lbl_SourceFile.setText(SourceFile.getPath());
        this.Accordion_Main.setExpandedPane(AccMain_Pane_Source_File);
        this.PartsCanBeCreate = true;
        this.Accordion_SourceFileParts.getPanes().clear();
        this.Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
        this.number_of_parts = 0;
        this.ToolBar_For_Radio_language_Button.setDisable(true);
    }
    
    @FXML
    void Click_MenuBar_btn_Directory(ActionEvent event) throws Exception{
    	try{this.clean_total_files(this.SourceFile.getName());}catch(Exception e){};
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File ff = dcr.showDialog(null);
    	
    	String Style_Of_File_For_Language = "*?"+this.Language_Identifier;
    	List<File> File_List = org.codehaus.plexus.util.FileUtils.getFiles(ff, null, this.SourceFile.getName());
    	
 
//    	if (ff != null){	
//    		System.out.println(ff.list());
//    	}
//    	int StringPointer = 0;
    	
    	this.Selected_Directory = ff.getPath()+"/";
//        File[] fileArray = ff.listFiles();
//        String[] files_in_Directory = new String[fileArray.length];
        String[] files_in_Directory = new String[File_List.size()];
        System.out.println(Style_Of_File_For_Language);
        int ppt = 0;
        for(int i=0;i<files_in_Directory.length;i++){
        	if(File_List.get(i).getName().indexOf(this.Language_Identifier)!=-1){
        		files_in_Directory[ppt] = File_List.get(ppt).getName();
        		ppt++;
        	} 	
        }
        files_in_Directory = this.removeNullValues(files_in_Directory);
        System.out.println(files_in_Directory.length);
        for(int i = 0;i<files_in_Directory.length;i++){
        	System.out.println(files_in_Directory[i]);
        }
        ///////////////////
//		for(int i=0;i<fileArray.length;i++){
//			if(fileArray[i].getName().equals(this.SourceFile.getName())){
//				continue;
//			}
//			files_in_Directory[StringPointer] = fileArray[i].getName();
//			StringPointer++;
//		}
        
		this.lbl_directory.setText(ff.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
		this.DirectoryGridPane.setGridLinesVisible(true);
		
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);
    }
    
    @FXML
    void Click_btn_Directory_Search(ActionEvent event) {
		for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
			gpi.cleanSearch();
		}
    	if(this.Map_GridPaneItem.isEmpty()==false){
    		for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    			gpi.ToSearch(this.txt_Directory_Search.getText());
//    			label.setStyle("-fx-background-color:GoldenRod");
    	
    		}
    	}
    }
    
    @FXML
    void Click_btn_Directory_Cancel(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
			gpi.cleanSearch();
		}
    }
    
    @FXML
    void Click_btn_Directory_SelectAll(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		gpi.checkBoxTrue();
    	}
    	this.renewLbl_total_number_of_files();
    }

    @FXML
    void Click_btn_Directory_ClearAll(ActionEvent event) {
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		gpi.checkBoxFalse();
    	}
    	this.renewLbl_total_number_of_files();
    }
    
    
    @FXML
    void Click_btn_UpLoadSourceFile(ActionEvent event) throws IOException, InterruptedException {
    	try{this.clean_item_files(this.SourceFile.getName());System.out.println(this.SourceFile.getName());}catch(Exception v){System.out.println("no file");}
    	if(this.WhetherShowUploadFileReminder==true){
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Confirmation Dialog");
    		a.setHeaderText("Current source file will be overlapped ");
    		a.setContentText("You are going to upload a new source file \n and this will cause overlapping of current source file,\nIn addition, all selected parts will be deleted and erased. \nDo you want to continue?");
    		Optional<ButtonType> result = a.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    			return;
    		}
//    		a.showAndWait();
    		
    	}
    	configureFileChooser(fileChooser, "Choose a source file", this.LastSourceFilePath);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            SourceFile = file;
            if(file.getName().indexOf(this.Language_Identifier)==-1){
            	Alert a1 = new Alert(AlertType.CONFIRMATION);
            	a1.setTitle("Confirmation Dialog");
            	a1.setHeaderText("The file you selected does not include "+this.Language_Identifier);
            	a1.setContentText("Do you want to change the kind of language or reselect a file?"+"\n"+"If you want to change the kind of language please press YES"+"\n"+"If you want to reselect a file, please choose cancel");
            	Optional<ButtonType> result = a1.showAndWait();
            	if (result.get() == ButtonType.OK){
        		    // ... user chose OK
            		if(file.getName().indexOf(".java")!=-1){
            			this.Language_Identifier = ".java";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Python.setSelected(false);
            			this.Radio_Java.setSelected(true);
            		}
            		if(file.getName().indexOf(".c")!=-1){
            			this.Language_Identifier = ".c";
            			this.Radio_C_Cplus.setSelected(true);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(false);
            		}
            		if(file.getName().indexOf(".py")!=-1){
            			this.Language_Identifier = ".py";
            			this.Radio_C_Cplus.setSelected(false);
            			this.Radio_Java.setSelected(false);
            			this.Radio_Python.setSelected(true);
            		}
        		} else {
        		    // ... user chose CANCEL or closed the dialog
        			return;
        		}
            }
        }else{return;}
        
        
        String TextToInput=null;
        for(String s:this.readTxtFileIntoStringArrList(SourceFile.getPath())){
        	TextToInput+=(s+"\n");
        }
        TextToInput = TextToInput.replaceAll("\n", "<br>");
        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
        TextToInput = TextToInput.replaceAll(" ", "&nbsp");
        this.htmlEditor.setHtmlText(TextToInput);
        this.LastSourceFilePath = SourceFile.getPath().toString().replace(SourceFile.getName().toString(), "");
        this.lbl_SourceFile.setText(SourceFile.getPath());
        this.Accordion_Main.setExpandedPane(AccMain_Pane_Source_File);
        this.PartsCanBeCreate = true;
        this.Accordion_SourceFileParts.getPanes().clear();
        this.Map_SourcePane = new HashMap<String,SourceFileTitledPane>();
        this.number_of_parts = 0;
        this.ToolBar_For_Radio_language_Button.setDisable(true);
    }

    @FXML
    void Click_btn_UpLoadDirectory(ActionEvent event) throws Exception {
    	try{this.clean_total_files(this.SourceFile.getName());}catch(Exception e){};
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File ff = dcr.showDialog(null);
    	
    	String Style_Of_File_For_Language = "*?"+this.Language_Identifier;
    	List<File> File_List = org.codehaus.plexus.util.FileUtils.getFiles(ff, null, this.SourceFile.getName());
    	
 
//    	if (ff != null){	
//    		System.out.println(ff.list());
//    	}
//    	int StringPointer = 0;
    	
    	this.Selected_Directory = ff.getPath()+"/";
//        File[] fileArray = ff.listFiles();
//        String[] files_in_Directory = new String[fileArray.length];
        String[] files_in_Directory = new String[File_List.size()];
        System.out.println(Style_Of_File_For_Language);
        int ppt = 0;
        for(int i=0;i<files_in_Directory.length;i++){
        	System.out.println(File_List.get(i).getName().substring(File_List.get(i).getName().length()-this.Language_Identifier.length()));
        	if(File_List.get(i).getName().indexOf(this.Language_Identifier)!=-1){
        		files_in_Directory[ppt] = File_List.get(ppt).getName();
        		ppt++;
        	} 	
        }
        System.out.println(this.Language_Identifier);
        files_in_Directory = this.removeNullValues(files_in_Directory);
        System.out.println(files_in_Directory.length);
//        for(int i = 0;i<files_in_Directory.length;i++){
//        	System.out.println(files_in_Directory[i]);
//        }
        ///////////////////
//		for(int i=0;i<fileArray.length;i++){
//			if(fileArray[i].getName().equals(this.SourceFile.getName())){
//				continue;
//			}
//			files_in_Directory[StringPointer] = fileArray[i].getName();
//			StringPointer++;
//		}
        
		this.lbl_directory.setText(ff.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
		this.DirectoryGridPane.setGridLinesVisible(true);
		if(Files_in_Directory.length==0){
			Alert b = new Alert(AlertType.CONFIRMATION);
    		b.setTitle("Confirmation Dialog");
    		b.setHeaderText("No"+this.Language_Identifier+"files in this directory");
    		b.setContentText("Do you want to reselect a directory? if yes, press OK");
    		Optional<ButtonType> result = b.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    this.Click_btn_UpLoadDirectory(event);
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    			return;
    		}
		}
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);
		System.out.println(this.DirectoryGridPane.isGridLinesVisible());
    }

    @FXML
    void Click_lbl_SourceFile(MouseEvent event) {
//    	try{this.clean_item_files(this.SourceFile.getName());System.out.println(this.SourceFile.getName());}catch(Exception v){System.out.println("no file");}
//    	configureFileChooser(fileChooser, "Choose a source file", this.LastSourceFilePath);
//        File file = fileChooser.showOpenDialog(null);
//        if (file != null) {
//            SourceFile = file;
//        }
//        String TextToInput=null;
//        for(String s:this.readTxtFileIntoStringArrList(SourceFile.getPath())){
//        	TextToInput+=(s+"\n");
//        }
//        TextToInput = TextToInput.replaceAll("\n", "<br>");
//        TextToInput = TextToInput.replaceAll("<br><br>", "<br>");
//        TextToInput = TextToInput.replaceAll(" ", "&nbsp");
//        this.htmlEditor.setHtmlText(TextToInput);
//        this.LastSourceFilePath = SourceFile.getPath().toString().replace(SourceFile.getName().toString(), "");
//        this.lbl_SourceFile.setText(SourceFile.getPath());
//        this.Accordion_Main.setExpandedPane(AccMain_Pane_Source_File);
//        this.PartsCanBeCreate = true;
    }
    
    
    ////the same as up_load_directory///
    @FXML
    void Click_LblDirectory(MouseEvent event) {
    	this.clean_total_files(this.SourceFile.getName());
    	File a = new File("src/uploadFiles/");
    	if(a.exists()!=true){
    		a.mkdirs();
    	}
    	DirectoryChooser dcr = new DirectoryChooser();
    	this.configureDirectoryChooser(dcr, "Chooser a directory for files", this.LastFilesDirectory);
    	File ff = dcr.showDialog(null);
    	if (ff != null){
    		System.out.println(ff.list());
    	}
    	int StringPointer = 0;
    	this.Selected_Directory = ff.getPath()+"/";
        File[] fileArray = ff.listFiles();
        String[] files_in_Directory = new String[fileArray.length];
        
        ///////////////////
		for(int i=0;i<fileArray.length;i++){
			if(fileArray[i].getName().equals(this.SourceFile.getName())){
				continue;
			}
			files_in_Directory[StringPointer] = fileArray[i].getName();
			StringPointer++;
		}
		this.lbl_directory.setText(ff.getPath());
		this.Files_in_Directory = files_in_Directory;
		
		this.DirectoryGridPane.getRowConstraints().clear();
		this.DirectoryGridPane.getChildren().clear();
		this.DirectoryGridPane.setGridLinesVisible(true);
		
		for(int i=0;i<this.Files_in_Directory.length;i++){
			try{
				if(this.Files_in_Directory[i].isEmpty()!=true){
					GridPaneItem gpi = new GridPaneItem(this.Files_in_Directory[i]);
					gpi.cb.setOnAction((ActionEvent e) ->
					{this.renewLbl_total_number_of_files();
					});
					this.Map_GridPaneItem.put(this.Files_in_Directory[i], gpi);
				}
			}catch(Exception ee){
				System.out.println();
			}
		}
		int num_Rows = this.Files_in_Directory.length/5+1;
	    for (int i = 0; i < num_Rows; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setMaxHeight(33);
	        row.setMinHeight(33);
	        row.setPrefHeight(33);
	        this.DirectoryGridPane.getRowConstraints().add(row);
	    }
	    int LinshiPointer = 0;
	    this.DirectoryGridPane.setGridLinesVisible(true);
		for(int i=0;i<num_Rows;i++){
			for(int j=0;j<5;j++){
				try{this.DirectoryGridPane.add(this.Map_GridPaneItem.get(this.Files_in_Directory[LinshiPointer]), j, i);}
				catch(Exception e11){break;}
				
				LinshiPointer++;
			}
		}
		this.renewLbl_total_number_of_files();
		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);
    }

    @FXML
    void Click_bnt_TotalCheck(ActionEvent event) throws IOException {
		this.Report_GridPane.getRowConstraints().clear();
		this.Report_GridPane.getChildren().clear();
    	this.Map_Result_Part_Num_to_its_AnchorPane = new HashMap<String,AnchorPane>();
    	this.process_whole_files_when_checking();
    	double progress_part = this.Map_SourcePane.size();
    	progress_part = 1/progress_part;
    	
    	System.out.println(progress_part);
    	int part_flag = 0;
    	for(SourceFileTitledPane tfp:this.Map_SourcePane.values()){
    		if(tfp.checkBox.isSelected()){
    			this.write_Part_file(tfp.Editor.getHtmlText());
    			this.check_One_Source_file_and_Export(tfp.title_name,part_flag);
    			part_flag++;
    			this.pointer_for_export++;
//    			break;
    			this.Done_For_ProgressBar.setText("Processing: "+tfp.title_name);
    		}
    		this.TheProgressBar.setProgress(progress_part);
    		progress_part+=progress_part;
    	}

//		this.renewLbl_total_number_of_files();
//		this.Accordion_Main.setExpandedPane(AccMain_Pane_Directory_Files);

    	int num_of_Row = (int) (Math.floor(this.pointer_for_export/2)+2);
    	System.out.println(num_of_Row);
    	for(int i=1;i<num_of_Row;i++){
    		RowConstraints row = new RowConstraints();
   	        row.setMaxHeight(30);
   	        row.setMinHeight(30);
   	        row.setPrefHeight(30);
   	        this.Report_GridPane.getRowConstraints().add(row);
   	    }
    	System.out.println(this.Report_GridPane.getRowConstraints());
   	    int LinshiPointer = 0;

   		for(int i=1;i<num_of_Row;i++){
   			for(int j=0;j<2;j++){
   				try{this.Report_GridPane.add(this.Map_Result_Part_Num_to_its_AnchorPane.get(LinshiPointer+""), j, i);System.out.println(this.Map_Result_Part_Num_to_its_AnchorPane.get(LinshiPointer+""));}
   				catch(Exception e11){break;}
   				
   				LinshiPointer++;
   			}
    	}
    	
    	this.pointer_for_export = 0;
    	this.Accordion_Main.setExpandedPane(AccMain_Pane_Check_Report);
   	    this.Report_GridPane.setGridLinesVisible(true);
   	    System.out.println("fffflag");
   	    System.out.println(this.Report_GridPane.gridLinesVisibleProperty());
    }

    @FXML
    void Click_btn_Addtohighlight(ActionEvent event) throws IOException {
    	System.out.println(this.Menu_ChooseAPart.getText());
    	if(this.Menu_ChooseAPart.getText().equals("Choose a part")){
    		this.showTanChuang(new Stage(), "Please select a part before highlighting");
    		return;
    	}
//    	try{flag = Integer.parseInt(this.Map_color_titlename.get(this.SourceFileSelectedPart));}catch(Exception v){System.out.println("Error");}
//    	if(flag==100){
//    		
//    	}
    	String Color_Name_For_HTML = null;
    	if(this.color_Scheme_Mode == "Auto"){
    		int colorIndex = Integer.parseInt(this.Map_color_titlename.get(this.SourceFileSelectedPart));
    		Color_Name_For_HTML = this.color_Link_Names.get(colorIndex).toLowerCase();
    	}else{
    		Color_Name_For_HTML = this.Map_Titlename_Color.get(this.SourceFileSelectedPart);
    	}
    	
    	WebView webView = (WebView) this.htmlEditor.lookup("WebView");
    	
    	
        if (webView != null) {
            WebEngine engine = webView.getEngine();
            Object selection = engine.executeScript(SELECT_TEXT);
            if (selection instanceof String && ((String) selection).length() != 0) {
                String add = (String) selection;
                add = this.process_prob_HTML(add);
                String deal = add;
                add = "<font color='"+Color_Name_For_HTML+"'>"+add+"</font>";
                
                String ppp = this.htmlEditor.getHtmlText();
                ppp = ppp.replaceAll("&lt;", "<");
                ppp = ppp.replaceAll("&gt;", ">");
                ppp = ppp.replace(deal,add);
                StringBuffer buf = new StringBuffer(this.Map_SourcePane.get(this.SourceFileSelectedPart).Editor.getHtmlText());
                buf.insert(buf.length()-21,"<br>"+deal);
                String addto = buf.toString();
                this.Map_SourcePane.get(this.SourceFileSelectedPart).Editor.setHtmlText(addto);
                this.htmlEditor.setHtmlText(ppp);
               
            }
        }

    }

    @FXML
    void click_btn_CreateNewPart(ActionEvent event) throws IOException {
    	String color_Name_For_HTML = null;
    	Color color_For_HTML = null;
    	if(this.color_Scheme_Mode == "Auto"){
    		 color_Name_For_HTML = this.color_Link_Names.get(number_of_parts).toLowerCase(); 
    		 color_For_HTML = this.color_LinkList.get(this.number_of_parts);
    	}else{
    		try{
    		color_Name_For_HTML = this.colorPicker.getChosenColorName();
    		color_For_HTML = this.colorPicker.getChosenColor();}
    		catch(Exception e){this.showTanChuang(new Stage(), "Please choose a color");}
    	}
    	try{File fff = new File(this.SourceFile.getPath());}
    	catch(Exception v){this.showTanChuang(new Stage(), "Please upload a source file before creating a part");return;}
    	
    	if(this.PartsCanBeCreate == true){

    		WebView webView = (WebView) this.htmlEditor.lookup("WebView");

            if (webView != null) {
                WebEngine engine = webView.getEngine();
                Object selection = engine.executeScript(SELECT_TEXT);
                if (selection instanceof String && ((String) selection).length() != 0) {
                	
                    String add = (String) selection;
//                	byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
//                    String UTFSpace = new String(bytes,"utf-8");
//                    add = add.replaceAll("\n", "<br>");
//                    add = add.replaceAll("<br><br>", "<br>");
//                    add = add.replaceAll("[?]", "&nbsp");
//                    add = add.replaceAll(" ", "&nbsp");
//                    add = add.replaceAll(UTFSpace, "&nbsp;");
                    add = this.process_prob_HTML(add);
                    String deal = add;
                    String title_Name = "Part"+(this.number_of_parts+1)+": "+color_Name_For_HTML;
                	MenuItem MI = new MenuItem(title_Name);
                	MI.setText(title_Name);
                	MI.setId(title_Name);
                	MI.setOnAction((ActionEvent et) -> {this.SourceFileSelectedPart = title_Name;this.Menu_ChooseAPart.setText(this.SourceFileSelectedPart);});
                	this.Menu_ChooseAPart.getItems().add(MI);
                	
                    add = "<font color=\""+color_Name_For_HTML+"\">"+add+"</font>";
                    
                    String ppp = this.htmlEditor.getHtmlText();

                    ppp = ppp.replaceAll("&lt;", "<");
                    ppp = ppp.replaceAll("&gt;", ">");
                    ppp = ppp.replace(deal,add);
                    
//            		SourceFileTitledPane TP1 = new SourceFileTitledPane(title_Name,this.color_LinkList.get(this.number_of_parts));
                    
            		SourceFileTitledPane TP1 = new SourceFileTitledPane(title_Name,color_For_HTML,color_Name_For_HTML);
            		TP1.btn_delete.setOnAction(evt -> {
            			WebView webView1 = (WebView) TP1.Editor.lookup("WebView");
            			WebEngine engine1 = webView1.getEngine();
                        Object selection1 = engine1.executeScript(SELECT_TEXT);
                        if (selection1 instanceof String && ((String) selection1).length() != 0) {
                        	String selected = (String) selection1;
                        	try {
								selected = this.process_prob_HTML(selected);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	String tempEditor = TP1.Editor.getHtmlText();
                        	String color_HTML_flag = "<font color=\""+TP1.inner_color_name+"\">";
                        	if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+7).equals("</font>")){
                        		if(tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace("<br>"+selected, ""));
                            	}else{
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected, ""));
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((selected+"</font>"),"</font>"+selected));
                        	}
                      
                        	else if(tempEditor.substring(tempEditor.indexOf(selected)-color_HTML_flag.length(),tempEditor.indexOf(selected)).equals(color_HTML_flag)){
                        		if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")){
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected+"<br>", ""));
                            	}else{
                            		TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected, ""));
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((color_HTML_flag+selected),selected+color_HTML_flag));
                        	}
                        	else{
                        		String match_flag1 = "&nbsp;";
                        		
                        		if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")
                        				&&tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){

                        			TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(selected+"<br>", ""));
                            	}else if(tempEditor.substring(tempEditor.indexOf(selected)+selected.length(),tempEditor.indexOf(selected)+selected.length()+4).equals("<br>")
                            			&&!tempEditor.substring(tempEditor.indexOf(selected)-4,tempEditor.indexOf(selected)).equals("<br>")){
                            		int selectedIndex = tempEditor.indexOf(selected);
                            		String tempEditor1 = tempEditor.substring(0,selectedIndex);
                            		int tempIndex = tempEditor1.lastIndexOf("<br>");
                            		String tempEditor1_Left = tempEditor1.substring(tempIndex);
                            		System.out.println(tempEditor1);
                            		System.out.println(tempEditor1_Left);
                            		if(tempEditor1_Left.replaceAll(match_flag1, "").equals("<br>")){
                            			String tempReplace = tempEditor.substring(tempIndex,selectedIndex+selected.length());
                            			TP1.Editor.setHtmlText(TP1.Editor.getHtmlText().replace(tempReplace, ""));
                            			String tempReplace_MainEditor = "</font>"+tempReplace+"<font color=\""+TP1.inner_color_name+"\">";
                            			System.out.println(tempReplace_MainEditor);
                            			System.out.println(this.htmlEditor.getHtmlText());
                            			this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace(tempReplace,tempReplace_MainEditor));
                            			System.out.println(this.htmlEditor.getHtmlText());
                            		}else{
                                		Alert alert4 = new Alert(AlertType.INFORMATION);
                                		alert4.setTitle("Warning");
                                		alert4.setHeaderText("Illegal choice, please choose a complete sentence");
                                		alert4.setContentText("Press OK to rehighlight");
                                		alert4.showAndWait();
                            		};
                            		
                            	}else{
                            		Alert alert5 = new Alert(AlertType.INFORMATION);
                            		alert5.setTitle("Warning");
                            		alert5.setHeaderText("Illegal choice, please choose a complete sentence");
                            		alert5.setContentText("Press OK to rehighlight");
                            		alert5.showAndWait();
                            	}
                            	this.htmlEditor.setHtmlText(this.htmlEditor.getHtmlText().replace((color_HTML_flag+selected),selected+color_HTML_flag));
                        	}
                        	
                        }
//            			this.htmlEditor.setHtmlText(TP1.Editor);
            			
            		});
            		
                	this.Accordion_SourceFileParts.getPanes().add(TP1.innerPane);
                	this.Accordion_SourceFileParts.setExpandedPane(TP1.innerPane);
                    TP1.Editor.setHtmlText(add);
                    this.htmlEditor.setHtmlText(ppp);
                    this.Map_SourcePane.put(title_Name, TP1);
                    this.Map_color_titlename.put(title_Name, this.number_of_parts+"");
                    this.Map_Titlename_Color.put(title_Name, this.colorPicker.getChosenColorName());
                    this.number_of_parts+=1;
                    this.WhetherShowUploadFileReminder = true;
                    this.MenuButton_Color.setDisable(true);
                }
            }       
    	}
//    	Color new_part_color = Color.RED;
//    	String new_part_name = this.number_of_parts+":"+ new_part_color.toString();
//    	TitledPane TP = this.create_titledPane_for_parts(new_part_name);
//    	this.Accordion_SourceFileParts.getPanes().add(TP);
//    	this.Accordion_SourceFileParts.setExpandedPane(TP);
//    	System.out.println(a.getId());
//    	SourceFileTitledPane TP1 = new SourceFileTitledPane(title_Name,this.color_LinkList.get(this.number_of_parts));
//    	this.Accordion_SourceFileParts.getPanes().add(TP1.innerPane);

    }


    @FXML
    void initialize() {
        assert lbl_directory != null : "fx:id=\"lbl_directory\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert btn_CreateNewPart != null : "fx:id=\"btn_CreateNewPart\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert btn_UpLoadDirectory != null : "fx:id=\"btn_UpLoadDirectory\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert AccMain_Pane_Directory_Files != null : "fx:id=\"AccMain_Pane_Directory_Files\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert Accordion_SourceFileParts != null : "fx:id=\"Accordion_SourceFileParts\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert AccMain_Pane_Source_File != null : "fx:id=\"AccMain_Pane_Source_File\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert TheColorPicker != null : "fx:id=\"TheColorPicker\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert AccMain_Pane_Check_Report != null : "fx:id=\"AccMain_Pane_Check_Report\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert htmlEditor != null : "fx:id=\"htmlEditor\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert lbl_SourceFile != null : "fx:id=\"lbl_SourceFile\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert btn_Addtohighlight != null : "fx:id=\"btn_Addtohighlight\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert Menu_ChooseAPart != null : "fx:id=\"Menu_ChooseAPart\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert btn_SourceFileCheck != null : "fx:id=\"btn_SourceFileCheck\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert Accordion_Main != null : "fx:id=\"Accordion_Main\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert btn_UpLoadSourceFile != null : "fx:id=\"btn_UpLoadSourceFile\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert bnt_TotalCheck != null : "fx:id=\"bnt_TotalCheck\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        assert MenuButton_Color != null : "fx:id=\"MenuButton_Color\" was not injected: check your FXML file 'MainPlatform.fxml'.";
        Tooltip tp = new Tooltip("Upload a new source file");
        Tooltip tp1 = new Tooltip("Upload a new file directory");
//        this.bnt_TotalCheck.onActionProperty().bind(this.btn_Generate_Report_Directory.onActionProperty());

        this.btn_Generate_Report_Directory.onActionProperty().bindBidirectional(this.bnt_TotalCheck.onActionProperty());
        this.Radio_Java.setSelected(true);
        this.btn_UpLoadSourceFile.setTooltip(tp);
        this.btn_UpLoadDirectory.setTooltip(tp1);
        this.ColorPickerContainer.getChildren().add(colorPicker);
        this.colorPicker.setOnMouseExited(evt -> {this.change_ColorPicker_Rectangle(this.colorPicker.getChosenColor(), this.colorPicker.getChosenColorName());});
        this.TheColorPicker.getStyleClass().add("column-filter");
        this.TheColorPicker.setText("None");
        this.TheColorPicker.setGraphic(new Rectangle(12,12));
        this.MenuButton_Color.setText("Automatic");
        this.TheColorPicker.setDisable(true);
        this.MenuButton_Color_1.setOnAction(evt -> {this.color_Scheme_Mode = "Auto";this.MenuButton_Color.setText(this.MenuButton_Color_1.getText());this.TheColorPicker.setDisable(true);});
        this.MenuButton_Color_2.setOnAction(evt -> {this.color_Scheme_Mode = "Hand";this.MenuButton_Color.setText(this.MenuButton_Color_2.getText());this.TheColorPicker.setDisable(false);});
        this.deleteBars(htmlEditor);
//        this.Back_Up_GridPane = this.DirectoryGridPane;
//        for(Node node: this.htmlEditor.lookupAll(".tool-bar")){   	    		  
//            node.setManaged(false);
//            node.setDisable(true);
//            node.setVisible(false);       	     	    	  
//        };
        
        /////Test about adding titledPane
//        AnchorPane gp = new AnchorPane();
//        TextArea ta = new TextArea();
//        ta.setLayoutX(14);
//        ta.setLayoutY(14);
//        ta.setPrefSize(200, 200);
//        gp.getChildren().add(ta);
//        TitledPane TP = new TitledPane("haha",gp); 
//        acc_Parts.getPanes().addAll(TP);
        

    }
    
    //private Methods
    
    //for add a part
    public void add_one_part(String Color_of_Part, String Content_of_Part){
    	
    }


    
    private static void configureFileChooser(final FileChooser fileChooser, String setTitle, String lastFilePath){                           
        fileChooser.setTitle(setTitle);
        fileChooser.setInitialDirectory(
            new File(lastFilePath)
        ); 
    }
    
    private static void configureDirectoryChooser( DirectoryChooser fileChooser, String setTitle, String lastFilePath){                           
        fileChooser.setTitle(setTitle);
        fileChooser.setInitialDirectory(
            new File(lastFilePath)
        ); 
    }
    
    ///////////////read a file by line////////////////////////
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                	lineTxt = lineTxt+"\n";
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }
    
    /////to delete the redundant bars/////
    public void deleteBars(HTMLEditor ThehtmlEditor){
        for(Node node: ThehtmlEditor.lookupAll(".tool-bar")){   	    		  
            node.setManaged(false);
            node.setDisable(true);
            node.setVisible(false);       	     	    	  
        };
    }
    
    ////create a new anchor and return////
    public TitledPane create_titledPane_for_parts(String pane_Name){
    	AnchorPane AP = new AnchorPane(); 	
    	HTMLEditor ED = new HTMLEditor();  	
    	ED.setId("html"+this.number_of_parts);
    	this.deleteBars(ED);
    	ED.setPrefSize(200, 250);
    	ED.setLayoutX(10);
    	ED.setLayoutY(20);
    	AP.getChildren().add(ED);
    	TitledPane TP = new TitledPane(pane_Name,AP);

    	return TP;
    }
    
    ///get RGB
    
    public int[] getRGB( Color col) 
    {
        int r = ((int)col.RED.getRed()*255);
        int g = ((int)col.RED.getGreen() * 255);
        int b = ((int)col.RED.getBlue() * 255);
//        int rgb = (r << 16) + (g << 8) + b;
        int[] rgb = new int[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        System.out.println(r);
        System.out.println(g);
        System.out.println(b);
        return rgb;
    }
    
    ///deal with problematic HTML/////
    public String process_prob_HTML(String s) throws UnsupportedEncodingException{
    	byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
        String UTFSpace = new String(bytes,"utf-8");

        s = s.replaceAll("\n", "<br>");
        s = s.replaceAll("<br><br>", "<br>");
        s = s.replaceAll(" ", "&nbsp");
        s = s.replaceAll(UTFSpace, "&nbsp;");
        return s;
    }
    
    ///check whether////
    public String[] checkString(String[] checkSt){
    	int p = 0;
    	for(int i=checkSt.length;i>1;i--){
    		if(p==0){
    			if(checkSt[i].isEmpty()){

    			}
    		}
    	}
    	return checkSt;
    
    }
    
    public void renewLbl_total_number_of_files(){
    	int numsss = 0;
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		if(gpi.cb.isSelected()){
    			numsss++;
    		}
    	}
    	this.lbl_Directory_number_of_files.setText("Number of files included: "+numsss);
    	this.total_Number_of_Selected_files = numsss;
    }
    
    
    ////write part compare file////
    public void write_Part_file(String s) throws IOException{
    	
        s = this.delHTMLTag(s);
        FileOutputStream fos=null;
        OutputStreamWriter osw=null;  
        BufferedWriter bw=null;  
        String path = "src/uploadFiles/"+this.SourceFile.getName();
	    File newFile=new File(path);
	    if(!newFile.exists()){
	        newFile.createNewFile();
	    }
	    fos=new FileOutputStream(newFile);  
	    osw=new OutputStreamWriter(fos);  
	    bw=new BufferedWriter(osw);  
//	    bw.write(ss+"\n");
	    bw.write(s);
	    bw.close();
	    this.Check_Java.running(newFile);
    }
    
    /////do whole files job when checking///////
    public void process_whole_files_when_checking(){
    	
    	for(GridPaneItem gpi:this.Map_GridPaneItem.values()){
    		if(gpi.cb.isSelected()){
    			File source = new File(this.Selected_Directory+gpi.file_Name);
    			File dest = new File("src/uploadFiles/"+gpi.file_Name);
    			try {
    				copyFileUsingJava7Files(source,dest);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    	
    }
    
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        htmlStr = htmlStr.replaceAll("<br>","\n");
        htmlStr = htmlStr.replaceAll("&nbsp;"," ");
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

       return htmlStr.trim(); //返回文本字符串 
    }
    /////copy files////////
	private static void copyFileUsingJava7Files1(File source, File dest)
	        throws IOException {
	        Files.copy(source.toPath(), dest.toPath());
	}
	
	private static void copyFileUsingJava7Files(File source, File dest)
	        throws IOException {    
	    InputStream input = null;    
	    OutputStream output = null;    
	    try {
	           input = new FileInputStream(source);
	           output = new FileOutputStream(dest);        
	           byte[] buf = new byte[1024];        
	           int bytesRead;        
	           while ((bytesRead = input.read(buf)) > 0) {
	               output.write(buf, 0, bytesRead);
	           }
	    } finally {
	        input.close();
	        output.close();
	    }
	}
	
	////check one source file and export results/////////
	public void check_One_Source_file_and_Export(String name,int part_flag) throws IOException{
		CountsComputer countsComputer = new CountsComputer();
		countsComputer.item_part = "item"+part_flag;
		
//		File dir = new File("src/items/item"+this.pointer_for_export+"/");
//		if(dir.exists()!=true){
//			dir.mkdirs();
//		}
		System.out.println(System.getProperty("user.dir")+"/items/item"+part_flag);
		String result_file = System.getProperty("user.dir")+"/items/item"+part_flag;
		this.CMD_writer.writee(result_file);
		this.CMD_executor.cmd();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countsComputer.running(result_file);

		
//		for(File file:countsComputer.Map_index_and_Files.values()){
//			File fff = new File(dir+"/"+file.getName());
//			this.copyFileUsingJava7Files(file, fff);
//			countsComputer.Map_index_and_new_Files.put(key, value)
////			file = new File(fff.getPath());
//		}
		/////////////////////yuanbendfe
		
//		for(String s:countsComputer.Map_index_and_Files.keySet()){
//			File file = countsComputer.Map_index_and_Files.get(s);
//			File fff = new File(dir+"/"+file.getName());
//			this.copyFileUsingJava7Files(file, fff);
////			file = new File(fff.getPath());
//		}
//		for(String s:this.Report_Extract_String){
//			for(File file:countsComputer.Map_index_and_Files.values()){
//				if(file.getName().length() == 11){
//					String file_n = file.getName().substring(0, 6)+s+file.getName().substring(6,11);
//					File newf = new File(file.getPath().replaceAll(file.getName(), "")+"/"+file_n);
//					File fff = new File(dir+"/"+file_n);
//					this.copyFileUsingJava7Files(newf, fff);
//				}else{
//					String file_n = file.getName().substring(0, 7)+s+file.getName().substring(7,12);
//					File newf = new File(file.getPath().replaceAll(file.getName(), "")+"/"+file_n);
//					File fff = new File(dir+"/"+file_n);
//					this.copyFileUsingJava7Files(newf, fff);
//				}
//			}
//		}
//		for(String s:this.Report_Extract_String1){
//			File filee = new File("src/result/"+s);
//			File fff = new File(dir+"/"+s);
//			System.out.println(filee.getPath());
//			System.out.println(fff.getPath());
//			this.copyFileUsingJava7Files(filee, fff);
//		}
		////////////////////yuanbende
		System.out.println("flagg");
		System.out.println((float)(Math.round((float)countsComputer.getNums()/this.total_Number_of_Selected_files*10000))/100);
		Result_AnchorPane_Item RAI = new Result_AnchorPane_Item(countsComputer.getNums(),(float)(Math.round((float)countsComputer.getNums()/this.total_Number_of_Selected_files*10000))/100,name,countsComputer.Map_index_and_Files);
		this.Map_Result_Part_Num_to_its_AnchorPane.put(this.pointer_for_export+"", RAI);
		
//		this.countsComputer.indexFiles=null;
//		this.countsComputer.Map_index_and_Files=new HashMap<String,File>();

		for(String mp:countsComputer.Map_index_and_Files.keySet()){
			Report_Menu_Item RMI = new Report_Menu_Item(mp,countsComputer.Map_index_and_Files.get(mp));
//			this.htmlEditor_2.setAccessibleText(arg0);
//			URL url1 = getClass().getResource(countsComputer.Map_index_and_Files.get(mp).getPath());
			String subpath = "/items/"+countsComputer.item_part+"/"+countsComputer.Map_index_and_Files.get(mp).getName();
			System.out.println(subpath);
//			String sb = subpath.substring(0, 19)+"-0"+subpath.substring(19,24);
//			System.out.println(sb);
//			URL url1 = getClass().getResource(subpath);

//			URL url1 = getClass().getResource(subpath.substring(0, 21)+"-0"+subpath.substring(21,26));
//			URL url2 = getClass().getResource(subpath.substring(0, 21)+"-1"+subpath.substring(21,26));
//			String p = countsComputer.Map_index_and_Files.get(mp).getPath();
			System.out.println("memmeda");
			System.out.println("file:/"+System.getProperty("user.dir")+subpath);
			System.out.println("aaaaaaaaaaaaaaaa");
			
//			URL url1 = new URL(getClass().getResource("../").toString().substring(0,getClass().getResource("../").toString().length()-4)+subpath);
//			System.out.println(getClass().getResource("../").toString().substring(0,getClass().getResource("../").toString().length()-4)+subpath);
			URL url1 = new URL("file:/"+System.getProperty("user.dir")+subpath);
//			RMI.setOnAction(evt -> {System.out.println(url1);this.WebView1.getEngine().load(url1.toExternalForm());
			RMI.setOnAction(evt -> {System.out.println(url1);this.WebView1.getEngine().load(url1.toExternalForm());
			});
			RAI.detail.getItems().add(RMI);
		}
	}
	
	/////read a file and return//////
	public String read_a_file_return_html(String file_path){
		int flag = 0;
		String text = "";
        for(String s:this.readTxtFileIntoStringArrList(file_path)){
        	if(flag==9||flag==10||flag==11||flag==12){
        	System.out.println(flag);
        	text+=s;
        	}
        	flag++;
        }
        System.out.println(text);
        return text;
	}
	/////delete files/////////////
	public void invokedelete(String path){
	    File f=new File(path);
	    if(f.isDirectory()){//如果是目录，先递归删除
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	            invokedelete(path+"//"+list[i]);//先删除目录下的文件
	        }
	    }       
	    f.delete();
	}
	//////clean item file////
	public void clean_item_files(String f){
		File a = new File("src/uploadFiles/"+f);
		System.out.println(a.getPath());
		if(a.exists()){
			this.invokedelete(a.getPath());
		}
		File b = new File(System.getProperty("user.dir")+"/items/");
		if(b.exists()){
			this.invokedelete(b.getPath());
		}
	}
	///////clean total files/////
	public void clean_total_files(String f){
		File a = new File("src/uploadFiles/");
		if(a.exists() && a.getName()!=f){
			this.invokedelete(a.getPath());
		}
		File b = new File(System.getProperty("user.dir")+"/items/");
		if(b.exists()){
			this.invokedelete(b.getPath());
		}
	}
	
	////show TanChuang/////
	public void showTanChuang(Stage primaryStage,String wrongInformation) throws IOException{
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TanChuang.fxml"));
	    Parent root = fxmlLoader.load();
	    
	    //如果使用 Parent root = FXMLLoader.load(...) 静态读取方法，无法获取到Controller的实例对象
	    Scene scene = new Scene(root);
	    TanChuangController controller = fxmlLoader.getController();   //获取Controller的实例对象
	    controller.ErrorInformation(wrongInformation);
	    //传递primaryStage，scene参数给Controller
	    primaryStage.setTitle("Error");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public void showTanChuang1(Stage primaryStage,String wrongInformation) throws IOException, InterruptedException{
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TanChuang1.fxml"));
	    Parent root = fxmlLoader.load();
	    
	    //如果使用 Parent root = FXMLLoader.load(...) 静态读取方法，无法获取到Controller的实例对象
	    Scene scene = new Scene(root);
	    TanChuangController1 controller = fxmlLoader.getController();   //获取Controller的实例对象
	    controller.ErrorInformation(wrongInformation);

	    //传递primaryStage，scene参数给Controller
	    primaryStage.setTitle("Error");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    primaryStage.setOnCloseRequest(evt -> System.out.println("gggg"));

//	    if()
	}
	/////return string[]/////
    public static String[] removeNullValues(String[] firstArray) {

	    List<String> list = new ArrayList<String>();

	    for (String s : firstArray) {
	        if (s != null && s.length() > 0) {
	            list.add(s);
	        }
	    }

	    firstArray = list.toArray(new String[list.size()]);
	    return firstArray;
	}	
    ////////change ColorPicker Rectangle////////////
    public void change_ColorPicker_Rectangle(Color c, String name){
    	this.TheColorPicker.setText(name);
    	Rectangle r = new Rectangle(12,12);
    	r.setFill(c);
    	this.TheColorPicker.setGraphic(r);
    }
}
