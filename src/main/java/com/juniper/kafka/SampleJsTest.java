package com.juniper.kafka;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.python.core.Options;
import org.python.util.PythonInterpreter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

public class SampleJsTest {

 public static void main(String[] args) throws NoSuchMethodException, ScriptException, IOException {
	 
	 
	 		Properties props = new Properties();

	 		props.setProperty("python.path", "C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32\\Scripts");
		    
	 		PythonInterpreter.initialize(System.getProperties(), props, new String[] {""});
	 		StringWriter writer = new StringWriter(); //ouput will be stored here

		    ScriptEngineManager manager = new ScriptEngineManager();
		    ScriptContext context = new SimpleScriptContext();

		    context.setWriter(writer); //configures output redirection
		    ScriptEngine engine = manager.getEngineByName("python");
		    engine.eval(new FileReader("D:\\python\\DemoPublisher.py"), context);
		    System.out.println(writer.toString()); 
		}
}
