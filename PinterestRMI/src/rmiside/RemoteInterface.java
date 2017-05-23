package rmiside;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

/*
Il faut imperativement, pour le DM, des echanges dans les deux sens : il faut donc trouver au moins une
fonctionnalite necessitant que le serveur interroge le client.

Idee : de temps en temps, le serveur envoie une notification a un client :
"Cette epingle vous interesse-t-elle ?"

... Est-ce que par hasard, il ne faudrait pas que RemoteClient implemente egalement une interface qui etend
Remote, pour pouvoir par exemple transmettre son attribut client ?

Question : faut-il garder le cote thread / runnable ou le supprimer ??
*/

public interface RemoteInterface extends Remote {
	public String check() throws RemoteException;
	public void repererClient(Ustandard u) throws RemoteException;
	
	public void synchroniser(Ustandard u) throws RemoteException;
	public void validerCreationTableau(Tableau t, Ustandard u) throws RemoteException;
	public void validerModificationTableau(Tableau t, String s, Ustandard u) throws RemoteException;
	public void validerPartageTableau(Tableau t, Ustandard u) throws RemoteException;
	
	public int donnerNbUtilisateurs() throws RemoteException;
	public Ustandard donnerUtilisateur(int i) throws RemoteException;
	
	public void validerCreationEpingle(Epingle e, Ustandard u) throws RemoteException;
	public void validerAjoutEpingle(Epingle e, Tableau t, Ustandard u) throws RemoteException;
	public void validerSuppressionEpingle(Epingle e, Tableau t, Ustandard u) throws RemoteException;
	public int donnerNbEpingles() throws RemoteException;
	public Epingle donnerEpingle(int i) throws RemoteException;
	public Tableau donnerTableau(String nom) throws RemoteException;
}
