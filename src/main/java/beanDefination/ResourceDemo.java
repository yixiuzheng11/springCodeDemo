package beanDefination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ResourceDemo {
	 /** 
	    * ClassPathResource可以用来获取类路径下的资源 
	    * @throws IOException 
	    */  
	   @Test  
	   public void testClassPath() throws IOException {  
	      Resource resource = new ClassPathResource("/spring/test.xml");  
	      String fileName = resource.getFilename();  
	      System.out.println("fileName----"+fileName);  
	      System.out.println("file---"+resource.getFile());   //获取资源对应的文件  
	      System.out.println("url-----"+resource.getURL()); //获取资源对应的URL  
	      if (resource.isReadable()) {  
	         //每次都会打开一个新的流  
	         InputStream is = resource.getInputStream();  
	         this.printContent(is);  
	      }  
	   }
	   
	   /** 
	    * FileSystemResource可以用来获取文件系统里面的资源，对于FileSystemResource而言我们 
	    * 可以获取到其对应的输出流。 
	    * @throws IOException 
	    */  
	   @Test  
	   public void testFileSystem() throws IOException {  
	      FileSystemResource resource = new FileSystemResource("D:\\test.txt");  
	      if (resource.isReadable()) {  
	         //FileInputStream  
	         printContent(resource.getInputStream());  
	      }  
	      if (resource.isWritable()) {  
	         //每次都会获取到一个新的输出流  
	         OutputStream os = resource.getOutputStream();  
	         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));  
	         bw.write("你好，中国！");  
	         bw.flush();  
	         if (os != null) {  
	            os.close();  
	         }  
	         if (bw != null) {  
	            bw.close();  
	         }  
	      }  
	   }

	   /** 
	    * 输出输入流的内容 
	    * @param is 
	    * @throws IOException 
	    */  
	   private void printContent(InputStream is) throws IOException {  
	      BufferedReader br = new BufferedReader(new InputStreamReader(is));  
	      String line;  
	      while ((line=br.readLine()) != null) {  
	         System.out.println(line);  
	      }  
	      if (is != null) {  
	         is.close();  
	      }  
	      if (br != null) {  
	         br.close();  
	      }  
	   } 
}
