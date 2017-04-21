package utilisateurs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import messagerie.Message;
import messagerie.Messagerie;
import rmi.RemoteInterface;
import pinterest.Epingle;
import pinterest.Tableau;

/**
 * Utilisateur standard du reseau social.
 * Il peut creer et modifier des tableaux, creer des epingles, ajouter et retirer des epingles
 * de ses tableaux, partager ses tableaux avec d'autres utilisateurs standards et envoyer des
 * messages a d'autres utilisateurs standards.
 */
public class Ustandard extends Utilisateur{
	private int nbTableaux = 0;
	
	public Ustandard(RemoteInterface s, String nom) {
		super(s, nom);
	}

	private void parcourirFil() {
		try {
			this.serveur.synchroniser(this);
			System.out.println(this.nom + " parcourt son fil");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/* Methodes de tableaux */
	private void creerTableau() {
		try {
			Tableau t = new Tableau(this.nbTableaux, "", this, new ArrayList<Ustandard>(), new ArrayList<Epingle>());
			this.nbTableaux++;
			this.tableaux.add(t);
			t.administrateurs.add(this);
			this.serveur.validerCreationTableau(t, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void modifierTableau(Tableau t) {
		try {
			String n = t.nom;
			t.modifierNom();
			this.serveur.validerModificationTableau(t, t.nom, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void partagerTableau(Tableau t, Ustandard u) {
		try {
			u.tableaux.add(t);
			t.administrateurs.add(u);
			this.serveur.validerPartageTableau(t, u);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/* Methodes d'epingles */
	private void creerEpingle() {
		try {
			int numE = this.serveur.donnerNbEpingles();
			Epingle e = new Epingle(numE, "", this, new ArrayList<Tableau>());
			this.serveur.validerCreationEpingle(e, this);
			if (this.tableaux.size() > 0 && (int)(Math.random()*2) == 0) {
				int numT = (int)(Math.random()*this.tableaux.size());
				Tableau t = this.tableaux.get(numT);
				t.ajouterEpingle(e);
				this.serveur.validerAjoutEpingle(e, t, this);
			}
			this.epinglesCreees.add(e);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void ajouterEpingle(Epingle ep, Tableau t) {
		try {
			t.epingles.add(ep);
			this.serveur.validerAjoutEpingle(ep, t, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void supprimerEpingle(Epingle ep, Tableau t) {
		try {
			t.epingles.remove(ep);
			this.serveur.validerSuppressionEpingle(ep, t, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/* Methodes de messagerie */
	/*
	private void ecrireMessage(Ustandard destinataire) {
		Message message = new Message(this, destinataire);
		System.out.println(this.nom+" a ecrit le message '"+message+"' pour "+destinataire.nom);
		Messagerie.getInstance().recupererMessage(message);
	}

	public void recevoirMessage(Utilisateur emetteur, String message) {
		System.out.println(this.nom+" a recu le message '"+message+"' de "+emetteur.nom);
	}

	public void recevoirNotifLu(Utilisateur destinataire) {
		System.out.println(this.nom+" a recu une notification 'Lu' de "+destinataire.nom);
	}
	*/
	
	/**
	 * L'utilisateur a le choix entre diverses actions :
	 * - creer une epingle
	 * - creer un tableau
	 * - modifier un tableau
	 * - partager un tableau avec quelqu'un
	 * - ajouter une epingle a un tableau
	 * - supprimer une epingle d'un tableau
	 * - se deconnecter
	 * - envoyer un message a quelqu'un
	 * - parcourir son fil
	 */
	public void agir() {
		try {
			int i = (int)(Math.random()*100);
			if(i < 15) {
				this.creerEpingle();
			} else if (i < 20) {
				this.creerTableau();
			} else if (i < 25 && this.tableaux.size() > 0) {
				this.modifierTableau(this.tableaux.get((int)(Math.random()*this.tableaux.size())));
			} else if (i < 30 && this.tableaux.size() > 0 && this.serveur.donnerNbUtilisateurs() > 1) {
				int idUser = (int)(Math.random()*this.serveur.donnerNbUtilisateurs());
				if (! this.serveur.donnerUtilisateur(idUser).equals(this)) {
					this.partagerTableau(this.tableaux.get((int)(Math.random()*this.tableaux.size())), this.serveur.donnerUtilisateur(idUser));
				}
			} else if (i < 45 && this.serveur.donnerNbEpingles() > 0 && this.tableaux.size() > 0) {
				this.ajouterEpingle(this.serveur.donnerEpingle((int)(Math.random()*this.serveur.donnerNbEpingles())), this.tableaux.get((int)(Math.random()*this.tableaux.size())));
			} else if (i < 50 && this.serveur.donnerNbEpingles() > 0 && this.tableaux.size() > 0) {
				int t = (int)(Math.random()*this.tableaux.size());
				if (this.tableaux.get(t).epingles.size() > 0) {
					this.supprimerEpingle(this.tableaux.get(t).epingles.get((int)(Math.random()*this.tableaux.get(t).epingles.size())), this.tableaux.get(t));
				}
			} else if (i < 60) {
				this.deconnecter();
			/*} else if (i < 70 && this.serveur.utilisateurs.size() > 1) {
				int j = (int)(Math.random()*this.serveur.utilisateurs.size());
				if (! this.serveur.utilisateurs.get(j).equals(this)) {
					synchronized(Messagerie.getInstance()) {
						ecrireMessage(this.serveur.utilisateurs.get(j));
					}
				}*/
			} else {
				this.parcourirFil();
			}
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
