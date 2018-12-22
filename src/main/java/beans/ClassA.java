package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.core.io.Resource;

public class ClassA {  
   //持有一个Resource属性  
   private Resource resource;
   public void printContent() {  
      if (resource != null && resource.exists()) {  
         if (resource.isReadable()) {  
            InputStream is;  
            try {  
                is = resource.getInputStream();  
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
            } catch (IOException e) {
                e.printStackTrace();  
            }  
         }  
      }  
   }  
    
   public void setResource(Resource resource) {  
      this.resource = resource;  
   }  
    
} 