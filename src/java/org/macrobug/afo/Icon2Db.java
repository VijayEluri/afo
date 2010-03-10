// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   Icon2Db.java
package org.macrobug.afo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.macrobug.afo.bean.Icon;
import org.macrobug.afo.bean.User;

// Referenced classes of package org.macrobug.afo:
//			Image2Db, NameGen
public class Icon2Db {

    public Icon2Db(java.util.zip.ZipFile file, java.lang.String path, java.lang.String user) {
        zf = file;
        this.path = path;
        this.user = user;
        ng = new NameGen(path);
    }

    public static void main(java.lang.String args[]) {
        org.macrobug.afo.Image2Db m = new Image2Db("prova.zip", "image");
        java.lang.System.out.println(m.run());
    }
    private java.util.zip.ZipFile zf;
    private java.lang.String path;
    private java.lang.String user;
    private org.macrobug.afo.NameGen ng;

    public boolean run() {
        java.util.Enumeration enu;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        enu = zf.entries();
        do {
            java.io.InputStream is = null;
            try {
                java.util.zip.ZipEntry ze = (java.util.zip.ZipEntry) enu.nextElement();
                is = zf.getInputStream(ze);
                java.lang.String s = ng.getName("png");
                java.io.File f = new File(path, s);
                java.io.FileOutputStream fos = new FileOutputStream(f);
                for (int i = is.read(); i >= 0; i = is.read()) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                (new Icon(new User(user), s)).save();
                } // Misplaced declaration of an exception variable
            catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } finally {
                    try {
                        is.close();
                    } // Misplaced declaration of an exception variable
                    catch (java.io.IOException ex) {
                        java.util.logging.Logger.getLogger(Image2Db.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
        } while (!enu.hasMoreElements());
        return true;
    }
}
