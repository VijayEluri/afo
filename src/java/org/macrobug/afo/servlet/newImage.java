package org.macrobug.afo.servlet;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.macrobug.afo.*;

public class newImage extends HttpServlet {

  public newImage() {
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    PrintWriter out = null;
    response.setContentType("text/html;charset=UTF-8");
    try {
      out = response.getWriter();
      ServletFileUpload upload = new ServletFileUpload();
      FileItemIterator iter = upload.getItemIterator(request);
      out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
      out.println("<head>\n<meta http-equiv=\"REFRESH\" content=\"1; URL=index.jsp\"/>\n<link rel=\"stylesheet\" href=\"ext/css.css\" type=\"text/css\" />");
      out.println("</head>\n<body>");
      do {
        if (!iter.hasNext()) {
          break;
        }
        FileItemStream item = iter.next();
        String name = item.getFieldName();
        InputStream stream = item.openStream();
        if (item.isFormField()) {
          System.out.println((new StringBuilder()).append("Form field ").append(name).append(" with value ").append(Streams.asString(stream)).append(" detected.").toString());
        } else {
          File f = File.createTempFile("tmp", "zip");
          FileOutputStream fos = new FileOutputStream(f);
          for (int i = stream.read(); i >= 0; i = stream.read()) {
            fos.write(i);
          }
          fos.flush();
          fos.close();
          String s = getServletConfig().getInitParameter("type");
          if (s.equals("image")) {
            Image2Db m = new Image2Db(f, getServletContext().getRealPath("image"));
            if (m.run()) {
              out.println((new StringBuilder()).append(item.getName()).append(" caricato riuscita").toString());
            }
          } else if (s.equals("icon")) {
            s = (String) request.getSession().getAttribute("user");
            Icon2Db m = new Icon2Db(new ZipFile(f), getServletContext().getRealPath("icon"), s);
            if (m.run()) {
              out.println((new StringBuilder()).append(item.getName()).append(" caricato riuscita").toString());
            }
          }
        }
      } while (true);
      out.print("</body></html>");
      out.flush();
      out.close();
    } catch (FileUploadException ex) {
      out.println("Operazione fallita\n");
      ex.printStackTrace(out);
      out.print("</body></html>");
      out.flush();
      out.close();
      Logger.getLogger(newImage.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
