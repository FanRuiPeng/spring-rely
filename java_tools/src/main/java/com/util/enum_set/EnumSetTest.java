package com.util.enum_set;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnumSetTest {

    public static void main(String[] args) {
//        EnumSet<Capt> off = EnumSet.of(Capt.off);
//        EnumSet<Capt> on = EnumSet.of(Capt.on);
//         on = EnumSet.complementOf(off);
//        Iterator<Capt> iterator = off.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//            System.out.println(off.size());
//        }

//        EnumMap<Capt, Capt.InnerCapt> innerCaptEnumMap = new EnumMap<>(Capt.class);
//        innerCaptEnumMap.put(Capt.on, Capt.InnerCapt.in);
//        EnumMap<Capt, Capt.InnerCapt> captInnerCaptEnumMap = new EnumMap<>(Capt.class);
//        captInnerCaptEnumMap.put(Capt.off, Capt.InnerCapt.out);
//        innerCaptEnumMap.putAll(captInnerCaptEnumMap);
//        System.out.println(innerCaptEnumMap.size());

        getAllJarUrls();
    }

    public static void getAllJarUrls() {
        try {
            final Enumeration<URL> mfUrls = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
            while (mfUrls.hasMoreElements()) {
                URL jarUrl = mfUrls.nextElement();
                if (!jarUrl.getProtocol().equals("jar")) {
                    continue;
                }
                try {
                    System.out.println(jarUrl.toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger("testcase").log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException e) {
            Logger.getLogger("testcase").log(Level.SEVERE, null, e);
        }
    }
}

enum Capt {
    on, off;

    public static enum InnerCapt {
        out(Capt.on, "台灯"),
        in(Capt.on, "li");
        private Capt values;
        private String desc;

        InnerCapt(Capt values, String desc) {
            this.values = values;
            this.desc = desc;
        }
    }
}
