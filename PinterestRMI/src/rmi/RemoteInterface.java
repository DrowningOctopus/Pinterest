package rmi;

import java.rmi.Remote;

import pinterest.Tableau;

public interface RemoteInterface extends Remote {
	//() throws RemoteException;
	
	public void validerCreationTableau(Tableau t);
	
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
