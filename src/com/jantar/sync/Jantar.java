package com.jantar.sync;

public class Jantar {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        new Filosofo("Socrates", mesa, 0).start();
        new Filosofo("Platao", mesa, 1).start();
        new Filosofo("Maquiavel", mesa, 2).start();
        new Filosofo("Aristoteles", mesa, 3).start();
        new Filosofo("Hobbes", mesa, 4).start();
    }
}

