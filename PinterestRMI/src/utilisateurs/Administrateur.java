package utilisateurs;

import pinterest.Epingle;
import serveur.Serveur;

/**
 * Utilisateur special ne pouvant pas creer de tableaux ou d'epingles et utilisant un systeme de
 * messagerie specifique a son role de moderateur.
 */
public class Administrateur extends Utilisateur {
	public Administrateur(Serveur s, String nom) {
		super(s, nom);
	}
	
	private void notifierUtilisateur(Ustandard u, Epingle e) {
		System.out.println("Votre epingle " + e.numero + " enfreint nos regles d'utilisation");
		u.repondreNotificationAdmin(this, e);
	}
	
	public void supprimerUtilisateur(Ustandard u) {
		this.serveur.utilisateurs.remove(u);
		u.actif = false;
		System.out.println(this.nom + " a supprime le compte de " + u.nom);
		this.serveur.enregistrerChangements(this);
	}
	
	/**
	 * Ici, l'administrateur a le choix entre ne rien faire ou envoyer une notification de
	 * moderation a un utilisateur standard a propos d'une epingle non conforme.
	 */
	public void agir() {
		try {
			int i = (int)(Math.random()*10);
			if(i==0 && this.serveur.epingles.size() > 0 && this.serveur.utilisateurs.size() > 0) {
				int u = (int)(Math.random()*this.serveur.utilisateurs.size());
				int e = (int)(Math.random()*this.serveur.utilisateurs.get(u).epinglesCreees.size());
				if (this.serveur.utilisateurs.get(u).epinglesCreees.size() > 0){
					this.notifierUtilisateur(this.serveur.utilisateurs.get(u), this.serveur.utilisateurs.get(u).epinglesCreees.get(e));
				}
			} else if(i==1) {
				this.deconnecter();
			} else {
				Thread.sleep(5000);
			} 
		}
		catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
