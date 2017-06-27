package com.lwjglb.game;

import com.lwjglb.engine.Engine;

public class Main {
 
    public static void main(String[] args) {
        try {
            Game game = new Game();
            Engine engine = new Engine("Terrain3D", true, game, false);
            engine.open();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}