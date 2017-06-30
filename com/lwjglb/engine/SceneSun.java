package com.lwjglb.engine;

import org.joml.Vector3f;

import com.lwjglb.engine.sun.DirectSun;
import com.lwjglb.engine.sun.SingleSun;
import com.lwjglb.engine.sun.SpotSun;

public class SceneSun {

    private Vector3f ambientSun;
    private Vector3f skyBoxSun;
    private SingleSun[] SingleSunList;
    private SpotSun[] spotSunList;
    private DirectSun directSun;

    public Vector3f getAmbientSun() {
        return ambientSun;
    }

    public void setAmbientSun(Vector3f ambientLight) {
        this.ambientSun = ambientLight;
    }

    public SingleSun[] getSingleSunList() {
        return SingleSunList;
    }

    public void setSingleSunList(SingleSun[] singleSunList) {
        this.SingleSunList = singleSunList;
    }

    public SpotSun[] getSpotSunList() {
        return spotSunList;
    }

    public void setSpotSunList(SpotSun[] spotSunList) {
        this.spotSunList = spotSunList;
    }

    public DirectSun getDirectSun() {
        return directSun;
    }

    public void setDirectSun(DirectSun directSun) {
        this.directSun = directSun;
    }

    public Vector3f getSkyBoxSun() {
        return skyBoxSun;
    }

    public void setSkyBoxSun(Vector3f skyBoxSun) {
        this.skyBoxSun = skyBoxSun;
    }
    
}