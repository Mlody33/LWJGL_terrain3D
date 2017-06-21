package com.lwjglb.engine.graph;

import java.util.List;
import java.util.Map;

import com.lwjglb.engine.Utils;
import com.lwjglb.engine.Window;
import com.lwjglb.engine.graph.lights.DirectionalLight;

public class Render {

    public Render() {
    }

    public void init(Window window) throws Exception {
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Camera camera) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

    }

    private void renderLights(Matrix4f viewMatrix) {
        for (int i = 0; i < numLights; i++) {
            Vector3f lightPos = currPointLight.getPosition();
            Vector4f aux = new Vector4f(lightPos, 1);
            aux.mul(viewMatrix);
            lightPos.x = aux.x;
            lightPos.y = aux.y;
            lightPos.z = aux.z;
        }

        for (int i = 0; i < numLights; i++) {
            Vector4f dir = new Vector4f(currSpotLight.getConeDirection(), 0);
            dir.mul(viewMatrix);
            currSpotLight.setConeDirection(new Vector3f(dir.x, dir.y, dir.z));

            Vector3f lightPos = currSpotLight.getPointLight().getPosition();
            Vector4f aux = new Vector4f(lightPos, 1);
            aux.mul(viewMatrix);
            lightPos.x = aux.x;
            lightPos.y = aux.y;
            lightPos.z = aux.z;
        }

        DirectionalLight currDirLight = new DirectionalLight();
    }
}
