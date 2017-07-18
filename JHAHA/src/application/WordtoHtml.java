package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WordtoHtml extends Application {
	
    public void wordToHtml(String docfilePath, String htmlfilePath) {  
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word  
        try {  
            app.setProperty("Visible", new Variant(false)); // 设置word为不可视  
            Dispatch dispatch = app.getProperty("Documents").toDispatch(); // 读取文档属性值  
            Dispatch doc = Dispatch.invoke(  
                    dispatch,  
                    "Open",  
                    Dispatch.Method,  
                    new Object[] { docfilePath, new Variant(false),  
                            new Variant(true) }, new int[1]).toDispatch(); // 功能调用  
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {  
                    htmlfilePath, new Variant(8) }, new int[1]); // 以html格式保存到临时文件  
            Variant f = new Variant(false);  
            Dispatch.call(doc, "Close", f); // 将文档关闭，并将其设置为不可视  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		javafx.stage.Window a = null;
		File mememe = null;
        FileChooser fff = new FileChooser();
        File haha = fff.showOpenDialog(a);
        if(haha!=null){
        	FileChooser ffff= new FileChooser();
        	File meme = ffff.showOpenDialog(a);
        	wordToHtml(haha.getPath(), meme.getPath()); // 调用格式转换方法
        	mememe=meme;
        }
        Thread.sleep(500);
        System.out.println(readTxtFileIntoStringArrList(mememe.getPath()));
        
	}

	public static void main(String[] args) {
		launch(args);
	}
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
}
