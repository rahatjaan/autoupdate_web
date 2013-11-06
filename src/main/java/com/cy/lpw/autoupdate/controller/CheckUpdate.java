package com.cy.lpw.autoupdate.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;

/**
 * Servlet implementation class CheckUpdate
 */
public class CheckUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckUpdate() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wathFile = ConfigurationUtil.getWatchFilePath();
		
		try {
			File file = new File(wathFile);
			System.out.println("Before Format : " + file.lastModified());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			System.out.println("After Format : " + sdf.format(file.lastModified()));
			response.getWriter().println(file.lastModified()+",\n"+sdf.format(file.lastModified()));
		} catch (IOException e) {
			e.printStackTrace();
			response.getWriter().println("false");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
