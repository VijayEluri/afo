package org.macrobug.afo;

import java.sql.*;
import java.util.logging.*;
import java.util.zip.*;

public class MyDb extends org.macrobug.util.Dibi
{

  private MyDb()
  {
    super("/home/hostingjava.it/SaThot/WEB-INF/db/", "afo", "hosting", "");
  }

  public static MyDb getDb()
  {
    try
    {
      if(db == null || db.getConnection() != null && db.getConnection().isClosed())
      {
        db = new MyDb();
      }
    }
    catch(SQLException ex)
    {
      ex.printStackTrace();
    }
    return db;
  }

  @Override
  public Statement getStatement()
  {
    return super.getStatement();
  }

  @Override
  public Connection getConnection()
  {
    return super.getConnection();
  }

  @Override
  protected synchronized void reset()
  {
    try
    {
      super.reset();
      ZipFile fs = new ZipFile("/home/hostingjava.it/SaThot/WEB-INF/db/image.zip");
      if(fs != null)
      {
        Image2Db.execute(fs, "/home/hostingjava.it/SaThot/image");
      }
      fs = new ZipFile("/home/hostingjava.it/SaThot/WEB-INF/db/icon.zip");
      if(fs != null)
      {
        (new Icon2Db(fs, "/home/hostingjava.it/SaThot/icon", "SaDjehwty")).run();
      }
    }
    catch(java.io.IOException ex)
    {
      Logger.getLogger(MyDb.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static final String path = "/home/hostingjava.it/SaThot/WEB-INF/db/";
  private static MyDb db = null;

}
