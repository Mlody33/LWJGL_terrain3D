package com.lwjglb.game;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.lwjglb.engine.InterfaceGameLogic;
import com.lwjglb.engine.MouseInput;
import com.lwjglb.engine.Scene;
import com.lwjglb.engine.Window;
import com.lwjglb.engine.graph.Camera;
import com.lwjglb.engine.graph.Render;
import com.lwjglb.engine.graph.lights.DirectionalLight;

public class Game implements InterfaceGameLogic {

    private final Camera camera;
    private final Render render;
    private Scene scene;

    public Game() {
    	render = new Render();
        camera = new Camera();
    }

    @Override
    public void init(Window window) throws Exception {

    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {         
        
        DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setIntensity(0.9f);
        
        directionalLight.getDirection().x = 100.0f;
        directionalLight.getDirection().y = 100.0f;
        
    }

    @Override
    public void render(Window window) {
    }

}
