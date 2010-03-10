// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Bean.java

package org.macrobug.afo.bean;

import org.macrobug.afo.MyDb;

public abstract class Bean
{

	public abstract boolean save();

	public abstract boolean exists();

	public abstract boolean delete();

	public Bean()
	{
		db = org.macrobug.afo.MyDb.getDb();
		id = -1;
	}

	public int getId()
	{
		return id;
	}

	protected int id;
	protected org.macrobug.afo.MyDb db;
}
