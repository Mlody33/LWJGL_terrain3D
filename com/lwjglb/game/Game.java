package com.lwjglb.game;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.lwjglb.engine.IGameLogic;
import com.lwjglb.engine.MouseInput;
import com.lwjglb.engine.Window;
import com.lwjglb.engine.graph.Camera;
import com.lwjglb.engine.graph.lights.DirectionalLight;

public class Game implements IGameLogic {

    private final Camera camera;

    public Game() {
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
