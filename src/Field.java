import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.HashSet;

public class Field extends JFrame implements KeyListener {
    JLabel label;
    JLabel label2;
    Ball ball;
    Set<Integer> pressedKeys = new HashSet<>();
    CustomPanel panel;
    JLabel scoreLabel;
    Timer timer;

    public Field() {
        setTitle("Pong Game");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this);

        panel = new CustomPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);

        label = new JLabel();
        label.setBounds(20, 310, 10, 50);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);

        label2 = new JLabel();
        label2.setBounds(1025, 310, 10, 50);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);

        ball = new Ball(label, label2, this);
        ball.setBounds(540, 350, 10, 10);
        ball.setBackground(Color.WHITE);
        ball.setOpaque(true);

        scoreLabel = new JLabel(ball.player1 + "     " + ball.player2, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(0, 0, 1080, 80);

        panel.add(label);
        panel.add(label2);
        panel.add(ball);
        panel.add(scoreLabel);
        add(panel);


        timer = new Timer(10, e -> {
            updatePositions();
            ball.move();
            checkEndGame();
        });
        timer.start();

        this.setVisible(true);
    }

    public void updateScore() {
        scoreLabel.setText(ball.getPlayer1() + "     " + ball.getPlayer2());
    }

    public void updateBallPosition() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ball.setBounds(540, 350, 10, 10);
                    }
                },
                3000
        );
    }

    private void checkEndGame() {
        if (ball.getPlayer1() >= 3 || ball.getPlayer2() >= 3) {
            timer.stop();
            endGameLogic();
        }
    }

    public void endGameLogic() {
        remove(panel);

        JLabel pScreen = new JLabel();
        if (ball.getPlayer1() == 3) {
            pScreen.setText("Parabéns Player1!!");
        } else if (ball.getPlayer2() == 3) {
            pScreen.setText("Parabéns Player2!!");
        }
        pScreen.setBackground(Color.BLACK);
        pScreen.setForeground(Color.WHITE);
        pScreen.setFont(new Font("Arial", Font.BOLD, 24));
        pScreen.setHorizontalAlignment(SwingConstants.CENTER);
        pScreen.setBounds(0, 0, 1080, 80);

        JPanel endGamePanel = new JPanel();
        endGamePanel.setLayout(new GridLayout(2, 1, 100, 100));
        endGamePanel.setBackground(Color.BLACK);
        endGamePanel.setBorder(new EmptyBorder(120, 120, 120, 120));

        JPanel buttonPanel = getjPanel();


        endGamePanel.add(pScreen, BorderLayout.NORTH);
        endGamePanel.add(buttonPanel, BorderLayout.CENTER);

        add(endGamePanel);

        revalidate();
        repaint();
    }

    private JPanel getjPanel() {
        JButton retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 40));
        retryButton.setBackground(Color.WHITE);
        retryButton.setOpaque(true);
        retryButton.setFocusPainted(false);
        retryButton.addActionListener(e -> {
            new Field();
            dispose();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 40));
        exitButton.setBackground(Color.WHITE);
        exitButton.setOpaque(true);
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 50, 50));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }


    private void updatePositions() {
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            if (label.getY() - 10 >= 0) {
                label.setLocation(label.getX(), label.getY() - 10);
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            if (label.getY() + label.getHeight() <= getHeight() - label.getHeight()) {
                label.setLocation(label.getX(), label.getY() + 10);
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            if (label2.getY() - 10 >= 0) {
                label2.setLocation(label2.getX(), label2.getY() - 10);
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            if (label2.getY() + label2.getHeight() <= getHeight() - label2.getHeight()) {
                label2.setLocation(label2.getX(), label2.getY() + 10);
            }
        }
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    private static class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(10));
            int segmentLength = 27;

            for (int i = 0; i < getHeight(); i += segmentLength * 2) {
                g2d.setColor(Color.WHITE);
                g2d.drawLine(540, i, 540, i + segmentLength);
            }

            g2d.drawLine(0,676, 1080, 676);
            g2d.drawLine(0,5, 1080, 5);

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Field::new);
    }
}