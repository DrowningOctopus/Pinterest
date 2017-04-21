package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

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
