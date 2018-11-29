package main.ui;

public class Printer {

    static TextEntryBox entry;

    public Printer(TextEntryBox entry) {
        this.entry = entry;
    }

    public static void print(String s) {
        entry.setTextArea(s);
    }


}
