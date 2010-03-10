// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   New.java

package org.macrobug.afo.servlet;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.macrobug.afo.DrawImage;
import org.mortbay.util.WriterOutputStream;

public class New extends javax.servlet.http.HttpServlet
{
	protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		response.setContentType("image/png");
		java.io.PrintWriter out = response.getWriter();
		java.lang.String text = request.getParameter("text");
		int file = java.lang.Integer.parseInt(request.getParameter("file"));
		text = text.replaceAll("/u", "\\\\u");
		text = org.macrobug.util.U2a.decode(text);
		java.util.StringTokenizer st = new StringTokenizer(text, "\n");
		int max = 0;
		int a = 0;
		java.awt.Font f = new Font("Dialog", 1, 14);
		java.util.ArrayList arr = new ArrayList();
		java.lang.String temp;
		for(; st.hasMoreTokens(); arr.add(temp))
		{
			temp = st.nextToken();
			java.awt.geom.Rectangle2D l = f.getStringBounds(temp, new FontRenderContext(null, true, false));
			a += 14;
			if(l.getWidth() > (double)max)
			{
				max = (int)java.lang.Math.ceil(l.getWidth());
			}
		}

		org.macrobug.afo.DrawImage im = new DrawImage(max, ++a, file, getServletContext().getRealPath("image"));
		java.awt.image.BufferedImage bi = im.getImage();
		java.awt.Dimension d = im.getStart();
		java.awt.Graphics2D g2d = bi.createGraphics();
		g2d.setColor(java.awt.Color.BLACK);
		g2d.setFont(f);
		for(int i = 0; i < arr.size(); i++)
		{
			g2d.drawString((java.lang.String)arr.get(i), d.width, d.height + (i + 1) * 14);
		}

		bi.flush();
		javax.imageio.ImageIO.write(bi, "png", new WriterOutputStream(out, "iso-8859-1"));
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
