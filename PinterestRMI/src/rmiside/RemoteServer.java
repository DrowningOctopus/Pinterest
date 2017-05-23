package rmiside;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import pinterest.Epingle;
import pinterest.Tableau;
import serveur.Serveur;
import utilisateurs.Ustandard;

public class RemoteServer implements RemoteServerInterface {
	private Serveur serveur;
	
	public RemoteServer() {
		serveur = new Serveur(new ArrayList<Ustandard>(), new ArrayList<Epingle>(), new HashMap<String, Tableau>());
	}
	
	public static void main(String args[]) {
        try {
            RemoteServer self = new RemoteServer();
            RemoteServerInterface stub = (RemoteServerInterface) UnicastRemoteObject.exportObject(self, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public String check() {
		return "* Serveur OK *";
	}

	@Override
	public void repererClient(Ustandard u) {
		System.out.println("reperons le client");
		System.out.println("serveur : "+serveur);
		System.out.println("serveur.utilisateurs : "+serveur.utilisateurs);
		serveur.utilisateurs.add(u);
		System.out.println("le client est repere");
	}

	@Override
	public void validerCreationTableau(Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la creation du tableau "+t.nom + " par " + u.nom);
	}

	@Override
	public void validerModificationTableau(Tableau t, String s, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la modification du tableau "+t.nom + " par " + u.nom);
	}

	@Override
	public void validerPartageTableau(Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide le partage du tableau " + t.nom + " de" + t.createur.nom + " avec "+u.nom);
	}

	@Override
	public void validerCreationEpingle(Epingle e, Ustandard u) {
		serveur.enregistrerChangements(u);
		serveur.epingles.add(e);
		serveur.nbEpingle++;
		System.out.println("Le serveur valide la creation de l'epingle "+e.numero + " par " + u.nom);
	}

	@Override
	public void validerAjoutEpingle(Epingle e, Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide l'ajout de l'epingle "+e.numero +" du tableau " + t.nom + " par " + u.nom);
	}

	@Override
	public void validerSuppressionEpingle(Epingle e, Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la suppression de l'epingle " +e.numero + " du tableau " + t.nom + " par " + u.nom);
	}

	@Override
	public int donnerNbEpingles() throws RemoteException {
		return serveur.nbEpingle;
	}

	@Override
	public Epingle donnerEpingle(int i) throws RemoteException {
		return serveur.epingles.get(i);
	}

	@Override
	public int donnerNbUtilisateurs() throws RemoteException {
		return serveur.utilisateurs.size();
	}

	@Override
	public Ustandard donnerUtilisateur(int i) throws RemoteException {
		return serveur.utilisateurs.get(i);
	}

	@Override
	public void synchroniser(Ustandard u) throws RemoteException {
		this.serveur.synchroniser(u);
	}

	@Override
	public Tableau donnerTableau(String nom) throws RemoteException {
		return this.serveur.tableaux.get(nom);
	}
}
