package com.packtFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.Properties;

public final class Configuration {

	private Configuration() {
		
	}
	
	private static Properties properties;
	
	public static void load() throws IOException {
		properties = new Properties();
		InputStream is = new FileInputStream(new File("config.properties"));
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						is,
						StandardCharsets.UTF_8));
		try {
			properties.load(reader);
		} finally {
			is.close();
			reader.close();
		}
	}
	
	public static String get(String option) {
		String value = properties.getProperty(option);
		if(value == null) {
			return "";
		}
		return value;
	}
	
	public static void print() {
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
		}
	}
	
	

}
