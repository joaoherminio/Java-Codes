package main;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TranslatorGUI extends JFrame implements KeyListener {

    private Controller controller;
    private JTextArea textInputArea, morseCodeArea;

    public TranslatorGUI() {

        super("Tradutor de Código Morse");

        setSize(540, 760);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.decode("#264653"));
        setLocationRelativeTo(null);

        controller = new Controller();
        addGuiComponents();
    }

    private void addGuiComponents() {

        JLabel titleLabel = new JLabel("Tradutor de Código Morse");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 540, 100);

        JLabel textInputLabel = new JLabel("Texto:");
        textInputLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        textInputLabel.setForeground(Color.WHITE);
        textInputLabel.setBounds(20, 100, 200, 30);

        textInputArea = new JTextArea();
        textInputArea.setFont(new Font("Dialog", Font.PLAIN, 18));
        textInputArea.addKeyListener(this);
        textInputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textInputArea.setLineWrap(true);
        textInputArea.setWrapStyleWord(true);

        JScrollPane textInputScroll = new JScrollPane(textInputArea);
        textInputScroll.setBounds(20, 132, 484, 236);

        morseCodeArea = new JTextArea();
        morseCodeArea.setFont(new Font("Dialog", Font.PLAIN, 18));
        morseCodeArea.setEditable(false);
        morseCodeArea.setLineWrap(true);
        morseCodeArea.setWrapStyleWord(true);
        morseCodeArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel morseCodeInputLabel = new JLabel("Código Morse:");
        morseCodeInputLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        morseCodeInputLabel.setForeground(Color.WHITE);
        morseCodeInputLabel.setBounds(20, 390, 200, 30);

        JScrollPane morseCodeScroll = new JScrollPane(morseCodeArea);
        morseCodeScroll.setBounds(20, 430, 484, 236);

        JButton playSoundButton = new JButton("Tocar som");
        playSoundButton.setBounds(210, 680, 120, 30);

        playSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                playSoundButton.setEnabled(false);

                Thread playMorseCodeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String[] morseCodeMessage =
                                    morseCodeArea.getText().split(" ");

                            controller.playSound(morseCodeMessage);

                        } catch (javax.sound.sampled.LineUnavailableException ex) {
                            ex.printStackTrace();

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();

                        } finally {
                            SwingUtilities.invokeLater(() ->
                                    playSoundButton.setEnabled(true));
                        }
                    }
                });

                playMorseCodeThread.start();
            }
        });

        add(titleLabel);
        add(textInputLabel);
        add(textInputScroll);
        add(morseCodeInputLabel);
        add(morseCodeScroll);
        add(playSoundButton);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() != KeyEvent.VK_SHIFT) {

            String inputText = textInputArea.getText();
            morseCodeArea.setText(controller.translateToMorse(inputText));

        }
    }
}