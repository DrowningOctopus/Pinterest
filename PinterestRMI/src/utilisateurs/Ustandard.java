package utilisateurs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiside.RemoteServerInterface;
import pinterest.Epingle;
import pinterest.Tableau;

/**
 * Utilisateur standard du reseau social.
 * Il peut creer et modifier des tableaux, creer des epingles, ajouter et retirer des epingles
 * de ses tableaux, partager ses tableaux avec d'autres utilisateurs standards et parcourir son
 * fil d'actualite.
 */
public class Ustandard extends Utilisateur {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Ustandard(RemoteServerInterface s, String nom) throws RemoteException {
		super(s, nom);
	}

	public Ustandard(RemoteServerInterface s) throws RemoteException {
		super(s, "");
		String choix = "abcdefghijklmnopqrstuvwxyz";
        int longueur = (int)(Math.random()*3+1);
        String nom = "";
        for (int i = 0 ; i < longueur ; i++) {
            int index = (int) (Math.random() * choix.length());
            nom += choix.charAt(index)+".";
        }
        this.nom = nom.toUpperCase();
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
			Tableau t = new Tableau(this, new ArrayList<Ustandard>(), new ArrayList<Epingle>());
			this.tableaux.add(t.nom);
			t.administrateurs.add(this);
			this.serveur.validerCreationTableau(t, this);
			System.out.println(this.nom+" cree le tableau "+t.nom);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void modifierTableau(String nomT) {
		try {
			Tableau t = this.serveur.donnerTableau(nomT);
			t.modifierNom();
			this.serveur.validerModificationTableau(t, t.nom, this);
			System.out.println(this.nom+" renomme le tableau "+nomT+" en "+t.nom);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void partagerTableau(Tableau t, Ustandard u) {
		try {
			this.serveur.validerPartageTableau(t, this, u);
			System.out.println(this.nom+" partage le tableau "+t.nom+" avec "+u.nom);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void recevoirPartageTableau(String n, Ustandard u) {
		tableaux.add(n);
		System.out.println(this.nom+" recoit les droits du tableau "+n+" de "+u.nom);
	}
	
	/* Methodes d'epingles */
	private void creerEpingle() throws RemoteException {
		try {
			int numE = this.serveur.donnerNbEpingles();
			Epingle e = new Epingle(numE, "", this);
			this.serveur.validerCreationEpingle(e, this);
			if (this.tableaux.size() > 0 && (int)(Math.random()*2) == 0) {
				int numT = (int)(Math.random()*this.tableaux.size());
				String nomT = this.tableaux.get(numT);
				this.serveur.donnerTableau(nomT).ajouterEpingle(e);
				System.out.println(this.nom+" ajoute l'epingle "+numE+" au tableau "+nomT);
				this.serveur.validerAjoutEpingle(e, this.serveur.donnerTableau(nomT), this);
			}
			this.epinglesCreees.add(e);
			System.out.println(this.nom+" cree l'epingle "+numE);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void ajouterEpingle(Epingle ep, String nomT) {
		try {
			Tableau t = this.serveur.donnerTableau(nomT);
			t.epingles.add(ep);
			this.serveur.validerAjoutEpingle(ep, t, this);
			System.out.println(this.nom+" ajoute l'epingle "+ep.numero+" au tableau "+t.nom);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void supprimerEpingle(Epingle ep, String nomT) {
		try {
			Tableau t = this.serveur.donnerTableau(nomT);
			t.epingles.remove(ep);
			this.serveur.validerSuppressionEpingle(ep, t, this);
			System.out.println(this.nom+" supprime l'epingle "+ep.numero+" du tableau "+t.nom);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * L'utilisateur a le choix entre diverses actions :
	 * - se deconnecter
	 * - creer une epingle
	 * - creer un tableau
	 * - modifier un tableau
	 * - partager un tableau avec quelqu'un
	 * - ajouter une epingle a un tableau
	 * - supprimer une epingle d'un tableau
	 * - parcourir son fil d'actualites
	 */
	public void agir() {
		try {
			if (!connecte) {
				this.connecter();
			} else {
				int i = (int)(Math.random()*100);
				if (i < 5) {
					this.deconnecter();
				} else if(i < 20) {
					this.creerEpingle();
				} else if (i < 35) {
					this.creerTableau();
				} else if (i < 45 && this.tableaux.size() > 0) {
					this.modifierTableau(this.tableaux.get((int)(Math.random()*this.tableaux.size())));
				} else if (i < 60 && this.tableaux.size() > 0 && this.serveur.donnerNbUtilisateurs() > 1) {
					int idUser = (int)(Math.random()*this.serveur.donnerNbUtilisateurs());
					String nomT = this.tableaux.get((int)(Math.random()*this.tableaux.size()));
					Ustandard u = this.serveur.donnerUtilisateur(idUser);
					Tableau t = this.serveur.donnerTableau(nomT);
					if (!t.estDejaAdministrePar(u)) {
						this.partagerTableau(t, u);
					}
				} else if (i < 80 && this.serveur.donnerNbEpingles() > 0 && this.tableaux.size() > 0) {
					this.ajouterEpingle(this.serveur.donnerEpingle((int)(Math.random()*this.serveur.donnerNbEpingles())), this.tableaux.get((int)(Math.random()*this.tableaux.size())));
				} else if (i < 90 && this.serveur.donnerNbEpingles() > 0 && this.tableaux.size() > 0) {
					int t = (int)(Math.random()*this.tableaux.size());
					Tableau tBis = this.serveur.donnerTableau(this.tableaux.get(t));
					if (tBis.epingles.size() > 0) {
						this.supprimerEpingle(tBis.epingles.get((int)(Math.random()*tBis.epingles.size())), this.tableaux.get(t));
					}
				} else {
					this.parcourirFil();
				}
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
