// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Ico.java

package org.macrobug.afo.servlet;

import java.io.File;
import org.macrobug.afo.bean.User;
import org.mortbay.util.WriterOutputStream;

public class Ico extends javax.servlet.http.HttpServlet
{

  public Ico()
  {
  }

  protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException
  {
    java.io.PrintWriter out;
    response.setContentType("image/png");
    out = response.getWriter();
    String user = request.getParameter("user");
    org.macrobug.afo.bean.Icon i[] = (new User(user)).getIcons();
    String path = getServletContext().getRealPath("icon");
    org.macrobug.util.Random r = org.macrobug.util.Random.getRandom();
    javax.imageio.ImageIO.write(javax.imageio.ImageIO.read(new File(path, i[r.nextInt(i.length)].getPath())), "png", new WriterOutputStream(out, response.getCharacterEncoding()));
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

  public String getServletInfo()
  {
    return "Short description";
  }
}
