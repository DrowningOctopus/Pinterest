package rmiside;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;
import rmiside.RemoteServerInterface;
import serveur.Rang;

public class RemoteClient implements RemoteClientInterface {
	private Ustandard client;
	
	private RemoteClient() {
		System.err.println("Client ready");
	}

	private void setClient(RemoteServerInterface ri) throws RemoteException {
		client = new Ustandard(ri);
	}
	
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
        	RemoteClient self = new RemoteClient();
            Registry registry = LocateRegistry.getRegistry(host);
            RemoteServerInterface stub = (RemoteServerInterface) registry.lookup("RemoteInterface");
            self.setClient(stub);
            stub.repererClient(self);
            // bad practice
            /*while (true) {
            	self.client.agir();
            }*/
            self.client.run();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public Ustandard donnerClient() throws RemoteException {
		return client;
	}

	@Override
	public String donnerNomTableauLePlusCelebre() throws RemoteException {
		return this.client.donnerTableauCelebre();
	}

	@Override
	public Rang donnerRang() throws RemoteException {
		return this.client.rang;
	}

	@Override
	public void changerRang(Rang r) throws RemoteException {
		this.client.rang = r;
	}
}
