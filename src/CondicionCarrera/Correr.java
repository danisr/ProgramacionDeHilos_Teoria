package CondicionCarrera;

class Contador {
	public static int c;
}

class Suma extends Thread {
	synchronized public void run() {
		for (int i = 0; i < 1000; i++)
			Contador.c++;
	}
}

class Resta extends Thread {
	synchronized public void run() {
		for (int i = 0; i < 1000; i++)
			Contador.c--;
	}
}

public class Correr {
	public static void main(String[] args) throws InterruptedException {
		Contador.c = 0;
		Thread s1 = new Suma();
		Thread r1 = new Resta();
		s1.start();
		r1.start();
		s1.join();
		r1.join();
		System.out.println("El resultado final es: " + Contador.c);
	}
}