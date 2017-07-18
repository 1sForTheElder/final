package application;

import java.awt.Color;
import java.lang.reflect.Array;

import javax.swing.text.Element;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.HTMLEditor;

public class TRYY{

	public static void main(String[] args){
		Color a = Color.YELLOW;
	     GridPane gridpane = new GridPane();
	     for (int i = 0; i < 10; i++) {
	         RowConstraints row = new RowConstraints(50);
	         gridpane.getRowConstraints().add(row);
	     }
		a.getRGB();
		Label l = new Label();

//		System.out.println(getRGB(a));
//		char[] aa;
//		aa= a.toString().toCharArray();
//		String aaa = String.valueOf(aa[2])+String.valueOf(aa[3])+String.valueOf(aa[4])+String.valueOf(aa[5])+String.valueOf(aa[6])+String.valueOf(aa[7]);
//		System.out.println(aaa);
//		int i=Integer.parseInt(aaa,16);
//		String str2=Integer.toBinaryString(i);
//		System.out.println(i);
	}
	
    public static int getRGB( Color col) 
    {
        int r = ((int)col.RED.getRed()*255);
        int g = ((int)col.RED.getGreen() * 255);
        int b = ((int)col.RED.getBlue() * 255);
        int rgb = (r << 16) + (g << 8) + b;
        return rgb;
    }
    
}
