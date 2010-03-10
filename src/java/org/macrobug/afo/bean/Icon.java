// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Icon.java

package org.macrobug.afo.bean;

import java.io.File;
import java.util.logging.Logger;

// Referenced classes of package org.macrobug.afo.bean:
//			Bean, User

public class Icon extends org.macrobug.afo.bean.Bean
{

	public synchronized boolean save()
	{
            try
		{
		if(exists())
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("update icon set username=?, \"path\"=? where id=?");
			ps.setInt(1, user.getId());
			ps.setString(2, path);
			ps.setInt(3, id);
			return ps.execute();
		}
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("insert into icon(username,\"path\") values (?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, path);
			return ps.execute();
		}
		catch(java.sql.SQLException ex)
		{
			Logger.getLogger(Icon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean exists()
	{
		if(id >= 0)
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select * from icon where id=?");
				ps.setInt(1, id);
				return ps.executeQuery().next();
			}
			catch(java.sql.SQLException ex)
			{
				Logger.getLogger(Icon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	public java.lang.String getPath()
	{
		return path;
	}

	public org.macrobug.afo.bean.User getUser()
	{
		return user;
	}

	public Icon(int id)
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select username,\"path\" from icon where id=?");
			ps.setInt(1, id);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				path = rs.getString(2);
				this.id = id;
				user = new User(rs.getInt(1));
			}
		}
		catch(java.sql.SQLException ex)
		{
			Logger.getLogger(Icon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public Icon(int id, org.macrobug.afo.bean.User user, java.lang.String path)
	{
		this(user, path);
		this.id = id;
	}

	public Icon(org.macrobug.afo.bean.User user, java.lang.String path)
	{
		this.user = user;
		this.path = path;
	}

	public boolean delete(java.lang.String path)
	{
		return delete() && (new File(path, this.path)).delete();
	}

	public boolean delete()
	{
		if(exists())
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("delete from icon where id=?");
				ps.setInt(1, id);
				return ps.executeUpdate() > 0;
			}
			catch(java.sql.SQLException ex)
			{
				Logger.getLogger(Icon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			return false;
		} else
		{
			return true;
		}
	}

	public static synchronized int count()
	{
		try
		{
			org.macrobug.afo.MyDb d = org.macrobug.afo.MyDb.getDb();
			java.sql.ResultSet rs = d.getStatement().executeQuery("select count(id) from icon");
			return rs.next() ? rs.getInt(1) : 0;
		}
		catch(java.sql.SQLException ex)
		{
			Logger.getLogger(Icon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return 0;
	}

	private org.macrobug.afo.bean.User user;
	private java.lang.String path;
}
