package rmiside;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientInterface extends Remote {
	public String donnerNomTableauLePlusCelebre() throws RemoteException;
	public String donnerRang() throws RemoteException;
	public void changerRang(String r) throws RemoteException;
}
