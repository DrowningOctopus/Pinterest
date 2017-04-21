package utilisateurs;

import java.util.ArrayList;

import pinterest.Epingle;
import pinterest.Tableau;
import rmi.RemoteInterface;

/**
 * Classe abstraite representant le comportement de base des utilisateurs : se connecter et se
 * deconnecter, et agir en consequence.
 * C'est elle qui implemente l'interface Runnable et qui possede donc le code de la methode run().
 */
public abstract class Utilisateur implements Runnable {
	public RemoteInterface serveur;
	public String nom;
	ArrayList<Tableau> tableaux = new ArrayList<Tableau>();
	ArrayList<Epingle> epinglesCreees = new ArrayList<Epingle>();
	public boolean connecte = false;
	public boolean actif = true;
	
	public Utilisateur(RemoteInterface s, String nom) {
		this.nom = nom;
		this.serveur = s;
	}
	
	private synchronized void connecter() {
		System.out.println(this.nom +" se connecte");
		this.connecte = true;
		notifyAll();
	}

	protected synchronized void deconnecter(){
		System.out.println(this.nom +" se deconnecte");
		this.connecte = false;
		notifyAll();
	}
	
	public abstract void agir();
	
	public void run(){
		System.out.println(this.nom + " commence");
		while (true) {
			if (this.actif) {
				if (this.connecte) {
					this.agir();
				} else {
					try {
						Thread.sleep((int)(Math.random()*5000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.connecter();
				}
			} else {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
		
}
