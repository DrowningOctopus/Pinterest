package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pinterest.Epingle;
import pinterest.Tableau;
import serveur.Serveur;
import utilisateurs.Ustandard;

public class RemoteServer implements RemoteInterface {
	private Serveur serveur;
	
	public RemoteServer() {
		serveur = new Serveur(new ArrayList<Ustandard>(), new ArrayList<Epingle>());
	}
	
	public static void main(String args[]) {
        try {
            RemoteServer self = new RemoteServer();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(self, 0);
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
		return "OK";
	}

	@Override
	public void repererClient(Ustandard u) {
		serveur.utilisateurs.add(u);
	}

	@Override
	public void validerCreationTableau(Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la creation du tableau "+t.nom);
	}

	@Override
	public void validerModificationTableau(Tableau t, String s, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la modification du tableau "+t.nom);
	}

	@Override
	public void validerPartageTableau(Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide le partage du tableau "+t.nom+" avec "+u.nom);
	}

	@Override
	public void validerCreationEpingle(Epingle e, Ustandard u) {
		serveur.enregistrerChangements(u);
		serveur.epingles.add(e);
		serveur.nbEpingle++;
		System.out.println("Le serveur valide la creation de l'epingle "+e.numero);
	}

	@Override
	public void validerAjoutEpingle(Epingle e, Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide l'ajout de l'epingle "+e.numero +" du tableau "+t.nom);
	}

	@Override
	public void validerSuppressionEpingle(Epingle e, Tableau t, Ustandard u) {
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la suppression de l'epingle "+e.numero +" du tableau "+t.nom);
	}

	@Override
	public int donnerNbEpingles() throws RemoteException {
		return serveur.nbEpingle;
	}
}
