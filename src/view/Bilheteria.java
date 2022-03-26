package view;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bilheteria extends Thread {

	private int idThread;
	private Semaphore semaforo;
	Random random = new Random();
	private static int INGRESSOS = 100;
	int tempo = 0;
	
	public Bilheteria(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		login();
	}

	private void login() {

		tempo = random.nextInt(1051) + 50;
		System.out.println("Login #" + idThread + " iniciado.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (tempo < 1000) {
			processo();
		} else {
			System.out.println("Timeout no login!");
		}
	}

	private void processo() {

		tempo = random.nextInt(2001) + 1000;
		System.out.println("Processo de compra #" + idThread + " iniciado.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (tempo < 2500) {
			validacao();
		} else {
			System.out.println("Final de tempo de sessão!");
		}
	}

	private void validacao() {
		
		int quantidadeBilhete = random.nextInt(4) + 1;

		if (quantidadeBilhete <= INGRESSOS) {
			try {
				semaforo.acquire();
//				INGRESSOS = INGRESSOS - quantidadeBilhete;
				INGRESSOS -= quantidadeBilhete;
				System.out.println("_________________________");
				System.out.println("Compra #" + idThread + " finalizada.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			System.out.println("Ingressos comprados: " + quantidadeBilhete);
			System.out.println("Ingressos disponpiveis:" + INGRESSOS);
		} else {
			System.out.println("Ingressos Esgotados!");

		}

	}
}
