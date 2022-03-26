package view;

import java.util.concurrent.Semaphore;
import controller.Bilheteria;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);

		for (int idThread = 1; idThread < 301; idThread++) {
			Thread tCompra = new Bilheteria(idThread, semaforo);
			tCompra.start();
		}
	}

}