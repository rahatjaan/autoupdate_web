package com.cy.lpw.autoupdate.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtil {
	private static final String WATCH_FOLDER_KEY = "watch_folder";
	private static final String WATCH_File_KEY = "installer_file";
	private static final String INSTALLER_CONTENT_TYPE_KEY = "installer_content_type";
	static Properties props = new Properties();
	static{
		
		try {
			props.load(ConfigurationUtil.class.getClassLoader().getResourceAsStream("settings.properties"));
			System.out.println(getString("host"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getString(String key) {
		return props.getProperty(key);
	}

	public static String getWatchFolderPath(){
		return props.getProperty(WATCH_FOLDER_KEY);
	}
	
	public static String getWatchFilePath(){
		return props.getProperty(WATCH_FOLDER_KEY)+File.separator+props.getProperty(WATCH_File_KEY);
	}

	public static String getAttachmentFileName() {
		return props.getProperty(WATCH_File_KEY);
	}

	public static String getInstallerContentType() {
		return props.getProperty(INSTALLER_CONTENT_TYPE_KEY);
	}

}
