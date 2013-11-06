package com.cy.lpw.autoupdate.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.lpw.autoupdate.config.ConfigurationUtil;
import com.cy.lpw.autoupdate.model.ErrorMessages;

/**
 * Servlet implementation class CheckUpdate
 */
public class CheckUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckUpdate() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wathFile = ConfigurationUtil.getWatchFilePath();
		String lastUpdate = request.getParameter("lastUpdate");
		PrintWriter writer = response.getWriter();
		String output="false";
		if(lastUpdate==null || lastUpdate.isEmpty())
			output = ErrorMessages.INPUT_DATE_EMPTY;
		try {
			File file = new File(wathFile);
			System.out.println("Before Format : " + file.lastModified());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String modifiedDateStr = sdf.format(file.lastModified());
			Date modifiedDate = sdf.parse(modifiedDateStr);
			Date lastupdateDownloaded = sdf.parse(lastUpdate);
			System.out.println("After Format : " + sdf.format(file.lastModified()));
			response.getWriter().println(file.lastModified()+",\n"+sdf.format(file.lastModified()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		writer.println(output);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
