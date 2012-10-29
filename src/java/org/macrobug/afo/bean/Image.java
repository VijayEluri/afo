package org.macrobug.afo.bean;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.macrobug.afo.MyDb;

public class Image extends Bean
{

  public synchronized boolean save()
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("insert into img(a,b,c,d,e,f,g,h,i,j,k,l,m) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
      for(int i = 1; i <= 13; i++)
      {
        ps.setString(i, getImgbyInt(i - 1));
      }

      return ps.executeUpdate() > 0;
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public Image(int img)
  {
    this.img = new String[13];
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select * from img where id=?");
      ps.setInt(1, img);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
        for(int i = 1; i < 14; i++)
        {
          setImg(i - 1, rs.getString(i + 1));
        }

        id = img;
      }
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Image[] getImages()
  {
    ArrayList ret = new ArrayList();
    ArrayList i = new ArrayList();
    try
    {
      org.macrobug.afo.MyDb d = org.macrobug.afo.MyDb.getDb();
      for(ResultSet rs = d.getStatement().executeQuery("select id from img"); rs.next(); i.add(Integer.valueOf(rs.getInt(1)))) { }
      Integer t;
      for(Iterator i$ = i.iterator(); i$.hasNext(); ret.add(new Image(t.intValue())))
      {
        t = (Integer)i$.next();
      }

    }
    catch(SQLException ex)
    {
      Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
    }
    return (Image[])ret.toArray(new Image[ret.size()]);
  }

  public static synchronized int count()
  {
    return Image.getImages().length;
  }

  public String getImgbyInt(int i)
  {
    return img[i];
  }

  public String getImgbyChar(char i)
  {
    return getImgbyInt(i - 97);
  }

  public Image()
  {
    img = new String[13];
    for(int i = 0; i < img.length; i++)
    {
      setImg(i, "");
    }

  }

  public Image(String s[])
  {
    img = new String[13];
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

  private void setImg(int i, String s)
  {
    img[i] = s;
  }

  public void setImg(char c, String s)
  {
    setImg(c - 97, s);
  }

  @Override
  public boolean exists()
  {
    if(id >= 0)
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("select * from img where id=?");
        ps.setInt(1, id);
        return ps.executeQuery().next();
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return false;
  }

  public boolean delete(String path)
  {
    boolean b = true;
    for(int i = 0; i < img.length; i++)
    {
      b &= (new File(path, img[i])).delete();
    }

    return delete() && b;
  }

  @Override
  public boolean delete()
  {
    if(exists())
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("delete from img where id=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    } else
    {
      return true;
    }
  }

  private String img[];
}
