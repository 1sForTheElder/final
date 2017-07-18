package application;

import java.net.MalformedURLException;
import java.net.URL;

public class shishi {
	public static void main(String[] args) throws MalformedURLException{
		 String os = System.getProperty("os.name");
		 System.out.println(os);
		 System.out.println(os.startsWith("Windows"));
	}
}
