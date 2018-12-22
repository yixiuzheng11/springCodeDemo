package beanDefination;

import java.io.BufferedReader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.ContextConfiguration;

import beans.ClassA;

/*网上找过很多方法多没有解决Class<SpringJUnit4ClassRunner> cannot be resolved to a type
最后通过对比正常的junit测试用例, 发现是因为没有import SpringJUnit4ClassRunner.
只需在import下面的包即可.
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;*/

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration("classpath:spring/applicationContext.xml")//加载配置文件 
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件 
public class ResourceDemo {
	   @Autowired 
	   private ClassA classA;  
	    
	   @Test  
	   public void test() {  
	      classA.printContent();  
	   }  

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
	    * 针对于URL进行封装的Resource，可用来从URL获取资源内容 
	    * @throws Exception 
	    */  
	   @Test  
	   public void testURL() throws Exception {  
	      UrlResource resource = new UrlResource("http://www.baidu.com");  
	      if (resource.isReadable()) {  
	         //URLConnection对应的getInputStream()。  
	         printContent(resource.getInputStream());  
	      }  
	   }
	   
	   /** 
	    * 针对于字节数组封装的Resource，用来从字节数组获取资源内容 
	    * @throws IOException 
	    */  
	   @Test  
	   public void testByteArray() throws IOException { 
	      ByteArrayResource resource = new ByteArrayResource("Hello".getBytes());
	      //ByteArrayInputStream()  
	      printContent(resource.getInputStream());  
	   }

	   @Test  
	   public void testInputStream() throws Exception {  
	      InputStream is = new FileInputStream("D:\\test.txt");  
	      InputStreamResource resource = new InputStreamResource(is);  
	      //对于InputStreamResource而言，其getInputStream()方法只能调用一次，继续调用将抛出异常。  
	      InputStream target = resource.getInputStream();   //返回的就是构件时的那个InputStream  
	      //is将在printContent方法里面进行关闭
	      printContent(target);  
	   }
	   
	   @Test  
	   public void testResourceLoader() {  
	      ResourceLoader loader = new DefaultResourceLoader();  
	      Resource resource = loader.getResource("http://www.google.com.hk");  
	      System.out.println(resource instanceof UrlResource); //true  
	      //注意这里前缀不能使用“classpath*:”，这样不能真正访问到对应的资源，exists()返回false  
	      resource = loader.getResource("classpath:test.txt");  
	      System.out.println(resource instanceof ClassPathResource); //true  
	      resource = loader.getResource("test.txt");
	      System.out.println(resource instanceof ClassPathResource); //true  
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
