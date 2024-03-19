package jantar_filosofo;

public class JantarFilosofo extends Thread{
	
	private String[] vet = {"Platão", "Aristóteles", "Sócrates", "Hipatia", "Aspasia"};
	private int id;  
	private String garfo_direita;
	private String garfo_esquerda;
	private static int contador = 0;
	
	private static Object lock = new Object();

	public JantarFilosofo(int id) {
		this.id = id;
	}

	public void comer() {
	    synchronized (lock) {
	    	System.out.println(vet[this.id] + " começou a comer");
	    	
	        if(this.id == 4) {
	            this.garfo_direita = vet[this.id];
	            this.garfo_esquerda = vet[0];
	        }
	        else {
	        	this.garfo_direita = vet[this.id];
		        this.garfo_esquerda = vet[this.id + 1];
	        }
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println(vet[this.id] + " terminou de comer");
	        lock.notify();
	    }
	}
	
	public void esperar() {
		synchronized(lock) {
			
			if(contador == 4) {
				lock.notify();
			}
			
            try {
            	contador++;
            	System.out.println(vet[this.id] + " está esperando");
            	lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }
	
	@Override
	public void run() {
		esperar();
		comer();
		esperar();
	}
}
