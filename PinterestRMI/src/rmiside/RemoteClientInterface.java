package rmiside;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.Rang;
import utilisateurs.Ustandard;

/**
 * Interface des clients de notre projet.
 * Les methodes changerRang et recevoirPartageTableau, notamment, sont appelees par le
 * RemoteServer pour modifier l'etat d'un utilisateur.
 */
public interface RemoteClientInterface extends Remote {
	public Ustandard donnerClient() throws RemoteException;
	public String donnerNomTableauLePlusCelebre() throws RemoteException;
	public Rang donnerRang() throws RemoteException;
	public void changerRang(Rang r) throws RemoteException;
	public void recevoirPartageTableau(String n, Ustandard u) throws RemoteException;
}
