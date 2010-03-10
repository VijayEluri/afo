// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   WriterOutputStream.java

package org.mortbay.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class WriterOutputStream extends java.io.OutputStream
{

	public WriterOutputStream(java.io.Writer writer, java.lang.String encoding)
	{
		_buf = new byte[1];
		_writer = writer;
		_encoding = encoding;
	}

	public WriterOutputStream(java.io.Writer writer)
	{
		_buf = new byte[1];
		_writer = writer;
	}

	public void close()
		throws java.io.IOException
	{
		_writer.close();
		_writer = null;
		_encoding = null;
	}

	public void flush()
		throws java.io.IOException
	{
		_writer.flush();
	}

	public void write(byte b[])
		throws java.io.IOException
	{
		if(_encoding == null)
		{
			_writer.write(new String(b));
		} else
		{
			_writer.write(new String(b, _encoding));
		}
	}

	public void write(byte b[], int off, int len)
		throws java.io.IOException
	{
		if(_encoding == null)
		{
			_writer.write(new String(b, off, len));
		} else
		{
			_writer.write(new String(b, off, len, _encoding));
		}
	}

	public synchronized void write(int b)
		throws java.io.IOException
	{
		_buf[0] = (byte)b;
		write(_buf);
	}

	protected java.io.Writer _writer;
	protected java.lang.String _encoding;
	private byte _buf[];
}
