package serveur;

import java.util.ArrayList;

import pinterest.Epingle;
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
	
	public Serveur(ArrayList<Ustandard> u, ArrayList<Epingle> e) {
		this.epingles = e;
		this.utilisateurs = u;
	}
	
	public void synchroniser(Utilisateur u) {
		System.out.println("Le serveur synchronise le fil pour "+ u.nom);
	}
	
	public void enregistrerChangements(Utilisateur u) {
		System.out.println("Le serveur enregistre le changement effectue par "+ u.nom);
	}	
}
