package com.jantar.semaforoOrdenado;

import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) {

        Semaphore s = new Semaphore(1);
        new Jantar(s, "Socrates").start();
        new Jantar(s, "Platão").start();
        new Jantar(s, "Thales").start();
        new Jantar(s, "Maquiavel").start();
        new Jantar(s, "Hobbes").start();
    }
}
