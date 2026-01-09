package physicsBall;

public class PhysicsBallDTO {
    public final int x;
    public final int y;
    public final int vx;
    public final int vy;
    public final int ax; //aceleration
    public final int ay; //aceleration
    public final int radius;
//    public final double masa;

    // Constructor completo
    public PhysicsBallDTO(int x, int y, int vx, int vy,int ax,int ay,int radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = radius;
    }
}
