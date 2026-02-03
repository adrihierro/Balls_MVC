package Generators.worldgenerator;

/**
 * Provides the final version and the position of the static object
 */


public class StaticObject {

    private final String path;
    private final int x;
    private final int y;
    private final int size;

    public StaticObject(String path, int x, int y, int size) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.size = size;
    }


    public String getPath() {
        return path;
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
