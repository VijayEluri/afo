package org.macrobug.afo.bean;

import java.sql.*;
import java.util.logging.*;
import org.macrobug.util.*;

public class Text extends Bean
{

  public Text(int id)
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select username,text from text where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
        text = rs.getString(2);
        user = new User(rs.getInt(1));
        this.id = id;
      }
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Text(int id, User user, String text)
  {
    this.user = user;
    this.text = text;
    this.id = id;
  }

  public Text(User user, String text)
  {
    this.user = user;
    this.text = U2a.encode(text);
  }

  public String getText()
  {
    return text;
  }

  public User getUser()
  {
    return user;
  }

  @Override
  public synchronized boolean save()
  {
            try
    {
    if(exists())
    {
      PreparedStatement ps = db.getConnection().prepareStatement("update text set username=?, text=? where id=?");
      ps.setInt(1, user.getId());
      ps.setString(2, text);
      ps.setInt(3, id);
      return ps.execute();
    }
      PreparedStatement ps = db.getConnection().prepareStatement("insert into text(username,text) values (?,?)");
      ps.setInt(1, user.getId());
      ps.setString(2, text);
      return ps.execute();
    }
    catch(SQLException ex)
    {
      Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  @Override
  public synchronized boolean exists()
  {
    if(id >= 0)
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("select * from text where id=?");
        ps.setInt(1, id);
        return ps.executeQuery().next();
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return false;
  }

  @Override
  public synchronized boolean delete()
  {
    if(exists())
    {
      try
      {
        PreparedStatement ps = db.getConnection().prepareStatement("delete from text where id=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
      }
      catch(SQLException ex)
      {
        Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    } else
    {
      return true;
    }
  }

  private User user;
  private String text;
}
