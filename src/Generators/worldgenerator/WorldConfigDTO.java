package Generators.worldgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Inmutable container of the final configuration of the world
 */

public class WorldConfigDTO {
    private final String backgroundPath;
    private final List<StaticObject> staticObjects;

    public WorldConfigDTO(String backgroundPath, List<StaticObject> staticObjects) {
        this.backgroundPath = backgroundPath;
        this.staticObjects = staticObjects;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public List<StaticObject> getStaticObjects() {
        return new ArrayList<>(staticObjects); // return a copy of the list
    }
}