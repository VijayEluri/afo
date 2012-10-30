package org.mortbay.util;

import java.io.*;

public class WriterOutputStream extends OutputStream
{

  public WriterOutputStream(Writer writer, String encoding)
  {
    _buf = new byte[1];
    _writer = writer;
    _encoding = encoding;
  }

  public WriterOutputStream(Writer writer)
  {
    _buf = new byte[1];
    _writer = writer;
  }

  @Override
  public void close()
    throws IOException
  {
    _writer.close();
    _writer = null;
    _encoding = null;
  }

  @Override
  public void flush()
    throws IOException
  {
    _writer.flush();
  }

  @Override
  public void write(byte b[])
    throws IOException
  {
    if(_encoding == null)
    {
      _writer.write(new String(b));
    } else
    {
      _writer.write(new String(b, _encoding));
    }
  }

  @Override
  public void write(byte b[], int off, int len)
    throws IOException
  {
    if(_encoding == null)
    {
      _writer.write(new String(b, off, len));
    } else
    {
      _writer.write(new String(b, off, len, _encoding));
    }
  }

  @Override
  public synchronized void write(int b)
    throws IOException
  {
    _buf[0] = (byte)b;
    write(_buf);
  }

  protected Writer _writer;
  protected String _encoding;
  private byte _buf[];
}
