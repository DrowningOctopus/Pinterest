package utilisateurs;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import pinterest.Epingle;
import rmiside.RemoteServerInterface;
import serveur.Rang;

/**
 * Classe abstraite representant le comportement de base des utilisateurs : se connecter et se
 * deconnecter, et agir en consequence.
 * C'est elle qui implemente l'interface Runnable et qui possede donc le code de la methode run().
 */
public abstract class Utilisateur implements Serializable, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RemoteServerInterface serveur;
	public String nom;
	ArrayList<String> tableaux = new ArrayList<String>();
	ArrayList<Epingle> epinglesCreees = new ArrayList<Epingle>();
	public boolean connecte = false;
	public boolean actif = true;
	public Rang rang;
	
	public Utilisateur(RemoteServerInterface s, String nom) throws RemoteException {
		this.nom = nom;
		this.serveur = s;
		this.rang = Rang.NOUVEAU;
	}
	
	protected synchronized void connecter() {
		System.out.println(this.nom +" se connecte");
		this.connecte = true;
		notifyAll();
	}

	protected synchronized void deconnecter(){
		System.out.println(this.nom +" se deconnecte");
		this.connecte = false;
		notifyAll();
	}
	
	public String donnerTableauCelebre() {
		if (this.tableaux.size() > 0) {
			int idTableau = (int)(Math.random()*this.tableaux.size());
			return this.tableaux.get(idTableau);
		} else {
			return "*";
		}
	}
	
	public abstract void agir() throws RemoteException;
	
	public void run(){
		System.out.println(this.nom + " commence");
		while (true) {
			if (this.actif) {
				if (this.connecte) {
					try {
						this.agir();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Thread.sleep((int)(Math.random()*2000));
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
