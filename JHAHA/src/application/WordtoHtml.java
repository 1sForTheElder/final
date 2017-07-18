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
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word  
        try {  
            app.setProperty("Visible", new Variant(false)); // ����wordΪ������  
            Dispatch dispatch = app.getProperty("Documents").toDispatch(); // ��ȡ�ĵ�����ֵ  
            Dispatch doc = Dispatch.invoke(  
                    dispatch,  
                    "Open",  
                    Dispatch.Method,  
                    new Object[] { docfilePath, new Variant(false),  
                            new Variant(true) }, new int[1]).toDispatch(); // ���ܵ���  
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {  
                    htmlfilePath, new Variant(8) }, new int[1]); // ��html��ʽ���浽��ʱ�ļ�  
            Variant f = new Variant(false);  
            Dispatch.call(doc, "Close", f); // ���ĵ��رգ�����������Ϊ������  
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
        	wordToHtml(haha.getPath(), meme.getPath()); // ���ø�ʽת������
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
            { // �ж��ļ��Ƿ����
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// ���ǵ������ʽ
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
                System.out.println("�Ҳ���ָ�����ļ�");
            }
        }
        catch (Exception e)
        {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }

        return list;
    }
}
