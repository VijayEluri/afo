// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   newImage.java

package org.macrobug.afo.servlet;

import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.macrobug.afo.Icon2Db;
import org.macrobug.afo.Image2Db;

public class newImage extends javax.servlet.http.HttpServlet
{

  public newImage()
  {
  }

  protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException
  {
            java.io.PrintWriter out=null;
            response.setContentType("text/html;charset=UTF-8");
            try {
                out = response.getWriter();
            org.apache.commons.fileupload.servlet.ServletFileUpload upload = new ServletFileUpload();
            org.apache.commons.fileupload.FileItemIterator iter = upload.getItemIterator(request);
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
            out.println("<head>\n<meta http-equiv=\"REFRESH\" content=\"1; URL=index.jsp\"/>\n<link rel=\"stylesheet\" href=\"ext/css.css\" type=\"text/css\" />");
            out.println("</head>\n<body>");
            do {
                if (!iter.hasNext()) {
                    break;
                }
                org.apache.commons.fileupload.FileItemStream item = iter.next();
                String name = item.getFieldName();
                java.io.InputStream stream = item.openStream();
                if (item.isFormField()) {
                    System.out.println((new StringBuilder()).append("Form field ").append(name).append(" with value ").append(org.apache.commons.fileupload.util.Streams.asString(stream)).append(" detected.").toString());
                } else {
                    java.io.File f = java.io.File.createTempFile("tmp", "zip");
                    java.io.FileOutputStream fos = new FileOutputStream(f);
                    for (int i = stream.read(); i >= 0; i = stream.read()) {
                        fos.write(i);
                    }
                    fos.flush();
                    fos.close();
                    String s = getServletConfig().getInitParameter("type");
                    if (s.equals("image")) {
                        org.macrobug.afo.Image2Db m = new Image2Db(f, getServletContext().getRealPath("image"));
                        if (m.run()) {
                            out.println((new StringBuilder()).append(item.getName()).append(" caricato riuscita").toString());
                        }
                    } else if (s.equals("icon")) {
                        s = (String) request.getSession().getAttribute("user");
                        org.macrobug.afo.Icon2Db m = new Icon2Db(new ZipFile(f), getServletContext().getRealPath("icon"), s);
                        if (m.run()) {
                            out.println((new StringBuilder()).append(item.getName()).append(" caricato riuscita").toString());
                        }
                    }
                }
            } while (true);
            out.print("</body></html>");
            out.flush();
            out.close();}
        catch(org.apache.commons.fileupload.FileUploadException ex){
            out.println("Operazione fallita\n");
            ex.printStackTrace(out);
            out.print("</body></html>");
            out.flush();
            out.close();
            Logger.getLogger(newImage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
