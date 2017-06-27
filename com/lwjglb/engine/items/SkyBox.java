package com.lwjglb.engine.items;

import com.lwjglb.engine.graph.Material;
import com.lwjglb.engine.graph.Mesh;
import com.lwjglb.engine.graph.ObjectLoader;
import com.lwjglb.engine.graph.TextureDecoder;

public class SkyBox extends GameItem {

    public SkyBox(String objModel, String textureFile) throws Exception {
        super();
        Mesh skyBoxMesh = ObjectLoader.loadMesh(objModel);
        TextureDecoder skyBoxtexture = new TextureDecoder(textureFile);
        skyBoxMesh.setMaterial(new Material(skyBoxtexture, 0.0f));
        setMesh(skyBoxMesh);
        setPosition(0, 0, 0);
    }
}
