package serveur;

import java.util.ArrayList;
import java.util.HashMap;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;
import utilisateurs.Utilisateur;

/**
 * Classe representant le serveur de Pinterest.
 * C'est elle qui garde en memoire l'etat des utilisateurs et des epingles.
 */
public class Serveur {
	public int nbEpingle = 0;
	public ArrayList<Ustandard> utilisateurs;
	public ArrayList<Epingle> epingles;
	public HashMap<String, Tableau> tableaux;
	
	public Serveur(ArrayList<Ustandard> u, ArrayList<Epingle> e, HashMap<String, Tableau> t) {
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
}
