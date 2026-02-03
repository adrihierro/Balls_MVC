import Generators.AssetProvider;
import Generators.DefaultAssetProvider;
import Generators.worldgenerator.RandomStaticGenerator;
import Generators.worldgenerator.RandomWorldGenerator;
import Generators.worldgenerator.StaticObjectGenerator;
import Generators.worldgenerator.WorldGenerator;
import controller.*;

public class Main {
    public static void main(String[] args) {

        AssetProvider assetProvider = new DefaultAssetProvider();
        StaticObjectGenerator objectGenerator = new RandomStaticGenerator(assetProvider);
        WorldGenerator worldGenerator = new RandomWorldGenerator(assetProvider,objectGenerator);

        Controller controller = new Controller(worldGenerator);
    }
}
