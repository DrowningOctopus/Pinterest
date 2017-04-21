package rmi;

import java.rmi.Remote;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

public interface RemoteInterface extends Remote {
	public String check();
	
	//() throws RemoteException;
	
	public void validerCreationTableau(Tableau t);
	
	public void validerModificationTableau(Tableau t, String s);
	
	public void validerPartageTableau(Tableau t, Ustandard u);
		
	
	public void validerCreationEpingle(Epingle e);
	
	public void validerAjoutEpingle(Epingle e, Tableau t);
	
	public void validerSuppressionEpingle(Epingle e, Tableau t);
	
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
