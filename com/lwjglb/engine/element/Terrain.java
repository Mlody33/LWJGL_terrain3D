package com.lwjglb.engine.element;

public class Terrain {

    private final TerrainBlock[] terrainBlocks;

    public Terrain(int blocksPerRow, float scale, float minY, float maxY, String heightMap, String textureFile, int textInc) throws Exception {
        terrainBlocks = new TerrainBlock[blocksPerRow * blocksPerRow];
        for (int row = 0; row < blocksPerRow; row++) {
            for (int col = 0; col < blocksPerRow; col++) {
                float xDisplacement = row * blocksPerRow * scale;
                float zDisplacement = row * blocksPerRow * scale;

                TerrainBlock terrainBlock = new TerrainBlock();
                terrainBlock.setPosition(xDisplacement, 0, zDisplacement);
                terrainBlocks[row * blocksPerRow + col] = terrainBlock;
            }
        }
    }

    public TerrainBlock[] getGameElement() {
        return terrainBlocks;
    }
}
