// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Image2Db.java

package org.macrobug.afo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipFile;
import org.macrobug.afo.bean.Image;

// Referenced classes of package org.macrobug.afo:
//			NameGen


public class Image2Db
{
    private NameGen ng;
    private String path;
    private File file;

	public boolean run()
	{
		try
		{
			org.macrobug.afo.bean.Image img = new Image();
			java.util.zip.ZipFile zf = new ZipFile(file);
			java.util.Enumeration enu = zf.entries();
			java.util.zip.ZipEntry ze;
			do
			{
				ze = (java.util.zip.ZipEntry)enu.nextElement();
			} while(!ze.getName().endsWith(".xml") && enu.hasMoreElements());
			javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setIgnoringComments(true);
			javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document d = db.parse(zf.getInputStream(ze));
			org.w3c.dom.NodeList n = d.getDocumentElement().getChildNodes();
			for(int i = 0; i < n.getLength(); i++)
			{
				if(n.item(i).getNodeType() != 1)
				{
					continue;
				}
				ze = zf.getEntry(n.item(i).getAttributes().item(0).getNodeValue());
				java.lang.String f = ng.getName(ze.getName().substring(ze.getName().lastIndexOf('.')));
				img.setImg(n.item(i).getAttributes().item(1).getNodeValue().charAt(0), f);
				java.io.InputStream is = zf.getInputStream(ze);
				java.io.FileOutputStream fos = new FileOutputStream((new StringBuilder()).append(path).append('/').append(f).toString());
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
		catch(org.xml.sax.SAXException ex)
		{
			java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(javax.xml.parsers.ParserConfigurationException ex)
		{
			java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(java.util.zip.ZipException ex)
		{
			java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(java.io.IOException ex)
		{
			java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public Image2Db(java.io.File file, java.lang.String path)
	{
		this.file = file;
		this.path = path;
		ng = new NameGen(path);
	}

	public Image2Db(java.lang.String file, java.lang.String path)
	{
		this(new File(file), path);
	}
public static void main(java.lang.String args[])
	{
		org.macrobug.afo.Image2Db m = new Image2Db("prova.zip", "image");
		java.lang.System.out.println(m.run());
	}
	public static void execute(java.util.zip.ZipFile zf, java.lang.String path)
	{
		java.io.File dir;
		java.util.Enumeration enu;
		dir = new File(path);
		if(!dir.exists())
		{
			dir.mkdir();
		}
		enu = zf.entries();
do{
                    java.io.InputStream is = null;
		try
		{
			is = zf.getInputStream((java.util.zip.ZipEntry) enu.nextElement());
                java.io.File f = java.io.File.createTempFile("temp", "xyz");
                java.io.FileOutputStream fos = new FileOutputStream(f);
                for (int i = is.read(); i >= 0; i = is.read()) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                org.macrobug.afo.Image2Db zd = new Image2Db(f, dir.getAbsolutePath());
                zd.run();
                    is.close();}
                catch(IOException ex){java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);}
                finally{
                try {
                    is.close();
                } // Misplaced declaration of an exception variable
                catch (java.io.IOException ex) {
                    java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                }}
		while(!enu.hasMoreElements());
}}
