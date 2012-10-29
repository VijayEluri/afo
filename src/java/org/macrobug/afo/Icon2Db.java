package org.macrobug.afo;

import java.io.*;
import java.util.logging.*;
import java.util.zip.*;
import org.macrobug.afo.bean.*;

public class Icon2Db {

    public Icon2Db(ZipFile file, String path, String user) {
        zf = file;
        this.path = path;
        this.user = user;
        ng = new NameGen(path);
    }

    public static void main(String args[]) {
        Image2Db m = new Image2Db("prova.zip", "image");
        System.out.println(m.run());
    }
    private ZipFile zf;
    private String path;
    private String user;
    private NameGen ng;

    public boolean run() {
        java.util.Enumeration enu;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        enu = zf.entries();
        do {
            InputStream is = null;
            try {
                ZipEntry ze = (ZipEntry) enu.nextElement();
                is = zf.getInputStream(ze);
                String s = ng.getName("png");
                File f = new File(path, s);
                FileOutputStream fos = new FileOutputStream(f);
                for (int i = is.read(); i >= 0; i = is.read()) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                (new Icon(new User(user), s)).save();
                } // Misplaced declaration of an exception variable
            catch (IOException ex) {
                    Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        is.close();
                    } // Misplaced declaration of an exception variable
                    catch (IOException ex) {
                        Logger.getLogger(Image2Db.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        } while (!enu.hasMoreElements());
        return true;
    }
}
