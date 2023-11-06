package com.example.akranoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {
    private JFrame frame;

    public StartMenu(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.BLACK);
        setLayout(null);

        // "ARKANOID"
        JLabel titleLabel = new JLabel("<html><font color='#FF0000'>A</font><font color='#FF8800'>R</font><font color='#FFFF00'>K</font><font color='#88FF00'>A</font><font color='#00FF00'>N</font><font color='#00FF88'>O</font><font color='#00FFFF'>I</font><font color='#0088FF'>D</font></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(20, 80, 460, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.TYPE1_FONT, 13));
        startButton.setBounds(200, 200, 110, 40);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.RED);
        startButton.setFocusPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(StartMenu.this);
                ArkanoidGame game = new ArkanoidGame();
                frame.add(game);
                frame.revalidate();
                frame.repaint();
                game.requestFocusInWindow();
            }
        });
        add(startButton);
    }
}
