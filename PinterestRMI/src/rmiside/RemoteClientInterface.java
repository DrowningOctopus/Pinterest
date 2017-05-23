package rmiside;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.Rang;
import utilisateurs.Ustandard;

public interface RemoteClientInterface extends Remote {
	public Ustandard donnerClient() throws RemoteException;
	public String donnerNomTableauLePlusCelebre() throws RemoteException;
	public Rang donnerRang() throws RemoteException;
	public void changerRang(Rang r) throws RemoteException;
}
