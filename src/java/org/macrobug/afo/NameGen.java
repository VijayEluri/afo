package org.macrobug.afo;

import java.io.*;

public class NameGen {

    public NameGen(String path) {
        c = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        this.path = path;
    }

    public String getName(final String ext) {
        String s[] = (new File(path)).list(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                if (!name.contains(".")) {
                    return false;
                } else {
                    return name.substring(name.lastIndexOf(".")).toLowerCase().contains(ext.toLowerCase());
                }
            }
        });
        String t = "";
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

    public static void main(String s[]) {
        org.macrobug.afo.NameGen ng = new NameGen(".");
        System.out.println(ng.getName("png"));
        System.out.println(ng.getName("txt"));
    }
    private String path;
    private char c[];
}
