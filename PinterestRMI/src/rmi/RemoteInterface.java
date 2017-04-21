package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

public interface RemoteInterface extends Remote {
	public String check() throws RemoteException;
	
	public void repererClient(Ustandard u) throws RemoteException;
	
	//() throws RemoteException;
	
	public void validerCreationTableau(Tableau t) throws RemoteException;
	
	public void validerModificationTableau(Tableau t, String s) throws RemoteException;
	
	public void validerPartageTableau(Tableau t, Ustandard u) throws RemoteException;
		
	
	public void validerCreationEpingle(Epingle e) throws RemoteException;
	
	public void validerAjoutEpingle(Epingle e, Tableau t) throws RemoteException;
	
	public void validerSuppressionEpingle(Epingle e, Tableau t) throws RemoteException;
	
	/*
	// TODO
	creerTableau()
	modifierTableau(Tableau)
	partagerTableau(Tableau)
	
	
	// TODO
	creerEpingle()
	ajouterEpingle(Epingle, Tableau)
	supprimerEpingle(Epingle, Tableau)
	
	// Plus tard
	parcourirFil ?
	
	// Plus tard
	ecrireMessage(Ustandard)
	recevoirMessage(Utilisateur)
	recevoirNotifLu(Utilisateur)
	
	// Side note
	main = agir
	*/
}
