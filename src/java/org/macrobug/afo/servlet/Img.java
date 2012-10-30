package org.macrobug.afo.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.macrobug.afo.bean.*;

public class Img extends HttpServlet
{

  public Img()
  {
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    PrintWriter out = response.getWriter();
    String text = (new StringBuilder()).append(request.getHeader("user-agent")).append("\n").append(request.getRemoteAddr()).append("\nBy SaDjehwty").toString();
    Image[] f = Image.getImages();
    getServletContext().getRequestDispatcher((new StringBuilder()).append("/niu.png?text=").append(text).append("&file=").append(f[org.macrobug.util.Random.getRandom().nextInt(f.length)].getId()).toString()).forward(request, response);
    out.close();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo()
  {
    return "Short description";
  }
}
