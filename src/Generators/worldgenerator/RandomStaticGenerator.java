package Generators.worldgenerator;

import Generators.AssetProvider;

import java.util.List;
import java.util.Random;

public class RandomStaticGenerator implements StaticObjectGenerator{

    private final Random random = new Random();
    private final AssetProvider assetProvider;

    public RandomStaticGenerator(AssetProvider assetProvider){
        this.assetProvider = assetProvider;
    }

    @Override
    public StaticObject generate(int viewerWidth, int viewerHeight) {

        List<StaticObjectConfig> configs = assetProvider.getStaticObject();

        StaticObjectConfig config = configs.get(random.nextInt(configs.size()));

        //Takes random value beetwen maxSize and Min Size
        int size = random.nextInt(config.maxSize - config.minSize + 1) + config.minSize;

        int x = random.nextInt(Math.max(1, viewerWidth - size));
        int y = random.nextInt(Math.max(1, viewerHeight - size));

        return new StaticObject(config.path,x,y,size);
    }
}
