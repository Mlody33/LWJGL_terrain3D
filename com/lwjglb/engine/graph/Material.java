package com.lwjglb.engine.graph;

import org.joml.Vector4f;

public class Material {

    private static final Vector4f DEFAULT_COLOUR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    private Vector4f ambientColour;
    private Vector4f diffuseColour;
    private Texture texture;

    public Material() {
        this.ambientColour = DEFAULT_COLOUR;
        this.diffuseColour = DEFAULT_COLOUR;
        this.texture = null;
    }

    public Material(Vector4f colour) {
        this(colour, colour, colour, null);
    }

    public Material(Texture texture) {
        this(DEFAULT_COLOUR, DEFAULT_COLOUR, DEFAULT_COLOUR, texture);
    }

    public Material(Vector4f ambientColour, Vector4f diffuseColour, Texture texture ) {
        this.ambientColour = ambientColour;
        this.diffuseColour = diffuseColour;
        this.texture = texture;
    }

    public Material(Vector4f colour, Vector4f colour2, Vector4f colour3, Object object) {
		// TODO Auto-generated constructor stub
	}

	public Vector4f getAmbientColour() {
        return ambientColour;
    }

    public void setAmbientColour(Vector4f ambientColour) {
        this.ambientColour = ambientColour;
    }

    public Vector4f getDiffuseColour() {
        return diffuseColour;
    }

    public void setDiffuseColour(Vector4f diffuseColour) {
        this.diffuseColour = diffuseColour;
    }

    public boolean isTextured() {
        return this.texture != null;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}