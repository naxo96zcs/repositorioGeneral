package com.example.jakartaee_webapp2;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringDemo {
    public static void main(String... args) {
    	        MessageSource resources = new ClassPathXmlApplicationContext("spring-app.xml");
 	   	String admin = resources.getMessage("user.admin", null, "Default", new Locale("de"));
 	   	System.out.println(admin);
 	   	String superadmin = resources.getMessage("user.superadmin", null, "Default", new Locale("de"));
 	   	System.out.println(superadmin);	   	
         	String name = resources.getMessage("errormsg.name", null, "Default", new Locale("de"));
 	   	System.out.println(name);
     	        String pwd = resources.getMessage("errormsg.pwd", null, "Default", new Locale("de"));
 	   	System.out.println(pwd);	   	
    } 
} 