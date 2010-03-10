// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   User.java

package org.macrobug.afo.bean;

import java.util.ArrayList;
import java.util.Vector;

// Referenced classes of package org.macrobug.afo.bean:
//			Bean, Text, Icon

public class User extends org.macrobug.afo.bean.Bean
{

	public User(int id)
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select username,pwd,email,lvl from login where id=?");
			ps.setInt(1, id);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				name = rs.getString(1);
				pwd = rs.getString(2);
				email = rs.getString(3);
				level = rs.getInt(4);
				this.id = id;
			}
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public User(java.lang.String name)
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select id,pwd,email,lvl from login where username=?");
			ps.setString(1, name);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				id = rs.getInt(1);
				pwd = rs.getString(2);
				email = rs.getString(3);
				level = rs.getInt(4);
				this.name = name;
			}
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public User(java.lang.String name, java.lang.String pwd)
	{
		this.pwd = getHash(pwd);
		this.name = org.macrobug.util.U2a.encode(name);
	}

	public User(java.lang.String name, java.lang.String pwd, java.lang.String email)
	{
		this(name, pwd);
		this.email = org.macrobug.util.U2a.encode(email);
	}

	public java.lang.String getName()
	{
		return org.macrobug.util.U2a.decode(name);
	}

	public int getLevel()
	{
		return level;
	}

	public java.lang.String getEmail()
	{
		if(email != null)
		{
			return org.macrobug.util.U2a.decode(email);
		} else
		{
			return "";
		}
	}

	public java.lang.String getPassword()
	{
		return pwd;
	}

	private java.lang.String getHash(java.lang.String s)
	{
		double d = 0.0D;
		for(int i = 0; i < s.length(); i++)
		{
			d += java.lang.Math.pow(s.charAt(i), 29D);
		}

		return java.lang.Long.toHexString((long)(d % 16777215D));
	}

	public synchronized boolean save()
	{
            try
		{
		if(exists())
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("update login set username=?, pwd=?, email=?, lvl=? where id=?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.setString(3, email);
			ps.setInt(4, level);
			ps.setInt(5, id);
			return ps.execute();
		}
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("insert into login(username,pwd,email,lvl) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.setString(3, email);
			ps.setInt(4, level);
			return ps.execute();
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public synchronized boolean exists()
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select * from logIn where userName=? and pwd=?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			return ps.executeQuery().next();
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public synchronized boolean delete()
	{
		if(exists())
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("delete from login where id=?");
				ps.setInt(1, id);
				return ps.executeUpdate() > 0;
			}
			catch(java.sql.SQLException ex)
			{
				java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			return false;
		} else
		{
			return true;
		}
	}

	public synchronized org.macrobug.afo.bean.Text[] getTexts()
	{
		try
		{
			java.util.ArrayList a = new ArrayList();
			java.sql.PreparedStatement ps;
			if(id > 0)
			{
				ps = db.getConnection().prepareStatement("select id,text from text where username=?");
				ps.setInt(1, id);
			} else
			{
				ps = db.getConnection().prepareStatement("select id,text from text");
			}
			for(java.sql.ResultSet rs = ps.executeQuery(); rs.next(); a.add(new Text(rs.getInt(1), this, rs.getString(2)))) { }
			return (org.macrobug.afo.bean.Text[])a.toArray(new org.macrobug.afo.bean.Text[a.size()]);
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return null;
	}

	public synchronized org.macrobug.afo.bean.Icon[] getIcons()
	{
		try
		{
			java.util.ArrayList a = new ArrayList();
			java.sql.PreparedStatement ps;
			if(id > 0)
			{
				ps = db.getConnection().prepareStatement("select id,\"path\" from icon where username=?");
				ps.setInt(1, id);
			} else
			{
				ps = db.getConnection().prepareStatement("select id,\"path\" from icon");
			}
			for(java.sql.ResultSet rs = ps.executeQuery(); rs.next(); a.add(new Icon(rs.getInt(1), this, rs.getString(2)))) { }
			return (org.macrobug.afo.bean.Icon[])a.toArray(new org.macrobug.afo.bean.Icon[a.size()]);
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return null;
	}

	public synchronized boolean setPassword(java.lang.String niu)
	{
		if(exists())
		{
			pwd = niu;
			return save();
		} else
		{
			return false;
		}
	}

	public synchronized boolean setPassword(int i)
	{
		if(exists())
		{
			level = i;
			return save();
		} else
		{
			return false;
		}
	}

	public synchronized boolean setMail(java.lang.String mail)
	{
		if(exists())
		{
			email = mail;
			return save();
		} else
		{
			return false;
		}
	}

	public static synchronized int count()
	{
		try
		{
			org.macrobug.afo.MyDb d = org.macrobug.afo.MyDb.getDb();
			java.sql.ResultSet rs = d.getStatement().executeQuery("select count(id) from login");
			return rs.next() ? rs.getInt(1) : 0;
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return 0;
	}

	public static synchronized java.util.Vector getAll()
	{
		java.util.Vector u = new Vector();
		org.macrobug.afo.MyDb d = org.macrobug.afo.MyDb.getDb();
		try
		{
			for(java.sql.ResultSet rs = d.getStatement().executeQuery("select id from login "); rs.next(); u.add(new User(rs.getInt(1)))) { }
			return u;
		}
		catch(java.sql.SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(java.lang.String argv[])
	{
		java.lang.String s[] = {
			"@anfora@", "utopia0", "0utopia0"
		};
		for(int i = 0; i < s.length; i++)
		{
			org.macrobug.afo.bean.User u = new User("fdas", s[i]);
			java.lang.System.out.println((new StringBuilder()).append(s[i]).append(" => ").append(u.getHash(s[i])).toString());
		}

	}

	private java.lang.String name;
	private java.lang.String email;
	private java.lang.String pwd;
	private int level;
	public static final org.macrobug.afo.bean.User ALL = new User(0);

}
