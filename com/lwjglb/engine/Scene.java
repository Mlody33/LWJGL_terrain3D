package com.lwjglb.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lwjglb.engine.element.SkyBox;
import com.lwjglb.engine.element.TerrainBlock;
import com.lwjglb.engine.modifier.Mesh;

public class Scene {

    private Map<Mesh, List<TerrainBlock>> meshMap;
    private SkyBox skyBox;
    private SceneSun sceneSun;

    public Scene() {
        meshMap = new HashMap();
    }
    
    public Map<Mesh, List<TerrainBlock>> getGameMeshes() {
        return meshMap;
    }

    public void setGameElement(TerrainBlock[] gameElements) {
        int numGameElement = gameElements != null ? gameElements.length : 0;
        for (int i=0; i<numGameElement; i++) {
            TerrainBlock gameElement = gameElements[i];
            Mesh mesh = gameElement.getMesh();
            List<TerrainBlock> list = meshMap.get(mesh);
            if ( list == null ) {
                list = new ArrayList<>();
                meshMap.put(mesh, list);
            }
            list.add(gameElement);
        }
    }

    public void cleanup() {
        for (Mesh mesh : meshMap.keySet()) {
            mesh.cleanUp();
        }
    }

    public SkyBox getSkyBox() {
        return skyBox;
    }

    public void setSkyBox(SkyBox skyBox) {
        this.skyBox = skyBox;
    }

    public SceneSun getSceneSun() {
        return sceneSun;
    }

    public void setSceneSun(SceneSun sceneSun) {
        this.sceneSun = sceneSun;
    }
    
}
