package pinterest;

import java.util.ArrayList;

import utilisateurs.Ustandard;

/**
 * Simple objet contenant les informations utiles d'une epingle.
 */
public class Epingle {
	public int numero;
	public String commentaire;
	public Ustandard createur;
	public ArrayList<Tableau> tableaux;
	
	public Epingle(int n, String c, Ustandard cr, ArrayList<Tableau> t) {
		this.numero = n;
		this.commentaire = c;
		this.createur  = cr;
		this.tableaux = t;
	}
}
