package GestionInterrupciones;

public class Dormilon extends Thread {
	int siesta;
	int intervalo;

	public Dormilon(String nombre, int siesta) {
		super (nombre); //Se pasa nombre a hijo
		//this.setName("Marquitos"); //Otra forma de poner un nombre
		this.siesta = siesta;
	}

	public void run() {
		try {
			System.out.println("El ni�o se echa a dormir");
			Thread.sleep(siesta);
		} catch (InterruptedException e) {
			System.out.println("Siesta interrumida por mam�");
			//return; //Con este return termina el proceso
		}
		if (Thread.interrupted()) {
			System.out.println("El ni�o llora");
		} else {
			System.out.println("El ni�o se despierta solito");
		}
	}

	public static void main(String[] args) {
		int siesta = 600; //Hijo
		int despertador = 500; //Madre
		System.out.println("La mam� acuesta al ni�o");
		Thread nene = new Dormilon("Luisito", siesta); //Se pasa par�metro al constructor
		nene.start();
		try {
			Thread.sleep(despertador); //Madre, se duerme hasta 500
			nene.interrupt();
			if (nene.isAlive()) { //Da true si el ni�o no ha muerto
				System.out.println("Voy a despertar al peque");
				nene.interrupt();
			}
			if (nene.isInterrupted()) {
				System.out.println("La mam� ha despertado al nene");
			}
			nene.join(); //La madre espera a que el ni�o termine, si se quita: la madre sigue y el hijo termina despu�s
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de la historia");
	}
}