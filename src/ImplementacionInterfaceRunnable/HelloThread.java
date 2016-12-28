package ImplementacionInterfaceRunnable;

public class HelloThread implements Runnable { //Bueno si se quiere heredar
	Thread t;

	HelloThread() {
		t = new Thread(this, "Nuevo Thread");
		System.out.println("Creado hilo: " + t);
		t.start(); //Arranca el nuevo hilo de ejecuci�n. Ejecuta run
	}

	public void run() { //Metodo para crear hilos
		//C�digo a ejecutar por el hilo
		System.out.println("Hola desde el hilo creado!");
		System.out.println("Hilo finalizando.");
	}
}