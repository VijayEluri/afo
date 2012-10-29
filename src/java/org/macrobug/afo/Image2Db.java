package org.macrobug.afo;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;
import javax.xml.parsers.*;
import org.macrobug.afo.bean.Image;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Image2Db
{
    private NameGen ng;
    private String path;
    private File file;

  public boolean run()
  {
    try
    {
      Image img = new Image();
      ZipFile zf = new ZipFile(file);
      java.util.Enumeration enu = zf.entries();
      ZipEntry ze;
      do
      {
        ze = (ZipEntry)enu.nextElement();
      } while(!ze.getName().endsWith(".xml") && enu.hasMoreElements());
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setIgnoringElementContentWhitespace(true);
      dbf.setIgnoringComments(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document d = db.parse(zf.getInputStream(ze));
      NodeList n = d.getDocumentElement().getChildNodes();
      for(int i = 0; i < n.getLength(); i++)
      {
        if(n.item(i).getNodeType() != 1)
        {
          continue;
        }
        ze = zf.getEntry(n.item(i).getAttributes().item(0).getNodeValue());
        String f = ng.getName(ze.getName().substring(ze.getName().lastIndexOf('.')));
        img.setImg(n.item(i).getAttributes().item(1).getNodeValue().charAt(0), f);
        InputStream is = zf.getInputStream(ze);
        FileOutputStream fos = new FileOutputStream((new StringBuilder()).append(path).append('/').append(f).toString());
        for(int b = is.read(); b >= 0; b = is.read())
        {
          fos.write(b);
        }

        fos.flush();
        fos.close();
      }

      img.save();
      return true;
    }
    catch(SAXException ex)
    {
      Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(ParserConfigurationException ex)
    {
      Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(ZipException ex)
    {
      Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(IOException ex)
    {
      Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public Image2Db(File file, String path)
  {
    this.file = file;
    this.path = path;
    ng = new NameGen(path);
  }

  public Image2Db(String file, String path)
  {
    this(new File(file), path);
  }
public static void main(String args[])
  {
    Image2Db m = new Image2Db("prova.zip", "image");
    System.out.println(m.run());
  }
  public static void execute(ZipFile zf, String path)
  {
    File dir;
    Enumeration enu;
    dir = new File(path);
    if(!dir.exists())
    {
      dir.mkdir();
    }
    enu = zf.entries();
do{
                    InputStream is = null;
    try
    {
      is = zf.getInputStream((ZipEntry) enu.nextElement());
                File f = File.createTempFile("temp", "xyz");
                FileOutputStream fos = new FileOutputStream(f);
                for (int i = is.read(); i >= 0; i = is.read()) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                Image2Db zd = new Image2Db(f, dir.getAbsolutePath());
                zd.run();
                    is.close();}
                catch(IOException ex){Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);}
                finally{
                try {
                    is.close();
                } // Misplaced declaration of an exception variable
                catch (IOException ex) {
                    Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
                }
                }}
    while(!enu.hasMoreElements());
}}
