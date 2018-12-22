package beanDefination;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import beans.TestBean;

public class BeanDefinationDemo {
	public static void main(String[] args) {
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		Resource resource = new InputStreamResource(BeanDefinationDemo.class.getResourceAsStream("/spring/spring/xsd.xml"));
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry); 
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
		reader.loadBeanDefinitions(resource);
		System.out.println("beanDefination total ----- "+registry.getBeanDefinitionCount());
		String[] beanNames = registry.getBeanDefinitionNames();
		for(int i=0; i<beanNames.length; i++) {
			System.out.println(beanNames[i]);
		}
	}
	
	@Test
	public void testDtdBeanDefination1() {
		Resource resource = new InputStreamResource(BeanDefinationDemo.class.getResourceAsStream("/spring/spring/test.xml"));
		System.out.println(this.getClass().getResource("/spring/spring/test.xml").getFile().toString());
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_DTD);
		reader.loadBeanDefinitions(resource);
		System.out.println("beanDefination total ----- "+registry.getBeanDefinitionCount());
		String[] aliases = registry.getAliases("multiAliased");
		for(int j=0; j<aliases.length; j++) {
			System.out.print(aliases[j]+"	");
		}
		System.out.println();
		String[] names = registry.getBeanDefinitionNames();
		for(int i=0; i<names.length; i++) {
			System.out.print(names[i]+"--");
		}
		System.out.println();
		BeanDefinition bd = registry.getBeanDefinition("aliased");
		System.out.println(bd.toString());
	}
	
	@Test
	public void testDtdBeanDefination2() {
		System.out.println(this.getClass().getResource("/spring/test.xml").getFile().toString());
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/spring/test.xml"));
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_DTD);
		reader.loadBeanDefinitions(resource);
		System.out.println("beanDefination total ----- "+factory.getBeanDefinitionCount());
		String[] aliases = factory.getAliases("multiAliased");
		for(int j=0; j<aliases.length; j++) {
			System.out.print(aliases[j]+"	");
		}
		System.out.println();
		String[] names = factory.getBeanDefinitionNames();
		for(int i=0; i<names.length; i++) {
			System.out.print(names[i]+"	");
		}
		System.out.println("getValidationMode--- "+reader.getValidationMode());
		BeanDefinition bd = factory.getBeanDefinition("aliased");
		System.out.println(bd.toString());
		TestBean bean = (TestBean) factory.getBean("aliased");
		bean.getAge();
	}
	
	@Test
	public void testXsdBeanDefination1() {
		System.out.println(this.getClass().getResource("/spring/xsd.xml").getFile().toString());
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/spring/xsd.xml"));
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
		reader.loadBeanDefinitions(resource);
		System.out.println("beanDefination total ----- "+registry.getBeanDefinitionCount());
		String[] aliases = registry.getAliases("multiAliased");
		for(int j=0; j<aliases.length; j++) {
			System.out.print(aliases[j]+"	");
		}
		System.out.println();
		String[] names = registry.getBeanDefinitionNames();
		for(int i=0; i<names.length; i++) {
			System.out.print(names[i]+"	");
		}
		System.out.println();
		BeanDefinition bd = registry.getBeanDefinition("aliased");
		System.out.println(bd.toString());
	}

	@Test
	public void testXsdBeanDefination2() {
		System.out.println(this.getClass().getResource("/spring/xsd.xml").getFile().toString());
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/spring/xsd.xml"));
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
		reader.loadBeanDefinitions(resource);
		System.out.println("beanDefination total ----- "+factory.getBeanDefinitionCount());
		String[] aliases = factory.getAliases("myalias");
		for(int j=0; j<aliases.length; j++) {
			System.out.print(aliases[j]+"	");
		}
		System.out.println();
		String[] names = factory.getBeanDefinitionNames();
		for(int i=0; i<names.length; i++) {
			System.out.print(names[i]+"	");
		}
		System.out.println("getValidationMode--- "+reader.getValidationMode());
		//BeanDefination是通过bean的id属性获取的，通过别名是获取不到的
		BeanDefinition bd = factory.getBeanDefinition("youralias");
		System.out.println(bd.toString());
		TestBean bean = (TestBean) factory.getBean("youralias");
		System.out.println(bean.getAge());
	}
}
