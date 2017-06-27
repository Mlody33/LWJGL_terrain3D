package com.lwjglb.engine;

import org.joml.Vector3f;

import com.lwjglb.engine.graph.lights.DirectionalLight;
import com.lwjglb.engine.graph.lights.PointLight;
import com.lwjglb.engine.graph.lights.SpotLight;

public class SceneLight {

    private Vector3f skyBoxLight;
    private DirectionalLight directionalLight;

    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public void setDirectionalLight(DirectionalLight directionalLight) {
        this.directionalLight = directionalLight;
    }

    public Vector3f getSkyBoxLight() {
        return skyBoxLight;
    }

    public void setSkyBoxLight(Vector3f skyBoxLight) {
        this.skyBoxLight = skyBoxLight;
    }
    
}