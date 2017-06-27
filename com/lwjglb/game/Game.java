package com.lwjglb.game;

import com.lwjglb.engine.*;
import com.lwjglb.engine.graph.Camera;
import com.lwjglb.engine.graph.RenderScene;
import com.lwjglb.engine.graph.lights.DirectionalLight;
import com.lwjglb.engine.items.SkyBox;
import com.lwjglb.engine.items.Terrain;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Game implements InterfaceGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;
    private final Vector3f cameraInc;
    private final RenderScene render;
    private final Camera camera;
    private Scene scene;
    private float lightAngle;
    private static final float CAMERA_POS_STEP = 0.05f;


    public Game() {
        render = new RenderScene();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        lightAngle = -90;
    }

    @Override
    public void init(Window window) throws Exception {
        render.init(window);

        scene = new Scene();

        float skyBoxScale = 50.0f;
        float terrainScale = 30;
        int terrainSize = 1;
        float minY = -0.1f;
        float maxY = 0.1f;
        int textInc = 40;
        Terrain terrain = new Terrain(terrainSize, terrainScale, minY, maxY, "/textures/heightmap.png", "/textures/terrain.png", textInc);
        scene.setGameItems(terrain.getGameItems());

        SkyBox skyBox = new SkyBox("/models/skybox.obj", "/textures/skybox.png");
        skyBox.setScale(skyBoxScale);
        scene.setSkyBox(skyBox);

        setupLights();

        camera.getPosition().x = 0.0f;
        camera.getPosition().z = 0.0f;
        camera.getPosition().y = -0.2f;
        camera.getRotation().x = 10.f;
    }

    private void setupLights() {
        SceneLight sceneLight = new SceneLight();
        scene.setSceneLight(sceneLight);

        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

        // Directional Light
        float lightIntensity = 1.0f;
        Vector3f lightPosition = new Vector3f(1, 1, 0);
        sceneLight.setDirectionalLight(new DirectionalLight(new Vector3f(1, 1, 1), lightPosition, lightIntensity));
    }

    @Override
    public void input(Window window, Input mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }

    @Override
    public void update(float interval, Input mouseInput) {
        if (mouseInput.isLeftButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }

        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        SceneLight sceneLight = scene.getSceneLight();
        DirectionalLight directionalLight = sceneLight.getDirectionalLight();
        lightAngle += 0.5f;
        if(lightAngle >= 90f){
            lightAngle = -90f;
        }
        directionalLight.setIntensity(0.9f);
        sceneLight.getSkyBoxLight().set(0.9f, 0.9f, 0.9f);

        double angRad = Math.toRadians(lightAngle);
        directionalLight.getDirection().x = (float) Math.sin(angRad);
        directionalLight.getDirection().y = (float) Math.cos(angRad);

        System.out.println("light angle: "+lightAngle+" | angRad: "+angRad);
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