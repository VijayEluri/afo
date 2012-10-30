package org.macrobug.afo.filter;

import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Personal
  implements javax.servlet.Filter
{

  public Personal()
  {
  }

  @Override
  public void init(FilterConfig config)
  {
    fc = config;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
  {
    String path = ((HttpServletRequest)request).getRequestURI();
    RequestDispatcher rd = null;
    ServletContext sc = fc.getServletContext();
    if(path.endsWith(".png"))
    {
      path = path.substring(path.lastIndexOf('/') + 1, path.indexOf(".png"));
      if(fc.getInitParameter("tipo").equals("aforisma"))
      {
        rd = sc.getRequestDispatcher((new StringBuilder()).append("/afo?user=").append(path).toString());
      } else
      if(fc.getInitParameter("tipo").equals("avatar"))
      {
        rd = sc.getRequestDispatcher((new StringBuilder()).append("/ico?user=").append(path).toString());
      } else
      {
        try
        {
          ((HttpServletResponse)response).sendError(403);
        }
        catch(IOException ex)
        {
          Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if(rd != null)
      {
        try
        {
          rd.forward(request, response);
        }
        catch(IOException ex)
        {
          ex.printStackTrace();
        }
        catch(ServletException ex)
        {
          ex.printStackTrace();
        }
        return;
      }
      try
      {
        chain.doFilter(request, response);
      }
      catch(IOException ex)
      {
        ex.printStackTrace();
      }
      catch(ServletException ex)
      {
        ex.printStackTrace();
      }
    } else
    {
      try
      {
        chain.doFilter(request, response);
      }
      catch(ServletException ex)
      {
        ex.printStackTrace();
      }
      catch(IOException ex)
      {
        ex.printStackTrace();
      }
    }
  }

  public void destroy()
  {
  }

  FilterConfig fc;
}
