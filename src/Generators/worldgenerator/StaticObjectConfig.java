package Generators.worldgenerator;

/**
 * Describes the type of the object
 *
 * @Path the route of the used image
 * @minSize The min size of the image can be
 * @MaxSize The max size of the image can be
 *
 */

public class StaticObjectConfig {

    public final String path;
    public final int minSize;
    public final int maxSize;

    public StaticObjectConfig(String path, int minSize, int maxSize) {
        this.path = path;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
}