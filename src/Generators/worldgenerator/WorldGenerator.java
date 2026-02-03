package Generators.worldgenerator;

public interface WorldGenerator {

    WorldConfigDTO generateWorld(int maxStaticObjects,int width,int height);

}