import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Cproxy {
	
	public static void main(String[] args) throws AWTException, InterruptedException, UnsupportedFlavorException, IOException
	{
		//openweb();
		//GetProxy();
		getIPS();
		
	}
	
	private static void GetProxy() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException
	{
		Robot robot = new Robot();
		Thread.sleep(5000);
		for (int i = 1; i <= 50; ++i)
		{
			
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_C);
			
			
			Thread.sleep(500);
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable t = cb.getContents(null);
			String readText = (String)t.getTransferData(DataFlavor.stringFlavor);
			//System.out.println(readText);
			String filePath = "F:\\Proxys\\";
			String fileName = "proxy" + Integer.toString(i) + ".txt";
			writeFile(filePath, fileName, readText);
			robot.keyPress(KeyEvent.VK_CONTROL);		
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
		}
	}
	
	private static void openweb() throws InterruptedException, AWTException
	{
		Robot robot = new Robot();
		Thread.sleep(5000);		
				
		String url = "http://www.freeproxylists.net/zh/?page=";
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	
		for(int i = 2; i <= 50; ++i)
		{
			String tUrl = url + Integer.toString(i);
			Transferable t = new StringSelection(tUrl);
			cb.setContents(t,null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);	
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		}
	}
	
	private static void writeFile(String filePath, String fileName, String args) throws IOException
	{
		//boolean result = false;
		File fileRoot = new File(filePath);
		if (!fileRoot.exists())
		{
			fileRoot.mkdir();
		}
		File file = new File(filePath,fileName);
		if (file.exists())
		{
			if (file.isFile())
			{
				file.delete();
			}
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(filePath+fileName);
		fw.write(args);		
		fw.close();
	}
	private static void getIPS() throws IOException
	{
		String result = "";
		String filePath = "F:\\Proxys\\";
		char[] str = new char[100000];
		
		//匹配 xxx.xxx.xxx.xxx yyyy的正则表达式
	
		Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+\\s\\d+");
				
		for(int i =1 ; i <= 34; ++i)
		{
			String fileName = "proxy"+Integer.toString(i)+".txt";
			File file = new File(filePath, fileName);
			FileReader fr = new FileReader(file);
			int j = 0;
			String tempStr;
			while (fr.read(str)!=-1)
			{
				//System.out.println("ttt");
			//	System.out.println(str);
			}
			tempStr = String.valueOf(str);
			Matcher matcher = pattern.matcher(tempStr);
			int step = 0;
			while (matcher.find(step))
			{
				result += matcher.group() + "\r\n";
				step = matcher.end();
			}
			
		}		
		writeFile(filePath, "ProxyIp.txt", result);
		
		
	}
}
