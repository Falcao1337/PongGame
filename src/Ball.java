import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;


class Ball extends JLabel {
    private int xVelocity = 5;
    private int yVelocity = 5;
    private JLabel paddle1;
    private JLabel paddle2;
    private Random random = new Random();
    public int player1;
    public int player2;
    public Campo campo;
    Timer timer = new Timer();



    public Ball(JLabel paddle1, JLabel paddle2, Campo campo) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        setInitialPositionAndVelocity();
        this.campo = campo;
    }


    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setInitialPositionAndVelocity() {
        // Posição inicial aleatória
        int x = random.nextInt(1080 - 30) + 10; // Evita a borda direita
        int y = random.nextInt(720 - 30) + 10; // Evita a borda inferior
        setBounds(x, y, 10, 10);

        // Direção inicial aleatória
        if (random.nextBoolean()) {
            xVelocity = -xVelocity;
        }
        if (random.nextBoolean()) {
            yVelocity = -yVelocity;
        }
    }

    public void move() {

        Rectangle bounds = getBounds();

        bounds.y += yVelocity;
        bounds.x += xVelocity;

        if (bounds.x == 0) {
            player2++;
            campo.updateScore();
            campo.updateBallPosition();
        } else if (bounds.x == 1080) {
            player1++;
            campo.updateScore();
            campo.updateBallPosition();
        }
        if (bounds.y < 10 || bounds.y > 660) {
            yVelocity = -yVelocity;
        }
        if (bounds.intersects(paddle1.getBounds()) || bounds.intersects(paddle2.getBounds())) {
            xVelocity = -xVelocity;
        }
        setBounds(bounds);
    }
}
