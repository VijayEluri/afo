package org.macrobug.afo.servlet;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import org.macrobug.afo.DrawImage;
import org.macrobug.util.*;
import org.mortbay.util.WriterOutputStream;

public class New extends HttpServlet
{
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("image/png");
    PrintWriter out = response.getWriter();
    String text = request.getParameter("text");
    int file = Integer.parseInt(request.getParameter("file"));
    text = text.replaceAll("/u", "\\\\u");
    text = U2a.decode(text);
    java.util.StringTokenizer st = new StringTokenizer(text, "\n");
    int max = 0;
    int a = 0;
    Font f = new Font("Dialog", 1, 14);
    java.util.ArrayList arr = new ArrayList();
    String temp;
    for(; st.hasMoreTokens(); arr.add(temp))
    {
      temp = st.nextToken();
      Rectangle2D l = f.getStringBounds(temp, new FontRenderContext(null, true, false));
      a += 14;
      if(l.getWidth() > (double)max)
      {
        max = (int)Math.ceil(l.getWidth());
      }
    }

    DrawImage im = new DrawImage(max, ++a, file, getServletContext().getRealPath("image"));
    BufferedImage bi = im.getImage();
    Dimension d = im.getStart();
    Graphics2D g2d = bi.createGraphics();
    g2d.setColor(Color.BLACK);
    g2d.setFont(f);
    for(int i = 0; i < arr.size(); i++)
    {
      g2d.drawString((String)arr.get(i), d.width, d.height + (i + 1) * 14);
    }

    bi.flush();
    ImageIO.write(bi, "png", new WriterOutputStream(out, "iso-8859-1"));
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
