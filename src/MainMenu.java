import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Pong Game - Main Menu");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 100, 100));
        panel.setBackground(Color.BLACK);
        panel.setBorder(new EmptyBorder(200, 200, 200, 200));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 40));
        startButton.setBackground(Color.WHITE);
        startButton.setOpaque(true);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            new Field();
            dispose();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 40));
        exitButton.setBackground(Color.WHITE);
        exitButton.setOpaque(true);
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(startButton);
        panel.add(exitButton);

        add(panel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}