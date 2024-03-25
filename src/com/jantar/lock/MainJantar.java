package com.jantar.lock;

public class MainJantar {

	public static void main(String[] args) {
		
		new Thread(new JantarFilosofo(0)).start();
		new Thread(new JantarFilosofo(1)).start();
		new Thread(new JantarFilosofo(2)).start();
		new Thread(new JantarFilosofo(3)).start();
		new Thread(new JantarFilosofo(4)).start();
	}
}
