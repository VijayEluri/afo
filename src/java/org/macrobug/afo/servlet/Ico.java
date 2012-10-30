package org.macrobug.afo.servlet;

import java.io.*;
import javax.imageio.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.macrobug.afo.bean.User;
import org.macrobug.util.Random;
import org.mortbay.util.WriterOutputStream;

public class Ico extends HttpServlet
{

  public Ico()
  {
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    PrintWriter out;
    response.setContentType("image/png");
    out = response.getWriter();
    String user = request.getParameter("user");
    org.macrobug.afo.bean.Icon i[] = (new User(user)).getIcons();
    String path = getServletContext().getRealPath("icon");
    Random r = Random.getRandom();
    ImageIO.write(ImageIO.read(new File(path, i[r.nextInt(i.length)].getPath())), "png", new WriterOutputStream(out, response.getCharacterEncoding()));
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
