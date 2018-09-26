package ru.efremovdm.lesson4;

import javax.swing.*;
import java.awt.*;

/**
 * Корневой фрейм-окно
 */
class Frame extends JFrame {

    Frame() {

        setTitle("Чат");
        setSize(new Dimension(500, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MessagePanel messagePanel = new MessagePanel();
        SendPanel sendPanel = new SendPanel(messagePanel.getTextArea());

        add(messagePanel, BorderLayout.CENTER);
        add(sendPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}