package HerenciaClaseThread;

public class HelloThread extends Thread { //No se puede heredar en un futuro
	public void run() {
		//C�digo a ejecutar por el hilo
		System.out.println("Hola desde el hilo creado!");
	}
}