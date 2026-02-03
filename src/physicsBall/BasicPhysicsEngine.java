package physicsBall;

public class BasicPhysicsEngine implements PhysicEngineInterface {

    @Override
    public PhysicsBallDTO newPosition(PhysicsBallDTO ball,int width,int height) {


        int vx = ball.vx+ ball.ax;
        int vy = ball.vy + ball.ay;

        int x = ball.x + vx;
        int y = ball.y + vy;

        int radius = ball.radius;

        int diameter = radius * 2;

        if (x <= 0) {
            x = 0;
            vx = -vx;
        } else if (x + diameter >= width) {
            x = width - diameter;
            vx = -vx;
        }

        if (y <= 0) {
            y = 0;
            vy = -vy;
        } else if (y+diameter >= height){
            y = height - diameter;
            vy = -vy;
        }

        return new PhysicsBallDTO(x,y,vx,vy,ball.ax,ball.ay,radius);
    }
}
