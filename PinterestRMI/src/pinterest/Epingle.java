package pinterest;

import java.io.Serializable;
import java.rmi.Remote;

import utilisateurs.Ustandard;

/**
 * Simple objet contenant les informations utiles d'une epingle.
 */
public class Epingle implements Remote, Serializable {
	private static final long serialVersionUID = 1L;
	public int numero;
	public String commentaire;
	public Ustandard createur;
	
	public Epingle(int n, String c, Ustandard cr) {
		this.numero = n;
		this.commentaire = c;
		this.createur  = cr;
	}
}
