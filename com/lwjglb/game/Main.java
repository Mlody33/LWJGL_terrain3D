package com.lwjglb.game;

import com.lwjglb.engine.GameEngine;
import com.lwjglb.engine.InterfaceGameLogic;
 
public class Main {
 
    public static void main(String[] args) {
        try {
            Game gameLogic = new Game();
            GameEngine gameEng = new GameEngine("GAME_test", true, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}