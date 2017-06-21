package com.lwjglb.engine.graph;

import static org.lwjgl.opengl.GL11.glViewport;

import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.lwjglb.engine.Scene;
import com.lwjglb.engine.SceneLight;
import com.lwjglb.engine.Utils;
import com.lwjglb.engine.Window;
import com.lwjglb.engine.graph.lights.DirectionalLight;
import com.lwjglb.engine.graph.lights.PointLight;
import com.lwjglb.engine.graph.lights.SpotLight;
import com.lwjglb.engine.items.GameItem;
import com.lwjglb.engine.items.SkyBox;

public class Render {

    public void init(Window window) throws Exception {
    }

    public void render(Window window, Camera camera, Scene scene) {

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

    }
    
    private void renderSkyBox(Window window, Camera camera, Scene scene) {
        SkyBox skyBox = scene.getSkyBox();
    }

        
    public void renderScene(Window window, Camera camera, Scene scene) {

        SceneLight sceneLight = scene.getSceneLight();
        renderLights(sceneLight);

    }
    
    private void renderLights(SceneLight sceneLight) {
    	
    	PointLight[] pointLightList = sceneLight.getPointLightList();
    	int numLights = pointLightList != null ? pointLightList.length : 0;
    	
    	for (int i = 0; i < numLights; i++) {
    		PointLight currPointLight = new PointLight(pointLightList[i]);
            Vector3f lightPos = currPointLight.getPosition();
            Vector4f aux = new Vector4f(lightPos, 1);
            lightPos.x = aux.x;
            lightPos.y = aux.y;
            lightPos.z = aux.z;
        }
    	
    	SpotLight[] spotLightList = sceneLight.getSpotLightList();
        numLights = spotLightList != null ? spotLightList.length : 0;

        for (int i = 0; i < numLights; i++) {
        	SpotLight currSpotLight = new SpotLight(spotLightList[i]);
            Vector4f dir = new Vector4f(currSpotLight.getConeDirection(), 0);
            currSpotLight.setConeDirection(new Vector3f(dir.x, dir.y, dir.z));

            Vector3f lightPos = currSpotLight.getPointLight().getPosition();
            Vector4f aux = new Vector4f(lightPos, 1);
            lightPos.x = aux.x;
            lightPos.y = aux.y;
            lightPos.z = aux.z;
        }

        DirectionalLight currDirLight = new DirectionalLight();
    }
}
