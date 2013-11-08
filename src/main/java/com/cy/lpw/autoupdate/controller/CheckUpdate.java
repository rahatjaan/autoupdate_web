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
		System.out.println("lastDate :"+lastUpdate);
		PrintWriter writer = response.getWriter();
		String output="false";
		if(lastUpdate==null || lastUpdate.isEmpty()){
			System.out.println("lastDate is empty");
			output = ErrorMessages.INPUT_DATE_EMPTY;
			writer.println(output+". Example http://localhost:8080/lpwupdates/checkupdate?lastUpdate=2013-11-10");
			return;
		}
		try {
			File file = new File(wathFile);
			System.out.println("Before Format : " + file.lastModified());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String modifiedDateStr = sdf.format(file.lastModified());
			Date modifiedDate = sdf.parse(modifiedDateStr);
			Date lastupdateDownloaded = sdf.parse(lastUpdate);
			if(modifiedDate.compareTo(lastupdateDownloaded)>0){
        		System.out.println("modifiedDate is after Date2");
        		output="true";
        	}else if(modifiedDate.compareTo(lastupdateDownloaded)<0){
        		System.out.println("modifiedDate is before Date2");
        		output="false";
        	}else if(modifiedDate.compareTo(lastupdateDownloaded)==0){
        		System.out.println("modifiedDate is equal to Date2");
        		output="false";
        	}else{
        		System.out.println("How to get here?");
        	}
//			System.out.println("After Format : " + sdf.format(file.lastModified()));
//			response.getWriter().println(file.lastModified()+",\n"+sdf.format(file.lastModified()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		writer.println(output);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
