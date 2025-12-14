package physicsBall;

public class BasicPhysicsEngine implements PhysicEngineInterface {
    @Override
    public void actualizar(PhysicsBallDTO ball, int viewerWidth, int viewerHeight) {
        // Actualizar posición
        ball.setX(ball.getX() + ball.getVx());
        ball.setY(ball.getY() + ball.getVy());

        int diameter = ball.getRadius() * 2;

        // Colisiones con bordes horizontales
        if (ball.getX() <= 0) {
            ball.setX(0);
            ball.setVx(-ball.getVx());
        } else if (ball.getX() + diameter >= viewerWidth) {
            ball.setX(viewerWidth - diameter);
            ball.setVx(-ball.getVx());
        }

        if (ball.getY() <= 0) {
            ball.setY(0);
            ball.setVy(-ball.getVy());
        } else if (ball.getY() + diameter >= viewerHeight) {
            ball.setY(viewerHeight - diameter);
            ball.setVy(-ball.getVy());
        }
    }
}
