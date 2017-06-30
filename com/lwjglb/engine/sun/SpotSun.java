package com.lwjglb.engine.sun;

import org.joml.Vector3f;

public class SpotSun {

    private SingleSun singleSun;
    private Vector3f coneDirection;
    private float cutOff;

    public SpotSun(SingleSun singleSun, Vector3f coneDirection, float cutOffAngle) {
        this.singleSun = singleSun;
        this.coneDirection = coneDirection;
        setCutOffAngle(cutOffAngle);
    }

    public SpotSun(SpotSun spotSun) {
        this(new SingleSun(spotSun.getSpotSun()),
                new Vector3f(spotSun.getConeDirection()),
                spotSun.getCutOff());
    }

    public SingleSun getSpotSun() {
        return singleSun;
    }

    public void setPointLight(SingleSun singleSun) {
        this.singleSun = singleSun;
    }

    public Vector3f getConeDirection() {
        return coneDirection;
    }

    public void setConeDirection(Vector3f coneDirection) {
        this.coneDirection = coneDirection;
    }

    public float getCutOff() {
        return cutOff;
    }

    public void setCutOff(float cutOff) {
        this.cutOff = cutOff;
    }
    
    public final void setCutOffAngle(float cutOffAngle) {
        this.setCutOff((float)Math.cos(Math.toRadians(cutOffAngle)));
    }

}

