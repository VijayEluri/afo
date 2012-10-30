package org.macrobug.afo.bean;

import org.macrobug.afo.*;

public abstract class Bean
{

  public abstract boolean save();

  public abstract boolean exists();

  public abstract boolean delete();

  public Bean()
  {
    db = MyDb.getDb();
    id = -1;
  }

  public int getId()
  {
    return id;
  }

  protected int id;
  protected MyDb db;
}
