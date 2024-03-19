package com.jantar.sync;

public class Filosofo extends Thread {
    /**
     * Tempo máximo que um filósofo passa pensando ou comendo, em milissegundos.
     */
    private final static int TEMPO_MAXIMO = 100;
    private Mesa mesa;
    private String nome;
    private int id;

    public Filosofo(String nome, Mesa mesa, int id) {
        super(nome);
        this.nome = nome;
        this.mesa = mesa;
        this.id = id;
    }

    /**
     * Gera um tempo aleatório entre 0 e TEMPO_MAXIMO para simular o tempo que o filósofo passa
     * pensando ou comendo.
     *
     * @return O tempo gerado.
     */
    private int gerarTempo() {
        return (int) (Math.random() * TEMPO_MAXIMO);
    }

    /**
     * Método sobrescrito de Thread que define o comportamento do filósofo durante o jantar.
     * O filósofo alterna entre pensar, pegar hashis, comer e devolver hashis.
     */
    @Override
    public void run() {
        while (!interrupted()) {
            pensar(gerarTempo()); // O filósofo pensa por um tempo aleatório
            mesa.pegarHashis(this.id); // O filósofo tenta pegar os hashis da mesa
            comer(gerarTempo()); // O filósofo come por um tempo aleatório
            mesa.devolverHashis(this.id); // O filósofo devolve os hashis à mesa
            System.out.println();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Simula o tempo que o filósofo passa pensando.
     *
     * @param tempo O tempo que o filósofo passará pensando.
     */
    private void pensar(int tempo) {
        try {
            System.out.printf("O filósofo %s está pensando...\n", this.nome);
            sleep(tempo); // O filósofo dorme por um tempo aleatório para simular o pensamento
        } catch (InterruptedException e) {
            System.out.printf("O filósofo %s pensou demais...\n", this.nome);
        }
    }

    /**
     * Simula o tempo que o filósofo passa comendo.
     *
     * @param tempo O tempo que o filósofo passará comendo.
     */
    private void comer(int tempo) {
        try {
            System.out.printf("%s está comendo...\n", this.nome);
            sleep(tempo); // O filósofo dorme por um tempo aleatório para simular a refeição
        } catch (InterruptedException e) {
            System.out.printf("O filósofo %s comeu demais...\n", this.nome);
        }
    }
}
