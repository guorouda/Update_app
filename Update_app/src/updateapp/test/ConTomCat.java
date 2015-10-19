package updateapp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConTomCat {
	  public void close() throws IOException
	  {
	     Process process = Runtime.getRuntime().exec("cmd /c  E:\\dev\\apache-tomcat-8.0.27\\bin\\shutdown.bat"); // 调用外部程序
	     final InputStream in = process.getInputStream();
	     BufferedReader br=new BufferedReader(new InputStreamReader(in));
	     StringBuilder buf = new StringBuilder();
	     String line = null;
	     while((line = br.readLine()) != null)
	    	 buf.append(line);
	     System.out.println("输出结果为：" + buf);
	     System.out.println();
	  }
	  public void start() throws IOException
	  {
		 Process process = Runtime.getRuntime().exec("cmd /c   E:\\dev\\apache-tomcat-8.0.27\\bin\\startup.bat"); // 调用外部程序
	     final InputStream in = process.getInputStream();
	     BufferedReader br=new BufferedReader(new InputStreamReader(in));
	     StringBuilder buf = new StringBuilder();
	     String line = null;
	     while((line = br.readLine()) != null)
	    	 buf.append(line);
	     System.out.println("输出结果为：" + buf);
	     System.out.println();
	  }
	  public static void main(String[] args) throws Exception{
	   // TODO Auto-generated method stub
	   ConTomCat con=new ConTomCat();
	   con.start();
	  
	  }
}
