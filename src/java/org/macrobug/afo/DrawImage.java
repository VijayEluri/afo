package org.macrobug.afo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import org.macrobug.afo.bean.*;

public class DrawImage {

  public DrawImage(int l, int a, int id, String path) {
    hash = new Hashtable<String,BufferedImage>();
    buff = null;
    offX = 0;
    offY = 0;
    this.l = l;
    this.a = a;
    this.id = id;
    this.path = path;
  }

  private void run() {
    try {
      int w = 0;
      int h = 0;
      int mTimeX = 0;
      int mTimeY = 0;
      int iTime = 0;
      int jTime = 0;
      int kTime = 0;
      int lTime = 0;
      Image img = new Image(id);
      for (char i = 'a'; i <= 'm'; i++) {
        if (img.getImgbyChar(i).length() > 0) {
          hash.put((new StringBuilder()).append("").append(i).toString(), ImageIO.read(ImageIO.createImageInputStream(new File((new StringBuilder()).append(path).append("/").append(img.getImgbyChar(i)).toString()))));
        }
      }

      BufferedImage nill = new BufferedImage(1, 1, 6);
      int i = 0;
      for (int j = 97; i < 13; j++) {
        if (!hash.containsKey((new StringBuilder()).append("").append((char) j).toString())) {
          hash.put((new StringBuilder()).append("").append((char) j).toString(), nill);
        }
        i++;
      }

      mTimeX = (int) Math.ceil((1.0D * l) / hash.get("m").getWidth());
      mTimeY = (int) Math.ceil((1.0D * a) / hash.get("m").getHeight());
      int c = mcm(hash.get("m").getWidth(), mcm(hash.get("l").getWidth(), hash.get("i").getWidth()));
      int W = (int) (c * Math.ceil((1.0D * mTimeX * hash.get("m").getWidth()) / c));
      c = mcm(hash.get("m").getHeight(), mcm(hash.get("j").getHeight(), hash.get("k").getHeight()));
      int H = (int) (c * Math.ceil((1.0D * mTimeY * hash.get("m").getHeight()) / c));
      mTimeX = W / hash.get("m").getWidth();
      mTimeY = H / hash.get("m").getHeight();
      iTime = W / hash.get("i").getWidth();
      lTime = W / hash.get("l").getWidth();
      jTime = H / hash.get("j").getHeight();
      kTime = H / hash.get("k").getHeight();
      int oY = Math.max(Math.max(hash.get("e").getHeight(), hash.get("i").getHeight()), hash.get("f").getHeight());
      int oB = Math.max(Math.max(hash.get("g").getHeight(), hash.get("l").getHeight()), hash.get("h").getHeight());
      int oX = Math.max(Math.max(hash.get("e").getWidth(), hash.get("j").getWidth()), hash.get("g").getWidth());
      int oD = Math.max(Math.max(hash.get("f").getWidth(), hash.get("k").getWidth()), hash.get("h").getWidth());
      offY = oY + hash.get("a").getHeight();
      int offB = oB + hash.get("d").getHeight();
      offX = oX + hash.get("c").getWidth();
      int offD = oD + hash.get("b").getWidth();
      w = offD + offX + W;
      h = offY + offB + H;
      W = Math.min(w - hash.get("a").getWidth(), w - hash.get("d").getWidth()) / 2;
      H = Math.min(h - hash.get("c").getHeight(), h - hash.get("b").getHeight()) / 2;
      if (W < 0) {
        offX -= W;
        offD -= W - 1;
        w = Math.max(hash.get("a").getWidth(), hash.get("d").getWidth());
      }
      if (H < 0) {
        offY -= H;
        offB -= H - 1;
        h = Math.max(hash.get("c").getHeight(), hash.get("b").getHeight());
      }
      buff = new BufferedImage(w, h, 6);
      Graphics g = buff.getGraphics();
      g.drawImage(hash.get("e"), offX - hash.get("e").getWidth(), offY - hash.get("e").getHeight(), null);
      i = 0;
      for (int I = offX; i < iTime; I += hash.get("i").getWidth()) {
        g.drawImage(hash.get("i"), I, offY - hash.get("i").getHeight(), null);
        i++;
      }

      g.drawImage(hash.get("f"), w - offD, offY - hash.get("f").getHeight(), null);
      i = 0;
      for (int I = offY; i < jTime; I += hash.get("j").getHeight()) {
        g.drawImage(hash.get("j"), offX - hash.get("j").getWidth(), I, null);
        i++;
      }

      i = offX;
      for (int I = 0; I < mTimeX; I++) {
        int j = offY;
        for (int J = 0; J < mTimeY; J++) {
          g.drawImage(hash.get("m"), i, j, null);
          j += hash.get("m").getHeight();
        }

        i += hash.get("m").getWidth();
      }

      i = 0;
      for (int I = offY; i < kTime; I += hash.get("k").getHeight()) {
        g.drawImage(hash.get("k"), w - offD, I, null);
        i++;
      }

      g.drawImage(hash.get("g"), offX - hash.get("g").getWidth(), h - offB, null);
      i = 0;
      for (int I = offX; i < lTime; I += hash.get("l").getWidth()) {
        g.drawImage(hash.get("l"), I, h - offB, null);
        i++;
      }

      g.drawImage(hash.get("h"), w - offD, h - offB, null);
      g.drawImage(hash.get("a"), (w - hash.get("a").getWidth()) / 2, offY - hash.get("a").getHeight() - oY, null);
      g.drawImage(hash.get("c"), offX - hash.get("c").getWidth() - oX, (h - hash.get("c").getHeight()) / 2, null);
      g.drawImage(hash.get("b"), (w - offD) + oD, (h - hash.get("b").getHeight()) / 2, null);
      g.drawImage(hash.get("d"), (w - hash.get("d").getWidth()) / 2, (h - offB) + oB, null);
      buff.flush();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static void main(String s[]) {
    try {
      ArrayList a = new ArrayList();
      int max = 0;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      for (String temp = br.readLine(); !temp.equals("--end--"); temp = br.readLine()) {
        if (temp.length() > max) {
          max = temp.length();
        }
        a.add(temp);
      }

      int offXet = 14;
      DrawImage im = new DrawImage(max, a.size(), 1, "C:\\Documents and Settings\\All Users\\Documenti\\workspace\\Afo 2\\build\\web\\image");
      BufferedImage bi = im.getImage();
      Dimension d = im.getStart();
      Graphics2D g2d = bi.createGraphics();
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Dialog", 1, offXet));
      for (int i = 0; i < a.size(); i++) {
        g2d.drawString((String) a.get(i), d.width, d.height + (i + 1) * offXet);
      }

      bi.flush();
      ImageIO.write(im.getImage(), "png", new FileOutputStream("prova.png"));
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public BufferedImage getImage() {
    if (buff == null) {
      run();
    }
    return buff;
  }

  public Dimension getStart() {
    if (buff == null) {
      run();
    }
    return new Dimension(offX, offY);
  }

  private int mcm(int a, int b) {
    return (a * b) / MCD(a, b);
  }

  private int MCD(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return MCD(b, a % b);
    }
  }
  private Hashtable<String,BufferedImage> hash;
  private BufferedImage buff;
  private int l;
  private int a;
  private int id;
  private String path;
  private int offX;
  private int offY;
}
