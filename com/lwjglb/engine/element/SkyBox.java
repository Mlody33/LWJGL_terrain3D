package com.lwjglb.engine.element;

import com.lwjglb.engine.modifier.LoaderObject;
import com.lwjglb.engine.modifier.Material;
import com.lwjglb.engine.modifier.Mesh;
import com.lwjglb.engine.modifier.Texture;

public class SkyBox extends TerrainBlock {

    public SkyBox(String objModel, String textureFile) throws Exception {
        super();
        Mesh skyBoxMesh = LoaderObject.loadMesh(objModel);
        Texture skyBoxtexture = new Texture(textureFile);
        skyBoxMesh.setMaterial(new Material(skyBoxtexture, 0.0f));
        setMesh(skyBoxMesh);
        setPosition(0, 0, 0);
    }
}
