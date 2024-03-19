package com.jantar.sync;


public class Mesa {
    /**
     * Enum que representa os possíveis estados de um filósofo.
     */
    private enum EstadoFilosofo {
        PENSANDO, COMENDO, FOME
    }

    /**
     * O número total de filósofos na mesa.
     */
    private final static int NUM_FILOSOFOS = 5;

    /**
     * Array para rastrear o estado de cada par de hashis.
     */
    private boolean[] hashis = new boolean[NUM_FILOSOFOS];

    /**
     * Array para rastrear o estado de cada filósofo.
     */
    private EstadoFilosofo[] estadosFilosofos = new EstadoFilosofo[NUM_FILOSOFOS];

    public Mesa() {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            hashis[i] = true; // Todos os hashis estão inicialmente livres
            estadosFilosofos[i] = EstadoFilosofo.PENSANDO; // Todos os filósofos começam pensando
        }
    }

    /**
     * Método sincronizado para um filósofo pegar os hashis e começar a comer.
     *
     * @param id O identificador do filósofo que está tentando pegar os hashis.
     */
    public synchronized void pegarHashis(int id) {
        estadosFilosofos[id] = EstadoFilosofo.FOME; // O filósofo está com fome
        // Aguarda até que ambos os vizinhos não estejam comendo
        while (estadosFilosofos[aEsquerda(id)] == EstadoFilosofo.COMENDO
                || estadosFilosofos[aDireita(id)] == EstadoFilosofo.COMENDO) {
            try {
                wait(); // Espera até que seja seguro pegar os hashis
            } catch (InterruptedException ignored) {
            }
        }
        hashis[hashiEsquerdo(id)] = false; // Pega o hashi esquerdo
        hashis[hashiDireito(id)] = false; // Pega o hashi direito
        estadosFilosofos[id] = EstadoFilosofo.COMENDO; // O filósofo começa a comer
        imprimeEstadosFilosofos(); // Imprime o estado dos filósofos
        imprimeHashis(); // Imprime o estado dos hashis
    }

    /**
     * Método sincronizado para um filósofo devolver os hashis após terminar de comer.
     *
     * @param id O identificador do filósofo que está devolvendo os hashis.
     */
    public synchronized void devolverHashis(int id) {
        hashis[hashiEsquerdo(id)] = true; // Devolve o hashi esquerdo
        hashis[hashiDireito(id)] = true; // Devolve o hashi direito
        // Notifica todos os filósofos se algum de seus vizinhos estiver com fome
        if (estadosFilosofos[aEsquerda(id)] == EstadoFilosofo.FOME
                || estadosFilosofos[aDireita(id)] == EstadoFilosofo.FOME) {
            notifyAll(); // Notifica todos os filósofos
        }
        estadosFilosofos[id] = EstadoFilosofo.PENSANDO; // O filósofo volta a pensar
        imprimeEstadosFilosofos(); // Imprime o estado dos filósofos
        imprimeHashis(); // Imprime o estado dos hashis
    }

    /**
     * Calcula o índice do filósofo à direita do filósofo dado.
     *
     * @param filosofo O identificador do filósofo atual.
     * @return O identificador do filósofo à direita.
     */
    private int aDireita(int filosofo) {
        return (filosofo + 1) % NUM_FILOSOFOS;
    }

    /**
     * Calcula o índice do filósofo à esquerda do filósofo dado.
     *
     * @param filosofo O identificador do filósofo atual.
     * @return O identificador do filósofo à esquerda.
     */
    private int aEsquerda(int filosofo) {
        return (filosofo + NUM_FILOSOFOS - 1) % NUM_FILOSOFOS;
    }

    /**
     * Retorna o índice do hashi à esquerda do filósofo dado.
     *
     * @param filosofo O identificador do filósofo atual.
     * @return O índice do hashi à esquerda do filósofo.
     */
    private int hashiEsquerdo(int filosofo) {
        return filosofo;
    }

    /**
     * Retorna o índice do hashi à direita do filósofo dado.
     *
     * @param filosofo O identificador do filósofo atual.
     * @return O índice do hashi à direita do filósofo.
     */
    private int hashiDireito(int filosofo) {
        return (filosofo + 1) % NUM_FILOSOFOS;
    }

    /**
     * Imprime o estado atual dos filósofos.
     */
    private void imprimeEstadosFilosofos() {
        System.out.print("Estados dos Filósofos: ");
        for (EstadoFilosofo estado : estadosFilosofos) {
            System.out.print(estado + " ");
        }
        System.out.println();
    }

    /**
     * Imprime o estado atual dos hashis.
     */
    private void imprimeHashis() {
        System.out.print("Estado dos Hashis: ");
        for (boolean hashi : hashis) {
            System.out.print(hashi ? "LIVRE " : "OCUPADO ");
        }
        System.out.println();
    }
}
