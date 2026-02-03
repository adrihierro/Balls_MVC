package vista;

import java.util.List;

public class WorldRenderableDTO {

    private final String backgroundpath;
    private final List<StaticObjectDTO> staticObject;

    public WorldRenderableDTO(String path, List<StaticObjectDTO> staticObject) {
        this.backgroundpath = path;
        this.staticObject = staticObject;
    }

    public String getPath() {
        return backgroundpath;
    }

    public List<StaticObjectDTO> getStaticObject() {
        return staticObject;
    }
}
