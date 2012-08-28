package gestordeprocessos.util;

import java.util.Random;

public final class Cripto {

    private static final String[] hexDigits = {"ABCDEFGHIJKLMNOP", "RXUTKQWMHÇAECFBD", "YWABXMHQJPOLZTGR", "&#@!"};

    public String codifica(String str) {
        StringBuffer buf = new StringBuffer();
        Random random = new Random();
        int e = random.nextInt(3);
        byte[] b = str.getBytes();
        char c1 = hexDigits[3].charAt(e);
        char c2 = hexDigits[3].charAt(e + 1);
        buf.append(c2);
        //buf.append(c2);
        int cont = 0;
        for (int i = 0; i < b.length; i++) {
            int j = ((int) b[i]) & 0xFF;
            //System.out.println(j);
            buf.append("%");
            buf.append(hexDigits[e].charAt(random.nextInt(16)));
            buf.append(hexDigits[e].charAt(j / 16));
            buf.append(c1);
            buf.append(hexDigits[e].charAt(j % 16));
            if (cont == 2) {
                buf.append(c2);
                cont = 0;
            } else {
                cont++;
            }
        }
        return buf.toString();
    }

    public String decodifica(String var) {
        String c2 = var.substring(0, 1);
        int e = hexDigits[3].lastIndexOf(c2);
        e = e - 1;
        String c1 = String.valueOf(hexDigits[3].charAt(e));
//        StringBuffer buf = new StringBuffer();
        var = var.replaceAll(c2, "").replaceAll(c1, "");
        String[] str = var.split("%");
        String sD = "";
        for (int i = 0; i < str.length; i++) {
            if (i > 0) {
                String temp = str[i];
                int t1 = 0;
                int t2 = 0;
                for (int j = 0; j < temp.length(); j++) {
                    if (j == 1) {
                        t1 = hexDigits[e].lastIndexOf(temp.charAt(j)) * 16;
                    } else if (j == 2) {
                        t2 = hexDigits[e].lastIndexOf(temp.charAt(j));
                    }
                }
                sD += (char) (t1 + t2);
            }
        }
        return sD;
    }
}
