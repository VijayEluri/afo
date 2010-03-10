// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Afo.java

package org.macrobug.afo.servlet;

import java.util.Date;
import org.macrobug.afo.bean.User;

public class Afo extends javax.servlet.http.HttpServlet
{

	protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		java.io.PrintWriter out = response.getWriter();
		java.lang.String path = request.getParameter("user");
		org.macrobug.afo.bean.Text s[] = (new User(path)).getTexts();
		if(s == null || s.length < 1)
		{
			response.sendError(404);
		}
		org.macrobug.afo.bean.Image[] f = org.macrobug.afo.bean.Image.getImages();
		org.macrobug.util.Random r = org.macrobug.util.Random.getRandom();
		java.lang.String temp = s[r.nextInt(s.length)].getText().replaceAll("~b", request.getHeader("user-agent")).replaceAll("~h", request.getRemoteAddr()).replaceAll("~d", (new Date(java.lang.System.currentTimeMillis())).toString());
		getServletContext().getRequestDispatcher((new StringBuilder()).append("/niu.png?text=").append(temp).append("&file=").append(f[r.nextInt(f.length)].getId()).toString()).forward(request, response);
		out.close();
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		processRequest(request, response);
	}

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		processRequest(request, response);
	}

	public java.lang.String getServletInfo()
	{
		return "Short description";
	}
}
