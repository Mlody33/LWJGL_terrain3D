package com.lwjglb.engine.graph;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HeightMapMesh {

    private final Mesh mesh;

    public HeightMapMesh(String heightMapFile, String textureFile, int textInc) throws Exception {

    	this.mesh = new Mesh();
        Texture texture = new Texture(textureFile);

        List<Float> positions = new ArrayList();
        List<Float> textCoords = new ArrayList();
        List<Integer> indices = new ArrayList();

        PNGDecoder decoder = new PNGDecoder(getClass().getResourceAsStream(heightMapFile));
        
		ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
        decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        
        for (int row = 0; row < 500; row++) {
            for (int col = 0; col < 500; col++) {
                positions.add((float) col);
                positions.add(getHeight(col, row, buffer));
                positions.add((float) row);
            }
        }
        
    }

	public Mesh getMesh() {
        return mesh;
    }


    private float getHeight(int x, int z, ByteBuffer buffer) {
        byte r = buffer.get(x * 4 + 0 + z * 4 * 500);
        byte g = buffer.get(x * 4 + 1 + z * 4 * 500);
        byte b = buffer.get(x * 4 + 2 + z * 4 * 500);
        byte a = buffer.get(x * 4 + 3 + z * 4 * 500);
        return r + Math.abs(g - b * a);
    }

}
