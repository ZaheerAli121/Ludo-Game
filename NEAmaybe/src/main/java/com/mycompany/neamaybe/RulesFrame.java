
package com.mycompany.neamaybe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesFrame extends JFrame {

    public RulesFrame() {
        // Set frame properties
        setTitle("Game Rules");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(Color.CYAN);

        // Create and set layout for the content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Create and add rules text
        JTextArea rulesText = new JTextArea("The rules of this game are, you must get all four pieces into the center of your color's path, then you have won. Your die roll is based on how quickly you answer the question.\n\n" +
                "Answering correctly within 5 seconds rolls a six, within 6-10 rolls a five, 11-15 roll is 4, etc., until 26-30 where the roll is one. If you answer incorrectly, your roll will be zero.\n\n" +
                "Your roll times 10 is the score you obtain per roll, and winning awards 500 points.\n\n" +
                "When performing division, the answer is the whole number, no need to include the remainder.");
        rulesText.setEditable(false);
        rulesText.setWrapStyleWord(true);
        rulesText.setLineWrap(true);
        rulesText.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(rulesText);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Create and add close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the JFrame
            }
        });

        // Create panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Pack and set visible
        setVisible(true);
    }
}