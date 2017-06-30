package com.lwjglb.game;

import com.lwjglb.engine.Engine;
import com.lwjglb.engine.GameInterface;
 
public class Main {
 
    public static void main(String[] args) {
        try {
            Game gameLogic = new Game();
            Engine gameEng = new Engine("GAME_test", true, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}