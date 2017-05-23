package pinterest;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;

import utilisateurs.Ustandard;

/**
 * Simple objet contenant les informations utiles d'un tableau.
 */
public class Tableau implements Remote, Serializable {
	private static final long serialVersionUID = 1L;
	public int numero;
	public String nom;
	public Ustandard createur;
	public ArrayList<Ustandard> administrateurs;
	public ArrayList<Epingle> epingles;
	
	public Tableau(int num, String n, Ustandard c, ArrayList<Ustandard> m, ArrayList<Epingle> e) {
		this.numero = num;
		this.nom = n;
		this.createur = c;
		this.administrateurs = m;
		this.epingles = e;
	}
	
	public void ajouterEpingle(Epingle e) {
		this.epingles.add(e);
	}
	
	public void supprimerEpingle(Epingle e) {
		this.epingles.remove(e);
	}

	public void modifierNom() {
		String choix = "abcdefghijklmnopqrstuvwxyz1234567890";
        int longueur = (int)(Math.random()*15+1);
        String nom = "";
        for (int i = 0 ; i < longueur ; i++) {
            int index = (int) (Math.random() * choix.length());
            nom += choix.charAt(index);
        }
        this.nom = nom;
	}
	
	public boolean estDejaAdministrePar(Ustandard u) {
		boolean resu = false;
		int i = 0;
		while (!resu && i < this.administrateurs.size()) {
			resu = this.administrateurs.get(i).nom.equals(u.nom);
			i++;
		}
		return resu;
	}
}
