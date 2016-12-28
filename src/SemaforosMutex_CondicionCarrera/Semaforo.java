package SemaforosMutex_CondicionCarrera;

import java.util.concurrent.Semaphore;

class Contador2 {
	public static int c;
}

class Suma2 extends Thread {
	Semaphore SemaforoSuma;

	public Suma2(Semaphore SemaforoSuma) {
		this.SemaforoSuma = SemaforoSuma;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				SemaforoSuma.acquire(); //Semáforo--. Si semáforo es menor a 0, le pasa a la cola y bloquea la tarea
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Contador2.c++;
			SemaforoSuma.release(); //Semáforo++. Si semáforo <= a 0, saca al primero de la lista de espera
		}
	}
}

class Resta2 extends Thread {
	Semaphore SemaforoResta;

	public Resta2(Semaphore SemaforoResta) {
		this.SemaforoResta = SemaforoResta;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				SemaforoResta.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Contador2.c--;
			SemaforoResta.release();
		}
	}
}

public class Semaforo {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(1); //Hay 1 cuarto de baño y libre
		Contador2.c = 0;
		Thread s1 = new Suma2(semaforo);
		Thread r1 = new Resta2(semaforo);
		s1.start(); 
		r1.start();
		s1.join(); //El main espera a s1 y luego a r1
		r1.join();
		System.out.println("El resultado final es: " + Contador2.c);
	}
}