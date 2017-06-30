package com.lwjglb.engine;

public class Engine implements Runnable {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;
    private final Window window;
    private final Thread LoopThread;
    private final Timer timer;
    private final GameInterface game;
    private final Input mouseInput;

    public Engine(String windowTitle, boolean vSync, GameInterface gameLogic) throws Exception {
        this(windowTitle, 0, 0, vSync, gameLogic);
    }
    
    public Engine(String windowTitle, int width, int height, boolean vSync, GameInterface game) throws Exception {
        LoopThread = new Thread(this, "GLT2");
        window = new Window(windowTitle, width, height, vSync);
        mouseInput = new Input();
        this.game = game;
        timer = new Timer();
    }

    public void start() {
            LoopThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            loop();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        mouseInput.init(window);
        game.init(window);
    }

    protected void loop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;


            while (accumulator >= interval) {
                accumulator -= interval;
            }

            if ( !window.isvSync() ) {
                sync();
            }
        }
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
        mouseInput.input(window);
        game.input(window, mouseInput);
    }

    protected void update(float interval) {
        game.update(interval, mouseInput);
    }

    protected void render() {
        game.render(window);
        window.update();
    }

}
