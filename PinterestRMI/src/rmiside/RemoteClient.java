package rmiside;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;
import rmiside.RemoteServerInterface;
import serveur.Rang;

public class RemoteClient implements RemoteClientInterface, Serializable {
	private static final long serialVersionUID = 1L;
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
        	Registry registry;
        	if (args.length < 2) {
        		registry = LocateRegistry.getRegistry(host);
        	} else {
        		int port = Integer.parseInt(args[1]);
        		registry = LocateRegistry.getRegistry(host, port);
        	}
            RemoteServerInterface stub = (RemoteServerInterface) registry.lookup("RemoteServerInterface");
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
