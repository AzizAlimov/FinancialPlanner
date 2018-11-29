package main.ui;

import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reader {

    private static String read1;
    private final TextEntryBox box;

    public Reader (TextEntryBox box) {
        this.box = box;
    }

    public String read() {
        return box.getInput();
    }

    public String readout() {
        return read1;
    }
}
