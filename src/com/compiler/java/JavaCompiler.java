package com.compiler.java;

import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
import java.io.File;
/*import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;*/
import java.io.InputStreamReader;
import java.io.OutputStream;
/*import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;*/
/*
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
*/
public class JavaCompiler {
	
	public static void compileJavaFile(File file)
    {
        String compileFileCommand = "javac " + file.getAbsolutePath();
        try
        {
            System.out.println("Executing Java File");

            Process compileProcess = Runtime.getRuntime().exec(compileFileCommand);

            String line = "";
            BufferedReader bri = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
            while ((line = bri.readLine()) != null)
            {
                System.out.println(line);
            }
            bri.close();
            while ((line = bre.readLine()) != null)
            {
                System.out.println(line);
            }
            bre.close();
            compileProcess.waitFor();
            System.out.println("Done.");
        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
    }

    public static String runJavaFile(File file,String input)
    {
        String runFileCommand = "java " + file.getName().split(".java")[0];
        String res="";
        try
        {
            System.out.println("runFileCommand : " + runFileCommand);
            System.out.println("Running Java File");

            Process runProcess = Runtime.getRuntime().exec(runFileCommand,null, new File("/C:/Users/panka"));
            if(!(input.isEmpty()||input.equals("")))
            {
            OutputStream out =runProcess.getOutputStream();
            out.write(input.getBytes());
            out.close();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String line = reader.readLine();
            System.out.println("line = " + line);
            
            while (line != null)
            {
                System.out.println(line);
                res=res+line;
                line = reader.readLine();
            }

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return res;
    }
	
	
	
	
	
	/*

	public static int compileJava(File file) throws InterruptedException, IOException{
	

		//String loc="C:/Users/panka/pkz_projects/compiler-service/TestFiles/java"+File.separator+"My.java";
		
		//String s="C:/Users/panka/pkz_projects/compiler-service/TestFiles/java/TestClass.java";;
	 //   File f=new File(s);
		//Files.copy(file.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
		//File file1=new File(loc);
		 javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//System.out.println("my"+file.getAbsolutePath());
		List<File> sourceFileList = new ArrayList<File>();
		sourceFileList.add( file );
		StandardJavaFileManager fileManager = compiler.getStandardFileManager( null, null, null );
		Iterable<? extends JavaFileObject> javaSource = fileManager.getJavaFileObjectsFromFiles( sourceFileList );
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaSource);
		task.call(); 
	
		
		
        javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(System.in,System.out,System.err,file.getAbsolutePath());
        //System.out.println("Compile result code = " + result);
		return result;
	}
	public static String invokeClass(String className) throws URISyntaxException, IOException, ReflectiveOperationException {
		
		Class<?> clazz = Class.forName(className);
	    // Alternatively, you can load the new class with a new Classloader, if you don't want to pollute the current Classloader
	    // Class<?> clazz = new URLClassLoader(new URL[]{getClass().getClassLoader().getResource("").toURI().toURL()}, getClass().getClassLoader()).loadClass(className);
	    Method main = clazz.getDeclaredMethod("main", String[].class);
	    try ( ByteArrayOutputStream out = new ByteArrayOutputStream();
	          PrintStream ps = new PrintStream(out)) {
	        System.setOut(ps);
	        main.invoke(main, new Object[]{new String[0]});
	      //  System.out.println(out.toString());
	        return out.toString();
	    }
	    finally {
	        // Reset to the console
	        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	    }
	
	
	
	}*/}
