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

/**
 * Implementation du serveur RMI de notre projet.
 * Le port utilise par defaut est 1091.
 * 
 * Les informations sur l'identite des utilisateurs sont recuperees par le serveur a
 * leur instanciation, via la methode repererClient.
 * 
 * Voir l'interface correspondante pour quelques remarques de plus a propos des methodes.
 */
public class RemoteServer implements RemoteServerInterface {
	private Serveur serveur;
	
	public RemoteServer() {
		serveur = new Serveur(new ArrayList<RemoteClientInterface>(), new ArrayList<Epingle>(), new HashMap<String, Tableau>());
	}
	
	public static void main(String args[]) {
		int port = (args.length < 1) ? 1091 : Integer.parseInt(args[0]);
		try {
            RemoteServer self = new RemoteServer();
            RemoteServerInterface stub = (RemoteServerInterface) UnicastRemoteObject.exportObject(self, 0);
            Registry registry = LocateRegistry.getRegistry(port);
            registry.bind("RemoteServerInterface", stub);
            System.err.println("Server ready");
            self.serveur.run();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public void repererClient(RemoteClientInterface u) {
		System.out.println("reperons le client");
		System.out.println("serveur : "+serveur);
		System.out.println("serveur.utilisateurs : "+serveur.utilisateurs);
		serveur.utilisateurs.add(u);
		System.out.println("le client est repere");
	}

	@Override
	public void validerCreationTableau(Tableau t, Ustandard u) {
		serveur.tableaux.put(t.nom, t);
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la creation du tableau "+t.nom + " par " + u.nom);
	}

	@Override
	public void validerModificationTableau(Tableau t, String s, Ustandard u) {
		Tableau tab = serveur.tableaux.get(t.nom);
		serveur.tableaux.remove(t.nom);
		serveur.tableaux.put(s, tab);
		serveur.enregistrerChangements(u);
		System.out.println("Le serveur valide la modification du tableau "+t.nom + " par " + u.nom);
	}

	@Override
	public void validerPartageTableau(Tableau t, Ustandard uC, Ustandard uR) throws RemoteException {
		RemoteClientInterface receveur = serveur.donnerClient(uR);
		receveur.recevoirPartageTableau(t.nom, uC);
		t.administrateurs.add(uR);
		serveur.enregistrerChangements(uC);
		System.out.println("Le serveur valide le partage du tableau " + t.nom + " de " + uC.nom + " avec "+uR.nom);
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
		return serveur.utilisateurs.get(i).donnerClient();
	}

	@Override
	public Tableau donnerTableau(String nom) throws RemoteException {
		return this.serveur.tableaux.get(nom);
	}

	@Override
	public void synchroniser(Ustandard u) throws RemoteException {
		this.serveur.synchroniser(u);
	}
}
