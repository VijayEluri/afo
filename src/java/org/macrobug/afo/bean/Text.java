// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Text.java

package org.macrobug.afo.bean;
//			Bean, User

public class Text extends org.macrobug.afo.bean.Bean
{

	public Text(int id)
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select username,text from text where id=?");
			ps.setInt(1, id);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				text = rs.getString(2);
				user = new User(rs.getInt(1));
				this.id = id;
			}
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public Text(int id, org.macrobug.afo.bean.User user, java.lang.String text)
	{
		this.user = user;
		this.text = text;
		this.id = id;
	}

	public Text(org.macrobug.afo.bean.User user, java.lang.String text)
	{
		this.user = user;
		this.text = org.macrobug.util.U2a.encode(text);
	}

	public java.lang.String getText()
	{
		return text;
	}

	public org.macrobug.afo.bean.User getUser()
	{
		return user;
	}

	public synchronized boolean save()
	{
            try
		{
		if(exists())
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("update text set username=?, text=? where id=?");
			ps.setInt(1, user.getId());
			ps.setString(2, text);
			ps.setInt(3, id);
			return ps.execute();
		}
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("insert into text(username,text) values (?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, text);
			return ps.execute();
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public synchronized boolean exists()
	{
		if(id >= 0)
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select * from text where id=?");
				ps.setInt(1, id);
				return ps.executeQuery().next();
			}
			catch(java.sql.SQLException ex)
			{
				java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	public synchronized boolean delete()
	{
		if(exists())
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("delete from text where id=?");
				ps.setInt(1, id);
				return ps.executeUpdate() > 0;
			}
			catch(java.sql.SQLException ex)
			{
				java.util.logging.Logger.getLogger(Text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			return false;
		} else
		{
			return true;
		}
	}

	private org.macrobug.afo.bean.User user;
	private java.lang.String text;
}
