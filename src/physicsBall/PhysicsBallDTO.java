package physicsBall;

public class PhysicsBallDTO {
    public int x;
    public int y;
    public int vx;
    public int vy;
    public int ax; //aceleration
    public int ay; //aceleration
    public int radius;
    public double masa;

    // Constructor completo
    public PhysicsBallDTO(int x, int y, int vx, int vy, int ax, int ay, int radius, double masa) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = radius;
        this.masa = masa;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVx() { return vx; }
    public int getVy() { return vy; }
    public int getAx() { return ax; }
    public int getAy() { return ay; }
    public int getRadius() { return radius; }
    public double getMasa() { return masa; }

    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVx(int vx) { this.vx = vx; }
    public void setVy(int vy) { this.vy = vy; }

}
