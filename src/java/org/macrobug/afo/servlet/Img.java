// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Img.java

package org.macrobug.afo.servlet;

public class Img extends javax.servlet.http.HttpServlet
{

  public Img()
  {
  }

  protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException
  {
    java.io.PrintWriter out = response.getWriter();
    String text = (new StringBuilder()).append(request.getHeader("user-agent")).append("\n").append(request.getRemoteAddr()).append("\nBy SaDjehwty").toString();
    org.macrobug.afo.bean.Image[] f = org.macrobug.afo.bean.Image.getImages();
    getServletContext().getRequestDispatcher((new StringBuilder()).append("/niu.png?text=").append(text).append("&file=").append(f[org.macrobug.util.Random.getRandom().nextInt(f.length)].getId()).toString()).forward(request, response);
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
