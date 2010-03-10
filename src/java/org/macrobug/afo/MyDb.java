// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   MyDb.java

package org.macrobug.afo;

import java.util.zip.ZipFile;

// Referenced classes of package org.macrobug.afo:
//			Icon2Db, Image2Db

public class MyDb extends org.macrobug.util.Dibi
{

	private MyDb()
	{
		super("/home/hostingjava.it/SaThot/WEB-INF/db/", "afo", "hosting", "");
	}

	public static org.macrobug.afo.MyDb getDb()
	{
		try
		{
			if(db == null || db.getConnection() != null && db.getConnection().isClosed())
			{
				db = new MyDb();
			}
		}
		catch(java.sql.SQLException ex)
		{
			ex.printStackTrace();
		}
		return db;
	}

	public java.sql.Statement getStatement()
	{
		return super.getStatement();
	}

	public java.sql.Connection getConnection()
	{
		return super.getConnection();
	}

	protected synchronized void reset()
	{
		try
		{
			super.reset();
			java.util.zip.ZipFile fs = new ZipFile("/home/hostingjava.it/SaThot/WEB-INF/db/image.zip");
			if(fs != null)
			{
				org.macrobug.afo.Image2Db.execute(fs, "/home/hostingjava.it/SaThot/image");
			}
			fs = new ZipFile("/home/hostingjava.it/SaThot/WEB-INF/db/icon.zip");
			if(fs != null)
			{
				(new Icon2Db(fs, "/home/hostingjava.it/SaThot/icon", "SaDjehwty")).run();
			}
		}
		catch(java.io.IOException ex)
		{
			java.util.logging.Logger.getLogger(MyDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	private static final java.lang.String path = "/home/hostingjava.it/SaThot/WEB-INF/db/";
	private static org.macrobug.afo.MyDb db = null;

}
