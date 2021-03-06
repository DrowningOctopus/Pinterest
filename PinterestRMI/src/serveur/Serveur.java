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
 * C'est elle qui garde en memoire l'etat des utilisateurs, des tableaux et des epingles.
 * 
 * Sa methode run le fait agir de temps en temps, soit pour choisir de mettre en premiere
 * page le tableau le plus celebre d'un utilisateur au hasard, soit pour modifier le rang
 * d'un utilisateur.
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
	
	public RemoteClientInterface donnerClient(Ustandard u) throws RemoteException {
		int i = 0;
		while (i < utilisateurs.size() && !u.nom.equals(utilisateurs.get(i).donnerClient().nom)) {
			i++;
		}
		return utilisateurs.get(i);
	}
	
	public void run() throws RemoteException{
		double v;
		while (true) {
			v = Math.random();
			if (utilisateurs.size() > 0 && v < 0.5) {
				int idUser = (int)(Math.random()*utilisateurs.size());
				Ustandard u = utilisateurs.get(idUser).donnerClient();
				String tNom = u.donnerTableauCelebre();
				if (tNom.equals("*")) {
					System.out.println("Le serveur n'a pas trouve de tableau chez "+u.nom+" a mettre en premiere page");
				} else {
					System.out.println("Le serveur met en premiere page le tableau le plus celebre de "+u.nom+" : "+tNom);
				}
			} else if (utilisateurs.size() > 0) {
				int idUser = (int)(Math.random()*utilisateurs.size());
				Ustandard u = utilisateurs.get(idUser).donnerClient();
				Rang rang = u.rang.changer();
				System.out.println("Le serveur change le rang de "+u.nom+" de "+u.rang+" a "+rang);
				u.rang = rang;
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
