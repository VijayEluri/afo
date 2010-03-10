// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Image.java

package org.macrobug.afo.bean;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.macrobug.afo.MyDb;

// Referenced classes of package org.macrobug.afo.bean:
//			Bean

public class Image extends org.macrobug.afo.bean.Bean
{

	public synchronized boolean save()
	{
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("insert into img(a,b,c,d,e,f,g,h,i,j,k,l,m) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			for(int i = 1; i <= 13; i++)
			{
				ps.setString(i, getImgbyInt(i - 1));
			}

			return ps.executeUpdate() > 0;
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return false;
	}

	public Image(int img)
	{
		this.img = new java.lang.String[13];
		try
		{
			java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select * from img where id=?");
			ps.setInt(1, img);
			java.sql.ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				for(int i = 1; i < 14; i++)
				{
					setImg(i - 1, rs.getString(i + 1));
				}

				id = img;
			}
		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public static org.macrobug.afo.bean.Image[] getImages()
	{
		java.util.ArrayList ret = new ArrayList();
		java.util.ArrayList i = new ArrayList();
		try
		{
			org.macrobug.afo.MyDb d = org.macrobug.afo.MyDb.getDb();
			for(java.sql.ResultSet rs = d.getStatement().executeQuery("select id from img"); rs.next(); i.add(java.lang.Integer.valueOf(rs.getInt(1)))) { }
			java.lang.Integer t;
			for(java.util.Iterator i$ = i.iterator(); i$.hasNext(); ret.add(new Image(t.intValue())))
			{
				t = (java.lang.Integer)i$.next();
			}

		}
		catch(java.sql.SQLException ex)
		{
			java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return (org.macrobug.afo.bean.Image[])ret.toArray(new org.macrobug.afo.bean.Image[ret.size()]);
	}

	public static synchronized int count()
	{
		return org.macrobug.afo.bean.Image.getImages().length;
	}

	public java.lang.String getImgbyInt(int i)
	{
		return img[i];
	}

	public java.lang.String getImgbyChar(char i)
	{
		return getImgbyInt(i - 97);
	}

	public Image()
	{
		img = new java.lang.String[13];
		for(int i = 0; i < img.length; i++)
		{
			setImg(i, "");
		}

	}

	public Image(java.lang.String s[])
	{
		img = new java.lang.String[13];
		int i = 0;
		for(int j = 0; j < s.length;)
		{
			setImg(i, s[j]);
			j++;
			i++;
		}

		for(; i < img.length; i++)
		{
			setImg(i, "");
		}

	}

	public void setImg(int i, java.lang.String s)
	{
		img[i] = s;
	}

	public void setImg(char c, java.lang.String s)
	{
		setImg(c - 97, s);
	}

	public boolean exists()
	{
		if(id >= 0)
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("select * from img where id=?");
				ps.setInt(1, id);
				return ps.executeQuery().next();
			}
			catch(java.sql.SQLException ex)
			{
				java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	public boolean delete(java.lang.String path)
	{
		boolean b = true;
		for(int i = 0; i < img.length; i++)
		{
			b &= (new File(path, img[i])).delete();
		}

		return delete() && b;
	}

	public boolean delete()
	{
		if(exists())
		{
			try
			{
				java.sql.PreparedStatement ps = db.getConnection().prepareStatement("delete from img where id=?");
				ps.setInt(1, id);
				return ps.executeUpdate() > 0;
			}
			catch(java.sql.SQLException ex)
			{
				java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			return false;
		} else
		{
			return true;
		}
	}

	private java.lang.String img[];
}
