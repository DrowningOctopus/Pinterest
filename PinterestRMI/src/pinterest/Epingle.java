package pinterest;

import utilisateurs.Ustandard;

/**
 * Simple objet contenant les informations utiles d'une epingle.
 */
public class Epingle {
	public int numero;
	public String commentaire;
	public Ustandard createur;
	
	public Epingle(int n, String c, Ustandard cr) {
		this.numero = n;
		this.commentaire = c;
		this.createur  = cr;
	}
}
