package org.macrobug.afo.filter;

public class Personal
  implements javax.servlet.Filter
{

  public Personal()
  {
  }

  public void init(javax.servlet.FilterConfig config)
  {
    fc = config;
  }

  public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain)
  {
    String path = ((javax.servlet.http.HttpServletRequest)request).getRequestURI();
    javax.servlet.RequestDispatcher rd = null;
    javax.servlet.ServletContext sc = fc.getServletContext();
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
          ((javax.servlet.http.HttpServletResponse)response).sendError(403);
        }
        catch(java.io.IOException ex)
        {
          java.util.logging.Logger.getLogger(Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }
      if(rd != null)
      {
        try
        {
          rd.forward(request, response);
        }
        catch(java.io.IOException ex)
        {
          ex.printStackTrace();
        }
        catch(javax.servlet.ServletException ex)
        {
          ex.printStackTrace();
        }
        return;
      }
      try
      {
        chain.doFilter(request, response);
      }
      catch(java.io.IOException ex)
      {
        ex.printStackTrace();
      }
      catch(javax.servlet.ServletException ex)
      {
        ex.printStackTrace();
      }
    } else
    {
      try
      {
        chain.doFilter(request, response);
      }
      catch(javax.servlet.ServletException ex)
      {
        ex.printStackTrace();
      }
      catch(java.io.IOException ex)
      {
        ex.printStackTrace();
      }
    }
  }

  public void destroy()
  {
  }

  javax.servlet.FilterConfig fc;
}
