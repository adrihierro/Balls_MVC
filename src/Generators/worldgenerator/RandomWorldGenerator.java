package Generators.worldgenerator;

import Generators.AssetProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * World generator generates static decoration for the world
 */

public class RandomWorldGenerator implements WorldGenerator {
    private Random random;
    private final AssetProvider assetProvider;
    private final StaticObjectGenerator staticObjectGenerator;

    public RandomWorldGenerator(AssetProvider assetProvider,StaticObjectGenerator staticObjectGenerator) {
        this.random = new Random();
        this.assetProvider = assetProvider;
        this.staticObjectGenerator = staticObjectGenerator;

    }

    // Method for build the random WorldConfig

    public WorldConfigDTO generateWorld(int maxStaticObjects, int viewerHeight, int viewerWidth) {

        List<String> backgrounds = assetProvider.getBackGrounds();

        String Background = backgrounds.get(random.nextInt(backgrounds.size()));

        List<StaticObject> staticObjectList = new ArrayList<>();

        for (int i = 0; i < maxStaticObjects; i++) {
            staticObjectList.add(staticObjectGenerator.generate(viewerWidth,viewerHeight));
        }

        return new WorldConfigDTO(Background, staticObjectList);
    }

}
