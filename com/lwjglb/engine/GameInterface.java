package com.lwjglb.engine;

public interface GameInterface {
    void init(Window window) throws Exception;
    void input(Window window, Input mouseInput);
    void update(float interval, Input mouseInput);
    void render(Window window);
	void cleanup();
}