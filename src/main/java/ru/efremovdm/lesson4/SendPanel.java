package ru.efremovdm.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Панель отправки сообщений
 */
class SendPanel extends JPanel {

    private JTextField textField;
    private JTextArea textArea;
    private String br;
    private Date date;
    private final SimpleDateFormat dateFormat;

    SendPanel(JTextArea textArea) {

        this.textArea = textArea;

        textField = new JTextField() {
            @Override
            public void addNotify() {
                super.addNotify();
                requestFocus();
            }
        };

        br = "";
        dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        setLayout(new BorderLayout());
        JButton btn = new JButton("Отправить");

        btn.addActionListener(new SendBtnListener());
        textField.addActionListener(new SendBtnListener());

        add(textField, BorderLayout.CENTER); //awt
        add(btn, BorderLayout.EAST); //awt
    }

    /**
     * Формирование даты и времени
     * @return
     */
    private String getDateTime() {
        date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Формирование сообщения
     */
    private void append() {

        String text = textField.getText();

        if (!text.isEmpty()) {
            textArea.setText(String.format("%s%s[%s] %s", textArea.getText(), br, getDateTime(), text));
            textField.setText("");
            br = "\n";
        }
    }

    /**
     * Листенер событий кнопки и текстового поля
     */
    private class SendBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // отправка по нажатию на JButton
            if (e.getSource() instanceof JButton) {
                append();
            }

            // отправка по Enter
            if (e.getSource() instanceof JTextField) {
                append();
            }
        }
    }
}