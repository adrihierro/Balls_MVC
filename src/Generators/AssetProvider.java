package Generators;

import Generators.worldgenerator.StaticObjectConfig;

import java.util.List;

public interface AssetProvider {
    List<String> getBackGrounds();
    List<StaticObjectConfig> getStaticObject();
}
