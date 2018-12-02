package com.impl;

/*import java.io.BufferedReader;
import java.io.BufferedWriter;*/
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

//import javax.tools.ToolProvider;
import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.compiler.java.*;

import com.compiler.cpp.*;

@Path("/api")
//@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class App {
	@POST
	@Path("/compile/{langName}/{hello}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.TEXT_XML,MediaType.TEXT_PLAIN})
	
	public String compileConnect(@PathParam("langName") String langName,@PathParam("hello") String fileName,String body) throws URISyntaxException, IOException, ReflectiveOperationException, InterruptedException{
		
	
		//System.out.println("/*******************/*"+body+"/************");
		FileWriter fileWriter=null;
	//	String langName="cpp";
		String result="Error";
		if(langName.equalsIgnoreCase("java"))
		{ String input="";
			//String loc="C:/Users/panka/pkz_projects/compiler-service/src/com/testclasses"+File.separator+fileName+".java";
			String loc="C:/Users/panka"+File.separator+fileName+".java";
			//String des="C:/Users/panka/pkz_projects/compiler-service/TestFiles/java/1"+fileName+".java";
			try{
				
			File file=new File(loc);
			fileWriter=new FileWriter(file);
			 StringTokenizer st = new StringTokenizer(body,"$");  
			//fileWriter.write(inputFile.getBody());
			 if(st.countTokens()<2)
			 {	fileWriter.write(st.nextToken());}
			 else
			 {
			input=st.nextToken();
			fileWriter.write(st.nextToken());
			 }
			fileWriter.close();
			
			
			JavaCompiler.compileJavaFile(file);
			 
			   Thread.sleep(2000);
			  
		result=JavaCompiler.runJavaFile(file,input);
			 
		 /*  int res=JavaCompiler.compileJava(file);
		 
		   Thread.sleep(3000);
		   if(res==0)
		    return   JavaCompiler.invokeClass("com.testclasses."+fileName);
		   else
			   return "Compilation error:"+res;*/
			 //javaClass.setResult(JavaCompiler.compileJava(file));
	       
			
			}
			finally{
				
				
				
			}
			// return JavaCompiler.invokeClass(loc);
			
		}
		
		if(langName.equalsIgnoreCase("cpp")||langName.equalsIgnoreCase("c")){
			
		String loc="";
			//String loc="C:/Users/panka/pkz_projects/compiler-service/src/com/testclasses"+File.separator+fileName+".java";
			if(langName.equals("c"))
				 loc="C:/Users/panka"+File.separator+fileName+".c";
			else
				if(langName.equals("cpp"))
					 loc="C:/Users/panka"+File.separator+fileName+".cpp";
			//String des="C:/Users/panka/pkz_projects/compiler-service/TestFiles/java/1"+fileName+".java";
			try{
				
			File file=new File(loc);
			 String input="";
			fileWriter=new FileWriter(file);
			 StringTokenizer st = new StringTokenizer(body,"$");  
			//fileWriter.write(inputFile.getBody());
			 System.out.println(st.countTokens());
			 if(st.countTokens()<2)
				 fileWriter.write(st.nextToken());
			 else
			 {
			 input=st.nextToken();
			 fileWriter.write(st.nextToken());
			 }
			 System.out.println("haha"+input);
			fileWriter.close();
			
			
			CppCompiler.compileCppFile(file);
			 
			   Thread.sleep(2000);
			  
		result=CppCompiler.runCppFile(file,input);
			//return  CppCompiler.compileCpp();
			// return CppCompiler.invokeClass("com.compiler.TestClass");
			
			
			//return "cpp";
		}
			finally{
				
				
			}
			
		
		
				
		
		//return "0";
	}
	
	
	return result;
	}
}
