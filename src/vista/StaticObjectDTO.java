package vista;

/**
 * DTO de object estático para la Vista
 */

public class StaticObjectDTO {
    private final String imagePath;
    private final int x;
    private final int y;
    private final int size;

    public StaticObjectDTO(String imagePath, int x, int y, int size) {
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}