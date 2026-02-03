package Generators;

import Generators.worldgenerator.StaticObjectConfig;

import java.util.ArrayList;
import java.util.List;

public class DefaultAssetProvider implements AssetProvider {

    private final List<String> availableBackgrounds = new ArrayList<>();
    private final List<StaticObjectConfig> availableStaticObjects = new ArrayList<>();

    public DefaultAssetProvider(){
        initBackgroundsCatalog();
        initStaticObjectCatalog();
    }

    private void initBackgroundsCatalog() {
        availableBackgrounds.add("/Images/Assets/background-space.jpg");
        availableBackgrounds.add("/Images/Assets/background-space2.jpg");
        availableBackgrounds.add("/Images/Assets/background-space3.jpg");
        availableBackgrounds.add("/Images/Assets/background-space4.jpg");
    }

    private void initStaticObjectCatalog() {
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/planet-3.png", 50, 100));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/planet-4.png", 50, 100));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/planet-5.png", 50, 100));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/sun-1.png", 50, 100));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/moon-2.png", 50, 100));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/stars-2-mini.png", 50, 200));
        availableStaticObjects.add(new StaticObjectConfig("/Images/Assets/planet-2.png", 50, 100));
    }

    @Override
    public List<String> getBackGrounds() {
        return availableBackgrounds;
    }

    @Override
    public List<StaticObjectConfig> getStaticObject() {
        return availableStaticObjects;
    }
}
