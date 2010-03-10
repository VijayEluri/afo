// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames braces deadcode 
// Source File Name:   NameGen.java
package org.macrobug.afo;

import java.io.File;

public class NameGen {

    public NameGen(java.lang.String path) {
        c = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        this.path = path;
    }

    public java.lang.String getName(final java.lang.String ext) {
        java.lang.String s[] = (new File(path)).list(new java.io.FilenameFilter() {

            public boolean accept(java.io.File dir, java.lang.String name) {
                if (!name.contains(".")) {
                    return false;
                } else {
                    return name.substring(name.lastIndexOf(".")).toLowerCase().contains(ext.toLowerCase());
                }
            }
        });
        java.lang.String t = "";
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                for (int k = 0; k < c.length; k++) {
                    for (int ì = 0; ì < c.length; ì++) {
                        for (int ç = 0; ç < c.length; ç++) {
                            for (int _i = 0; _i < c.length; _i++) {
                                t = new StringBuilder().append("").append(c[i]).append(c[j]).append(c[k]).append(c[ì]).append(c[ç]).append(c[_i]).toString();
                                int h;
                                for (h = 0; h < s.length && !s[h].contains(t); h++);
                                if (h == s.length) {
                                    return (new StringBuilder()).append(t).append(ext.contains(".") ? ext : (new StringBuilder()).append('.').append(ext).toString()).toString();
                                }
                            }

                        }

                    }

                }

            }

        }

        return null;
    }

    public static void main(java.lang.String s[]) {
        org.macrobug.afo.NameGen ng = new NameGen(".");
        java.lang.System.out.println(ng.getName("png"));
        java.lang.System.out.println(ng.getName("txt"));
    }
    private java.lang.String path;
    private char c[];
}
