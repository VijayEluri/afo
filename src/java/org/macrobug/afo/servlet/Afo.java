package org.macrobug.afo.servlet;

import java.io.*;
import java.util.Date;
import javax.servlet.http.*;
import org.macrobug.afo.bean.*;
import org.macrobug.util.Random;

public class Afo extends HttpServlet
{

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, IOException
  {
    PrintWriter out = response.getWriter();
    String path = request.getParameter("user");
    Text s[] = (new User(path)).getTexts();
    if(s == null || s.length < 1)
    {
      response.sendError(404);
    }
    Image[] f = Image.getImages();
    Random r = Random.getRandom();
    String temp = s[r.nextInt(s.length)].getText().replaceAll("~b", request.getHeader("user-agent")).replaceAll("~h", request.getRemoteAddr()).replaceAll("~d", (new Date(System.currentTimeMillis())).toString());
    getServletContext().getRequestDispatcher((new StringBuilder()).append("/niu.png?text=").append(temp).append("&file=").append(f[r.nextInt(f.length)].getId()).toString()).forward(request, response);
    out.close();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, IOException
  {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo()
  {
    return "Short description";
  }
}
