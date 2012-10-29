package org.macrobug.afo.bean;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.macrobug.afo.*;
import org.macrobug.util.*;

public class User extends Bean
{

  public User(int id)
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select username,pwd,email,lvl from login where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
        name = rs.getString(1);
        pwd = rs.getString(2);
        email = rs.getString(3);
        level = rs.getInt(4);
        this.id = id;
      }
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public User(String name)
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select id,pwd,email,lvl from login where username=?");
      ps.setString(1, name);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
        id = rs.getInt(1);
        pwd = rs.getString(2);
        email = rs.getString(3);
        level = rs.getInt(4);
        this.name = name;
      }
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public User(String name, String pwd)
  {
    this.pwd = getHash(pwd);
    this.name = U2a.encode(name);
  }

  public User(String name, String pwd, String email)
  {
    this(name, pwd);
    this.email = U2a.encode(email);
  }

  public String getName()
  {
    return U2a.decode(name);
  }

  public int getLevel()
  {
    return level;
  }

  public String getEmail()
  {
    if(email != null)
    {
      return U2a.decode(email);
    } else
    {
      return "";
    }
  }

  public String getPassword()
  {
    return pwd;
  }

  private String getHash(String s)
  {
    double d = 0.0D;
    for(int i = 0; i < s.length(); i++)
    {
      d += Math.pow(s.charAt(i), 29D);
    }

    return Long.toHexString((long)(d % 16777215D));
  }

  @Override
  public synchronized boolean save()
  {
            try
    {
    if(exists())
    {
      PreparedStatement ps = db.getConnection().prepareStatement("update login set username=?, pwd=?, email=?, lvl=? where id=?");
      ps.setString(1, name);
      ps.setString(2, pwd);
      ps.setString(3, email);
      ps.setInt(4, level);
      ps.setInt(5, id);
      return ps.execute();
    }
      PreparedStatement ps = db.getConnection().prepareStatement("insert into login(username,pwd,email,lvl) values (?,?,?,?)");
      ps.setString(1, name);
      ps.setString(2, pwd);
      ps.setString(3, email);
      ps.setInt(4, level);
      return ps.execute();
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  @Override
  public synchronized boolean exists()
  {
    try
    {
      PreparedStatement ps = db.getConnection().prepareStatement("select * from logIn where userName=? and pwd=?");
      ps.setString(1, name);
      ps.setString(2, pwd);
      return ps.executeQuery().next();
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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
        PreparedStatement ps = db.getConnection().prepareStatement("delete from login where id=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
      }
      catch(SQLException ex)
      {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    } else
    {
      return true;
    }
  }

  public synchronized Text[] getTexts()
  {
    try
    {
      java.util.ArrayList a = new ArrayList();
      PreparedStatement ps;
      if(id > 0)
      {
        ps = db.getConnection().prepareStatement("select id,text from text where username=?");
        ps.setInt(1, id);
      } else
      {
        ps = db.getConnection().prepareStatement("select id,text from text");
      }
      for(ResultSet rs = ps.executeQuery(); rs.next(); a.add(new Text(rs.getInt(1), this, rs.getString(2)))) { }
      return (Text[])a.toArray(new Text[a.size()]);
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public synchronized Icon[] getIcons()
  {
    try
    {
      java.util.ArrayList a = new ArrayList();
      PreparedStatement ps;
      if(id > 0)
      {
        ps = db.getConnection().prepareStatement("select id,\"path\" from icon where username=?");
        ps.setInt(1, id);
      } else
      {
        ps = db.getConnection().prepareStatement("select id,\"path\" from icon");
      }
      for(ResultSet rs = ps.executeQuery(); rs.next(); a.add(new Icon(rs.getInt(1), this, rs.getString(2)))) { }
      return (Icon[])a.toArray(new Icon[a.size()]);
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public synchronized boolean setPassword(String niu)
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

  public synchronized boolean setMail(String mail)
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
      MyDb d = MyDb.getDb();
      ResultSet rs = d.getStatement().executeQuery("select count(id) from login");
      return rs.next() ? rs.getInt(1) : 0;
    }
    catch(SQLException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
  }

  public static synchronized ArrayList getAll()
  {
    ArrayList u = new ArrayList();
    MyDb d = MyDb.getDb();
    try
    {
      for(ResultSet rs = d.getStatement().executeQuery("select id from login "); rs.next(); u.add(new User(rs.getInt(1)))) { }
      return u;
    }
    catch(SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }

  public static void main(String argv[])
  {
    String s[] = {
      "@anfora@", "utopia0", "0utopia0"
    };
    for(int i = 0; i < s.length; i++)
    {
      User u = new User("fdas", s[i]);
      System.out.println((new StringBuilder()).append(s[i]).append(" => ").append(u.getHash(s[i])).toString());
    }

  }

  private String name;
  private String email;
  private String pwd;
  private int level;
  public static final User ALL = new User(0);

}
