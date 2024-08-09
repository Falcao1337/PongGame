import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Ball extends JLabel {
    private int xVelocity = 5;
    private int yVelocity = 5;
    private final JLabel paddle1;
    private final JLabel paddle2;
    private final Random random = new Random();
    public int player1;
    public int player2;
    public Field field;



    public Ball(JLabel paddle1, JLabel paddle2, Field field) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        setInitialPositionAndVelocity();
        this.field = field;
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
            field.updateScore();
            field.updateBallPosition();
        } else if (bounds.x == 1080) {
            player1++;
            field.updateScore();
            field.updateBallPosition();
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
