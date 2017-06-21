package com.lwjglb.engine.graph.lights;

import org.joml.Vector3f;

import com.lwjglb.engine.graph.lights.PointLight;

public class SpotLight {

    private PointLight pointLight;
    private Vector3f coneDirection;

    public SpotLight(PointLight pointLight, Vector3f coneDirection) {
        this.pointLight = pointLight;
        this.coneDirection = coneDirection;
    }

    public SpotLight(SpotLight spotLight) {
		// TODO Auto-generated constructor stub
	}

	public PointLight getPointLight() {
        return pointLight;
    }
    public void setPointLight(PointLight pointLight) {
        this.pointLight = pointLight;
    }

    public Vector3f getConeDirection() {
        return coneDirection;
    }
    public void setConeDirection(Vector3f coneDirection) {
        this.coneDirection = coneDirection;
    }

}
