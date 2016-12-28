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
			System.out.println("El niño se echa a dormir");
			Thread.sleep(siesta);
		} catch (InterruptedException e) {
			System.out.println("Siesta interrumida por mamá");
			//return; //Con este return termina el proceso
		}
		if (Thread.interrupted()) {
			System.out.println("El niño llora");
		} else {
			System.out.println("El niño se despierta solito");
		}
	}

	public static void main(String[] args) {
		int siesta = 600; //Hijo
		int despertador = 500; //Madre
		System.out.println("La mamá acuesta al niño");
		Thread nene = new Dormilon("Luisito", siesta); //Se pasa parámetro al constructor
		nene.start();
		try {
			Thread.sleep(despertador); //Madre, se duerme hasta 500
			nene.interrupt();
			if (nene.isAlive()) { //Da true si el niño no ha muerto
				System.out.println("Voy a despertar al peque");
				nene.interrupt();
			}
			if (nene.isInterrupted()) {
				System.out.println("La mamá ha despertado al nene");
			}
			nene.join(); //La madre espera a que el niño termine, si se quita: la madre sigue y el hijo termina después
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de la historia");
	}
}