package com.jantar.semaforoOrdenado;

import java.util.concurrent.Semaphore;

class Jantar extends Thread {

    // ATRIBUTOS
    private Semaphore s;
    private String nome;
    // o filosofo comeu?
    private boolean cheio = false;

    // CONSTRUTOR
    public Jantar(Semaphore s, String nome) {
        this.s = s;
        this.nome = nome;
    }

    // GETTERS E SETTERS
    public Semaphore getS() {
        return s;
    }

    public void setS(Semaphore s) {
        this.s = s;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // METODOS
    public void run() {
        try {
            if (!this.cheio) {
                getS().acquire();
                System.out.println(getNome() + " pegou um lugar na mesa");

                sleep(300);
                this.cheio = true;

                System.out.println(getNome() + " comeu e deixou a mesa");
                getS().release();
            }
        } catch (InterruptedException e) {
            System.err.println("Erro: " + e.toString());
        }
    }
}