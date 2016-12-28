package UsoSynchronized;

class Contador {
	public int c;

	public Contador() {
		this.c = 0;
	}

	public synchronized void sumar() { //El synchronized no puede ir en el run()
		c++;
	}

	public synchronized void restar() {
		c--;
	}

	public int getContador() {
		return c;
	}
}

class Suma extends Thread {
	Contador count;

	public Suma(Contador count) {
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < 1000; i++)
			count.sumar();
	}
}

class Resta extends Thread {
	Contador count;

	public Resta(Contador count) {
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < 1000; i++)
			count.restar();
	}
}

public class SynchronizedCorrer {
	public static void main(String[] args) throws InterruptedException {
		Contador c = new Contador();
		Thread s1 = new Suma(c);
		Thread r1 = new Resta(c);
		s1.start();
		r1.start();
		s1.join();
		r1.join();
		System.out.println("El resultado final es: " + c.getContador());
	}
}