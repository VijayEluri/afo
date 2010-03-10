// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   DrawImage.java

package org.macrobug.afo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import org.macrobug.afo.bean.Image;

public class DrawImage
{

	public DrawImage(int l, int a, int id, java.lang.String path)
	{
		hash = new Hashtable();
		buff = null;
		offX = 0;
		offY = 0;
		this.l = l;
		this.a = a;
		this.id = id;
		this.path = path;
	}

	private void run()
	{
		try
		{
			int w = 0;
			int h = 0;
			int mTimeX = 0;
			int mTimeY = 0;
			int iTime = 0;
			int jTime = 0;
			int kTime = 0;
			int lTime = 0;
			org.macrobug.afo.bean.Image img = new Image(id);
			for(char i = 'a'; i <= 'm'; i++)
			{
				if(img.getImgbyChar(i).length() > 0)
				{
					hash.put((new StringBuilder()).append("").append(i).toString(), javax.imageio.ImageIO.read(javax.imageio.ImageIO.createImageInputStream(new File((new StringBuilder()).append(path).append("/").append(img.getImgbyChar(i)).toString()))));
				}
			}

			java.awt.image.BufferedImage nill = new BufferedImage(1, 1, 6);
			int i = 0;
			for(int j = 97; i < 13; j++)
			{
				if(!hash.containsKey((new StringBuilder()).append("").append((char)j).toString()))
				{
					hash.put((new StringBuilder()).append("").append((char)j).toString(), nill);
				}
				i++;
			}

			mTimeX = (int)java.lang.Math.ceil((1.0D * (double)l) / (double)((java.awt.image.BufferedImage)hash.get("m")).getWidth());
			mTimeY = (int)java.lang.Math.ceil((1.0D * (double)a) / (double)((java.awt.image.BufferedImage)hash.get("m")).getHeight());
			int c = mcm(((java.awt.image.BufferedImage)hash.get("m")).getWidth(), mcm(((java.awt.image.BufferedImage)hash.get("l")).getWidth(), ((java.awt.image.BufferedImage)hash.get("i")).getWidth()));
			int W = (int)((double)c * java.lang.Math.ceil((1.0D * (double)mTimeX * (double)((java.awt.image.BufferedImage)hash.get("m")).getWidth()) / (double)c));
			c = mcm(((java.awt.image.BufferedImage)hash.get("m")).getHeight(), mcm(((java.awt.image.BufferedImage)hash.get("j")).getHeight(), ((java.awt.image.BufferedImage)hash.get("k")).getHeight()));
			int H = (int)((double)c * java.lang.Math.ceil((1.0D * (double)mTimeY * (double)((java.awt.image.BufferedImage)hash.get("m")).getHeight()) / (double)c));
			mTimeX = W / ((java.awt.image.BufferedImage)hash.get("m")).getWidth();
			mTimeY = H / ((java.awt.image.BufferedImage)hash.get("m")).getHeight();
			iTime = W / ((java.awt.image.BufferedImage)hash.get("i")).getWidth();
			lTime = W / ((java.awt.image.BufferedImage)hash.get("l")).getWidth();
			jTime = H / ((java.awt.image.BufferedImage)hash.get("j")).getHeight();
			kTime = H / ((java.awt.image.BufferedImage)hash.get("k")).getHeight();
			int oY = java.lang.Math.max(java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("e")).getHeight(), ((java.awt.image.BufferedImage)hash.get("i")).getHeight()), ((java.awt.image.BufferedImage)hash.get("f")).getHeight());
			int oB = java.lang.Math.max(java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("g")).getHeight(), ((java.awt.image.BufferedImage)hash.get("l")).getHeight()), ((java.awt.image.BufferedImage)hash.get("h")).getHeight());
			int oX = java.lang.Math.max(java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("e")).getWidth(), ((java.awt.image.BufferedImage)hash.get("j")).getWidth()), ((java.awt.image.BufferedImage)hash.get("g")).getWidth());
			int oD = java.lang.Math.max(java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("f")).getWidth(), ((java.awt.image.BufferedImage)hash.get("k")).getWidth()), ((java.awt.image.BufferedImage)hash.get("h")).getWidth());
			offY = oY + ((java.awt.image.BufferedImage)hash.get("a")).getHeight();
			int offB = oB + ((java.awt.image.BufferedImage)hash.get("d")).getHeight();
			offX = oX + ((java.awt.image.BufferedImage)hash.get("c")).getWidth();
			int offD = oD + ((java.awt.image.BufferedImage)hash.get("b")).getWidth();
			w = offD + offX + W;
			h = offY + offB + H;
			W = java.lang.Math.min(w - ((java.awt.image.BufferedImage)hash.get("a")).getWidth(), w - ((java.awt.image.BufferedImage)hash.get("d")).getWidth()) / 2;
			H = java.lang.Math.min(h - ((java.awt.image.BufferedImage)hash.get("c")).getHeight(), h - ((java.awt.image.BufferedImage)hash.get("b")).getHeight()) / 2;
			if(W < 0)
			{
				offX -= W;
				offD -= W - 1;
				w = java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("a")).getWidth(), ((java.awt.image.BufferedImage)hash.get("d")).getWidth());
			}
			if(H < 0)
			{
				offY -= H;
				offB -= H - 1;
				h = java.lang.Math.max(((java.awt.image.BufferedImage)hash.get("c")).getHeight(), ((java.awt.image.BufferedImage)hash.get("b")).getHeight());
			}
			buff = new BufferedImage(w, h, 6);
			java.awt.Graphics g = buff.getGraphics();
			g.drawImage((java.awt.Image)hash.get("e"), offX - ((java.awt.image.BufferedImage)hash.get("e")).getWidth(), offY - ((java.awt.image.BufferedImage)hash.get("e")).getHeight(), null);
			i = 0;
			for(int I = offX; i < iTime; I += ((java.awt.image.BufferedImage)hash.get("i")).getWidth())
			{
				g.drawImage((java.awt.Image)hash.get("i"), I, offY - ((java.awt.image.BufferedImage)hash.get("i")).getHeight(), null);
				i++;
			}

			g.drawImage((java.awt.Image)hash.get("f"), w - offD, offY - ((java.awt.image.BufferedImage)hash.get("f")).getHeight(), null);
			i = 0;
			for(int I = offY; i < jTime; I += ((java.awt.image.BufferedImage)hash.get("j")).getHeight())
			{
				g.drawImage((java.awt.Image)hash.get("j"), offX - ((java.awt.image.BufferedImage)hash.get("j")).getWidth(), I, null);
				i++;
			}

			i = offX;
			for(int I = 0; I < mTimeX; I++)
			{
				int j = offY;
				for(int J = 0; J < mTimeY; J++)
				{
					g.drawImage((java.awt.Image)hash.get("m"), i, j, null);
					j += ((java.awt.image.BufferedImage)hash.get("m")).getHeight();
				}

				i += ((java.awt.image.BufferedImage)hash.get("m")).getWidth();
			}

			i = 0;
			for(int I = offY; i < kTime; I += ((java.awt.image.BufferedImage)hash.get("k")).getHeight())
			{
				g.drawImage((java.awt.Image)hash.get("k"), w - offD, I, null);
				i++;
			}

			g.drawImage((java.awt.Image)hash.get("g"), offX - ((java.awt.image.BufferedImage)hash.get("g")).getWidth(), h - offB, null);
			i = 0;
			for(int I = offX; i < lTime; I += ((java.awt.image.BufferedImage)hash.get("l")).getWidth())
			{
				g.drawImage((java.awt.Image)hash.get("l"), I, h - offB, null);
				i++;
			}

			g.drawImage((java.awt.Image)hash.get("h"), w - offD, h - offB, null);
			g.drawImage((java.awt.Image)hash.get("a"), (w - ((java.awt.image.BufferedImage)hash.get("a")).getWidth()) / 2, offY - ((java.awt.image.BufferedImage)hash.get("a")).getHeight() - oY, null);
			g.drawImage((java.awt.Image)hash.get("c"), offX - ((java.awt.image.BufferedImage)hash.get("c")).getWidth() - oX, (h - ((java.awt.image.BufferedImage)hash.get("c")).getHeight()) / 2, null);
			g.drawImage((java.awt.Image)hash.get("b"), (w - offD) + oD, (h - ((java.awt.image.BufferedImage)hash.get("b")).getHeight()) / 2, null);
			g.drawImage((java.awt.Image)hash.get("d"), (w - ((java.awt.image.BufferedImage)hash.get("d")).getWidth()) / 2, (h - offB) + oB, null);
			buff.flush();
		}
		catch(java.io.IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public static void main(java.lang.String s[])
	{
		try
		{
			java.util.ArrayList a = new ArrayList();
			int max = 0;
			java.io.BufferedReader br = new BufferedReader(new InputStreamReader(java.lang.System.in));
			for(java.lang.String temp = br.readLine(); !temp.equals("--end--"); temp = br.readLine())
			{
				if(temp.length() > max)
				{
					max = temp.length();
				}
				a.add(temp);
			}

			int offXet = 14;
			org.macrobug.afo.DrawImage im = new DrawImage(max, a.size(), 1, "C:\\Documents and Settings\\All Users\\Documenti\\workspace\\Afo 2\\build\\web\\image");
			java.awt.image.BufferedImage bi = im.getImage();
			java.awt.Dimension d = im.getStart();
			java.awt.Graphics2D g2d = bi.createGraphics();
			g2d.setColor(java.awt.Color.BLACK);
			g2d.setFont(new Font("Dialog", 1, offXet));
			for(int i = 0; i < a.size(); i++)
			{
				g2d.drawString((java.lang.String)a.get(i), d.width, d.height + (i + 1) * offXet);
			}

			bi.flush();
			javax.imageio.ImageIO.write(im.getImage(), "png", new FileOutputStream("prova.png"));
		}
		catch(java.io.FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(java.io.IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public java.awt.image.BufferedImage getImage()
	{
		if(buff == null)
		{
			run();
		}
		return buff;
	}

	public java.awt.Dimension getStart()
	{
		if(buff == null)
		{
			run();
		}
		return new Dimension(offX, offY);
	}

	private int mcm(int a, int b)
	{
		return (a * b) / MCD(a, b);
	}

	private int MCD(int a, int b)
	{
		if(b == 0)
		{
			return a;
		} else
		{
			return MCD(b, a % b);
		}
	}

	private java.util.Hashtable hash;
	private java.awt.image.BufferedImage buff;
	private int l;
	private int a;
	private int id;
	private java.lang.String path;
	private int offX;
	private int offY;
}
