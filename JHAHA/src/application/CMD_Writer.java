package application;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CMD_Writer {
	static String test_file_path = "";
	public static String getTest_file_path() {
		return test_file_path;
	}
	public static void setTest_file_path(String test_file_path) {
		CMD_Writer.test_file_path = test_file_path;
	}
//	public static void main(String args[]){
//		writee();
//	}
	public static void writee(String result_file){
		setTest_file_path("src/uploadFiles/");
		String pathh = "running.bat";
	    FileOutputStream fos=null;  
	    OutputStreamWriter osw=null;  
	    BufferedWriter bw=null;  
		try {
			//Ð´ÐÂÎÄ¼þ  
		    File newFile=new File(pathh);
		    if(!newFile.exists()){  
		        newFile.createNewFile();
		    }  
		    fos=new FileOutputStream(newFile);  
		    osw=new OutputStreamWriter(fos);  
		    bw=new BufferedWriter(osw);  
//		    String ss="java -jar src/detect/jplag-2.11.8-SNAPSHOT-jar-with-dependencies.jar -l java17 -r src/test/result src/test"; 
		    String ss="java -jar src/detect/jplag-2.11.8-SNAPSHOT-jar-with-dependencies.jar -l java17 -r "+result_file+" "+test_file_path;   
		    bw.write(ss+"\n");
		    bw.write("exit");
		    bw.flush();  
	//	    }
	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
