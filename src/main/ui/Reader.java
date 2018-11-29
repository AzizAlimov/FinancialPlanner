package main.ui;

import javax.xml.soap.Text;

public class Reader {

    private static String read1;

    public Reader () {
    }

    public static void read(String s) {
        read1 = s;
    }

    public String readout() {
        return read1;
    }
}
