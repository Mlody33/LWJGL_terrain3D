package com.lwjglb.game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.lwjglb.engine.GameInterface;
import com.lwjglb.engine.Input;
import com.lwjglb.engine.Scene;
import com.lwjglb.engine.SceneSun;
import com.lwjglb.engine.Window;
import com.lwjglb.engine.element.SkyBox;
import com.lwjglb.engine.element.Terrain;
import com.lwjglb.engine.modifier.Camera;
import com.lwjglb.engine.modifier.Render;
import com.lwjglb.engine.sun.DirectSun;

public class Game implements GameInterface {

    private final Vector3f cameraInc;
    private final Camera camera;
    private Scene scene;
    private float lightPosition;
	private Render render;

    public Game() {
        render = new Render();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        lightPosition = -40;
    }

    @Override
    public void init(Window window) throws Exception {
        render.init(window);
        scene = new Scene();

        Terrain terrain = new Terrain(1, 30, -0.1f, 0.1f, "/textures/heightmap.png", "/textures/terrain.png", 40);
        scene.setGameElement(terrain.getGameElement());

        SkyBox skyBox = new SkyBox("/models/skybox.obj", "/textures/skybox.png");
        skyBox.setScale(50.0f);
        scene.setSkyBox(skyBox);

        SceneSun sceneSun = new SceneSun();
        scene.setSceneSun(sceneSun);

        sceneSun.setSkyBoxSun(new Vector3f(0.9f, 0.9f, 0.9f));

        Vector3f lightPosition = new Vector3f(1, 1, 0);
        sceneSun.setDirectSun(new DirectSun(new Vector3f(1, 1, 1), lightPosition, 1.0f));

        camera.getPosition().x = 0.0f;
        camera.getPosition().z = 0.0f;
        camera.getPosition().y = -0.2f;
        camera.getRotation().x = 10.f;
    }

    @Override
    public void input(Window window, Input mouseInput) {
        cameraInc.set(0, 0, 0);
        if ((window.isKeyPressed(GLFW_KEY_W)) || (window.isKeyPressed(GLFW_KEY_UP))) {
            cameraInc.z = -1;
        } else if ( (window.isKeyPressed(GLFW_KEY_S) || (window.isKeyPressed(GLFW_KEY_DOWN)) )) {
            cameraInc.z = 1;
        }
        if ((window.isKeyPressed(GLFW_KEY_A)) || (window.isKeyPressed(GLFW_KEY_LEFT))) {
            cameraInc.x = -1;
        } else if ((window.isKeyPressed(GLFW_KEY_D)) || (window.isKeyPressed(GLFW_KEY_RIGHT))) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }

    @Override
    public void update(float interval, Input input) {
        if (input.isLeftButtonPressed()) {
            Vector2f rotationVector = input.getDisplayVector();
            camera.moveRotation(rotationVector.x * 0.2f, rotationVector.y * 0.2f, 0);
        }

        camera.movePosition(cameraInc.x * 0.05f, cameraInc.y * 0.05f, cameraInc.z * 0.05f);

        SceneSun sceneSun = scene.getSceneSun();
        DirectSun directSun = sceneSun.getDirectSun();
        lightPosition += 0.5f;
        if(lightPosition >= 90f){
        	lightPosition = -90f;
        }
        directSun.setIntensity(0.9f);
        sceneSun.getSkyBoxSun().set(0.9f, 0.9f, 0.9f);
    }

    @Override
    public void render(Window window) {
        render.render(window, camera, scene);
    }

    @Override
    public void cleanup() {
        render.cleanup();
        scene.cleanup();
    }

}
