package com.juniper.kafka;

import java.io.*;
import java.util.Map;

public class PythonCaller {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
		String pythonScriptPath = "D:\\python\\DemoPublisher.py";
		String[] cmd = new String[2];
		cmd[0] = "C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe"; // check version of installed python: python -V
		cmd[1] = pythonScriptPath;
		ProcessBuilder pb = new ProcessBuilder("CMD.exe", "/C", "SET"); // SET prints out the environment variables
	     pb.redirectErrorStream(true);
	     Map<String,String> env = pb.environment();
	     String path = env.get("Path") + ";C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32;C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32\\Scripts;C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32\\Lib\\site-packages";
	     env.put("Path", path);
	     Process process = pb.start();
		/*Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);

		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line = bfr.readLine()) != null) {
			System.out.println(line);
		}*/
		//ProcessBuilder pb = new ProcessBuilder(cmd[0],pythonScriptPath);
		//Process p = pb.start();
		//BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

		//String command = "python /c start python D:\\python\\DemoPublisher.py";
		/*String line ="";
		String errline ="";
		Process p = Runtime.getRuntime().exec("python D:\\python\\DemoPublisher.py");
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader errin = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		while ((errline = errin.readLine()) != null) {
			System.out.println(errline);
		}*/
	     
	     String[] args1 = new String[] { "C:\\Users\\mohammed_razak01\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe",
	                "D:\\python\\DemoPublisher.py"};
	Process pr = Runtime.getRuntime().exec(args1);
	BufferedReader in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
	String line;
	StringBuffer res = new StringBuffer();
	while ((line = in.readLine()) != null) {
	    System.out.println(line);
	    res.append(line);
	}
	     
		}catch (Exception e) {
			System.out.println("err");
			e.printStackTrace();
		}
		
	}
}
