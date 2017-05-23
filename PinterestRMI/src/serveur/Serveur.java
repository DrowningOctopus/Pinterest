package serveur;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import pinterest.Epingle;
import pinterest.Tableau;
import rmiside.RemoteClientInterface;
import utilisateurs.Ustandard;
import utilisateurs.Utilisateur;

/**
 * Classe representant le serveur de Pinterest.
 * C'est elle qui garde en memoire l'etat des utilisateurs et des epingles.
 */
public class Serveur {
	public int nbEpingle = 0;
	public ArrayList<RemoteClientInterface> utilisateurs;
	public ArrayList<Epingle> epingles;
	public HashMap<String, Tableau> tableaux;
	
	public Serveur(ArrayList<RemoteClientInterface> u, ArrayList<Epingle> e, HashMap<String, Tableau> t) {
		this.epingles = e;
		this.utilisateurs = u;
		this.tableaux = t;
	}
	
	public void synchroniser(Utilisateur u) {
		System.out.println("Le serveur synchronise le fil pour "+ u.nom);
	}
	
	public void enregistrerChangements(Utilisateur u) {
		System.out.println("Le serveur enregistre le changement effectue par "+ u.nom);
	}
	
	public void run() throws RemoteException{
		double v;
		while (true) {
			v = Math.random();
			if (utilisateurs.size() > 0 && v < 0.1) {
				System.out.println("premiere page ???");
				int idUser = (int)(Math.random()*utilisateurs.size());
				Ustandard u = utilisateurs.get(idUser).donnerClient();
				String tNom = u.donnerTableauCelebre();
				if (tNom.equals("*")) {
					System.out.println("Le serveur n'a pas trouve de tableau chez "+u.nom+" a mettre en premiere page");
				} else {
					System.out.println("Le serveur met en premiere page le tableau le plus celebre de "+u.nom+" : "+tNom);
				}
			} else if (utilisateurs.size() > 0 && v < 0.2) {
				System.out.println("rang ???");
				int idUser = (int)(Math.random()*utilisateurs.size());
				Ustandard u = utilisateurs.get(idUser).donnerClient();
				Rang rang = u.rang.changer();
				System.out.println("Le serveur change le rang de "+u.nom+" de "+u.rang+" a "+rang);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
