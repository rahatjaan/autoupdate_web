package com.cy.lpw.autoupdate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;

/**
 * Servlet implementation class DownloadUpdate
 */
public class DownloadUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BYTES_DOWNLOAD = 1024;
    public DownloadUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wathFile = ConfigurationUtil.getWatchFilePath();
		File file = new File(wathFile);
		InputStream fileStream = new FileInputStream(file);
		presentDownloadFile(response,fileStream);
	}

	private void presentDownloadFile(HttpServletResponse response,InputStream is ) throws IOException {
		response.setContentType(ConfigurationUtil.getInstallerContentType());
		String attachmentFileName = ConfigurationUtil.getAttachmentFileName();
		attachmentFileName = attachmentFileName.replace(" ", "");
		attachmentFileName = attachmentFileName.trim();
		response.setHeader("Content-Disposition",
	                     "attachment;filename="+attachmentFileName);
		int read=0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();
	 
		while((read = is.read(bytes))!= -1){
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
