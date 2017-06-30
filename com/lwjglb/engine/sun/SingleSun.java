package com.lwjglb.engine.sun;

import org.joml.Vector3f;

public class SingleSun {

    private Vector3f color;
    private Vector3f position;
    private float intensity;
    private Gradient gradient;
    
    public SingleSun(Vector3f color, Vector3f position, float intensity) {
        gradient = new Gradient(1, 0, 0);
        this.color = color;
        this.position = position;
        this.intensity = intensity;
    }

    public SingleSun(Vector3f color, Vector3f position, float intensity, Gradient attenuation) {
        this(color, position, intensity);
        this.gradient = attenuation;
    }

    public SingleSun(SingleSun pointLight) {
        this(new Vector3f(pointLight.getColor()), new Vector3f(pointLight.getPosition()),
                pointLight.getIntensity(), pointLight.getAttenuation());
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public Gradient getAttenuation() {
        return gradient;
    }

    public void setAttenuation(Gradient attenuation) {
        this.gradient = attenuation;
    }

    public static class Gradient {

        private float constant;
        private float linear;
        private float exponent;

        public Gradient(float constant, float linear, float exponent) {
            this.constant = constant;
            this.linear = linear;
            this.exponent = exponent;
        }

        public float getConstant() {
            return constant;
        }

        public void setConstant(float constant) {
            this.constant = constant;
        }

        public float getLinear() {
            return linear;
        }

        public void setLinear(float linear) {
            this.linear = linear;
        }

        public float getExponent() {
            return exponent;
        }

        public void setExponent(float exponent) {
            this.exponent = exponent;
        }
    }
}