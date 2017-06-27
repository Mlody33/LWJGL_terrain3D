package com.lwjglb.engine;

public interface InterfaceGame {
    void initialize(Window window) throws Exception;
    void input(Window window, Input mouseInput);
    void update(float interval, Input mouseInput);
    void render(Window window);
    void delete();
}