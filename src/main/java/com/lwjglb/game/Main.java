package com.lwjglb.game;

public class Main {
 
    public static void main(String[] args) {
        try {
        	System.out.println("Window start");
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}