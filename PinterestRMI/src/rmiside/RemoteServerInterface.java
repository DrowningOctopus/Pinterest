package rmiside;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

/**
 * Interface du serveur de notre projet.
 * Les methodes validerCreationTableau, validerModificationTableau, validerCreationEpingle
 * et validerPartageTableau notamment, sont appelees par les RemoteClient pour modifier
 * l'etat du serveur.
 */
public interface RemoteServerInterface extends Remote {
	public void repererClient(RemoteClientInterface u) throws RemoteException;
	
	public void synchroniser(Ustandard u) throws RemoteException;
	public void validerCreationTableau(Tableau t, Ustandard u) throws RemoteException;
	public void validerModificationTableau(Tableau t, String s, Ustandard u) throws RemoteException;
	public void validerPartageTableau(Tableau t, Ustandard uC, Ustandard uR) throws RemoteException;
	public Tableau donnerTableau(String nom) throws RemoteException;
	
	public int donnerNbUtilisateurs() throws RemoteException;
	public Ustandard donnerUtilisateur(int i) throws RemoteException;
	
	public void validerCreationEpingle(Epingle e, Ustandard u) throws RemoteException;
	public void validerAjoutEpingle(Epingle e, Tableau t, Ustandard u) throws RemoteException;
	public void validerSuppressionEpingle(Epingle e, Tableau t, Ustandard u) throws RemoteException;
	public int donnerNbEpingles() throws RemoteException;
	public Epingle donnerEpingle(int i) throws RemoteException;
}
