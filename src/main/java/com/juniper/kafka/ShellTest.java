package com.juniper.kafka;

import java.io.IOException;

public class ShellTest {

	public static void main(String[] args) {
		String[] cmd = { "bash", "-c", "~/path/to/shellscript.sh" };
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
