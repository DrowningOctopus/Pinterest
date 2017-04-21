package pinterest;

import java.util.ArrayList;

import serveur.Serveur;
import utilisateurs.Administrateur;
import utilisateurs.Ustandard;

/**
 * Classe principale contenant le main.
 */
public class Pinterest {

	public static void main(String[] args) {
		
		Serveur s = new Serveur(null, new ArrayList<Ustandard>(), new ArrayList<Epingle>());
		Administrateur greg = new Administrateur(s, "Greg");
		Ustandard sherlock = new Ustandard(s, "Sherlock");
		Ustandard mycroft = new Ustandard(s, "Mycroft");
		Ustandard john = new Ustandard(s, "John");
		
		s.setAdministrateur(greg);
		s.utilisateurs.add(sherlock);
		s.utilisateurs.add(mycroft);
		s.utilisateurs.add(john);
		
		Thread gregThread = new Thread(greg);
		Thread sherlockThread = new Thread(sherlock);
		Thread mycroftThread = new Thread(mycroft);
		Thread johnThread = new Thread(john);
		
		gregThread.start();
		sherlockThread.start();
		mycroftThread.start();
		johnThread.start();
	}
	
}
