package messagerie;

import java.util.Random;

import utilisateurs.Ustandard;

/**
 * Simple objet encapsulant l'emetteur, le destinataire et le contenu d'un message prive.
 */
public class Message {
	private Ustandard emetteur;
	private Ustandard destinataire;
	private String message;
	
	public Message(Ustandard emetteur, Ustandard destinataire) {
		this.emetteur = emetteur;
		this.destinataire = destinataire;
		Random rand = new Random();
		this.message = "*"+rand.nextInt(100)+"*"+rand.nextInt(100)+"*";
	}
	
	public Ustandard getEmetteur() {
		return emetteur;
	}
	
	public Ustandard getDestinataire() {
		return destinataire;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
