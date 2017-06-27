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

public class Game implements InterfaceGame {

    private final Vector3f cameraMovement;
    private final RenderScene render;
    private final Camera camera;
    private Scene scene;
    private float lightPosition;


    public Game() {
        render = new RenderScene();
        camera = new Camera();
        cameraMovement = new Vector3f(0.0f, 0.0f, 0.0f);
        lightPosition = -90;
    }

    @Override
    public void initialize(Window window) throws Exception {
        render.init(window);
        scene = new Scene();

        Terrain terrain = new Terrain(1, 30, -0.1f, 0.1f, "/textures/heightmap.png", "/textures/terrain.png", 40);
        scene.setItems(terrain.setItems());

        SkyBox skyBox = new SkyBox("/models/skybox.obj", "/textures/skybox.png");
        skyBox.setScale(50.0f);
        scene.setSkyBox(skyBox);

        Vector3f lightPosition = new Vector3f(1, 1, 0);

        SceneLight sceneLight = new SceneLight();
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));
        sceneLight.setDirectionalLight(new DirectionalLight(new Vector3f(1, 1, 1), lightPosition, 1.0f));

        scene.setSceneLight(sceneLight);

        camera.getPosition().x = 0.0f;
        camera.getPosition().z = 0.0f;
        camera.getPosition().y = -0.2f;
        camera.getRotation().x = 10.f;
    }

    @Override
    public void input(Window window, Input mouseInput) {
        cameraMovement.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraMovement.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraMovement.z = 1;
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraMovement.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraMovement.x = 1;
        }

        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraMovement.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraMovement.y = 1;
        }
    }

    @Override
    public void update(float interval, Input mouse) {
        if (mouse.isLeftButtonPressed()) {
            Vector2f rotation = mouse.getDisplVec();
            camera.moveRotation(rotation.x * 0.2f, rotation.y * 0.2f, 0);
        }
        camera.movePosition(cameraMovement.x * 0.05f, cameraMovement.y * 0.05f, cameraMovement.z * 0.05f);

        SceneLight sceneLight = scene.getSceneLight();
        DirectionalLight directionalLight = sceneLight.getDirectionalLight();
        lightPosition += 0.5f;
        if(lightPosition >= 90f){
            lightPosition = -90f;
        }
        directionalLight.setIntensity(0.9f);
        sceneLight.getSkyBoxLight().set(0.9f, 0.9f, 0.9f);
    }

    @Override
    public void render(Window window) {
        render.render(window, camera, scene);
    }

    @Override
    public void delete() {
        render.delete();
        scene.delete();
    }

}