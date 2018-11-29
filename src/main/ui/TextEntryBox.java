package main.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextEntryBox extends JFrame {

    private JTextField entry;
    private JPanel panel;
    private JLabel jLabel1;
    private JTextArea textArea;
    private JLabel status;
    private Reader r;


    public TextEntryBox() {
        super("FinancialPlanner");
        initComponents();
        setSize(500, 500);
        setVisible(true);
    }

    public void setTextArea(String s) {
        textArea.setText(s);
    }

    private void initComponents() {
        panel = new JPanel();
        entry = new JTextField();
        textArea = new JTextArea();
        status = new JLabel();
        jLabel1 = new JLabel();

        setResizable(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TextField");

        textArea.setColumns(30);
        textArea.setLineWrap(true);
        textArea.setRows(20);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);

        entry.setColumns(30);

        panel.add(entry);
        panel.add(textArea);
        panel.add(status);
        panel.add(jLabel1);


        JButton btn = new JButton("Enter");
        btn.setActionCommand("myButton");


        entry.add(btn);

        panel.add(btn);

        add(panel);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(entry.getText());
                r.read(entry.getText());
                entry.setText("");
            }
        });
    }
}