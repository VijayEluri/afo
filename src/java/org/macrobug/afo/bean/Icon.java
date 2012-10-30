package org.macrobug.afo.bean;

import java.io.File;
import java.sql.*;
import java.util.logging.*;
import org.macrobug.afo.*;

public class Icon extends Bean
{

  @Override
  public synchronized boolean save()
  {
            try
    {
    if(exists())
    {
      PreparedStatement ps = db.getConnection().prepareStatement("update icon set username=?, \"path\"=? where id=?");
      ps.setInt(1, user.getId());
      ps.setString(2, path);
      ps.setInt(3, id);
      return ps.execute();
    }
      PreparedStatement ps = db.getConnection().prepareStatement("insert into icon(username,\"path\") values (?,?)");
      ps.setInt(1, user.getId());
      ps.setString(2, path);
      return ps.execute();
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Icon.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  @Override
  public boolean exists()
  {
    if(id >= 0)
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("select * from icon where id=?");
        ps.setInt(1, id);
        return ps.executeQuery().next();
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Icon.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return false;
  }

  public String getPath()
  {
    return path;
  }

  public User getUser()
  {
    return user;
  }

  public Icon(int id)
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select username,\"path\" from icon where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
        path = rs.getString(2);
        this.id = id;
        user = new User(rs.getInt(1));
      }
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Icon.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Icon(int id, User user, String path)
  {
    this(user, path);
    this.id = id;
  }

  public Icon(User user, String path)
  {
    this.user = user;
    this.path = path;
  }

  public boolean delete(String path)
  {
    return delete() && (new File(path, this.path)).delete();
  }

  @Override
  public boolean delete()
  {
    if(exists())
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("delete from icon where id=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Icon.class.getName()).log(Level.SEVERE, null, ex);
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
      MyDb d = MyDb.getDb();
      ResultSet rs = d.getStatement().executeQuery("select count(id) from icon");
      return rs.next() ? rs.getInt(1) : 0;
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Icon.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
  }

  private User user;
  private String path;
}
