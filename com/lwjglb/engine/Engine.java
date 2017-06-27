package com.lwjglb.engine;

public class Engine implements Runnable {

    public static final int TARGET_FPS = 60;
    public static final int TARGET_UPS = 30;
    private final Window window;
    private final Thread mainLoopThread;
    private final Timer timer;
    private final InterfaceGame game;
    private final Input mouse;

    public Engine(String windowTitle, boolean vSync, InterfaceGame game, boolean resized) throws Exception {
        this(windowTitle, 0, 0, vSync, game, resized);
    }

    public Engine(String windowTitle, int width, int height, boolean vSync, InterfaceGame game, boolean resized) throws Exception {
        mainLoopThread = new Thread(this, "MAIN LOOP THREAD");
        window = new Window(windowTitle, width, height, vSync, resized);
        mouse = new Input();
        this.game = game;
        timer = new Timer();
    }

    public void open() {
        mainLoopThread.start();
    }

    @Override
    public void run() {
        try {
            window.initialize();
            timer.initialize();
            mouse.initialize(window);
            game.initialize(window);

            mainLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            delete();
        }
    }

    protected void mainLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if ( !window.isvSync() ) {
                sync();
            }
        }
    }

    protected void delete() {
        game.delete();
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void input() {
        mouse.input(window);
        game.input(window, mouse);
    }

    protected void update(float interval) {
        game.update(interval, mouse);
    }

    protected void render() {
        game.render(window);
        window.update();
    }
}
