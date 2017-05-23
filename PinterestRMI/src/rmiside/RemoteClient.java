package rmiside;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;
import rmiside.RemoteServerInterface;
import serveur.Rang;

/**
 * Implementation des clients RMI de notre projet.
 * Le port utilise par defaut est 1091.
 * 
 * Par defaut le serveur est suppose etre local, pour tester sur une machine distante, il
 * faut utiliser le menu Run -> Run Configurations pour entrer en argument d'execution
 * l'adresse IPv4 de la machine faisant tourner le serveur.
 * 
 * Voir l'interface correspondante pour quelques remarques de plus a propos des methodes.
 */
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
        int port = (args.length < 2) ? 1091 : Integer.parseInt(args[1]);
        try {
        	RemoteClient self = new RemoteClient();
        	Registry registry = LocateRegistry.getRegistry(host, port);
            RemoteServerInterface stub = (RemoteServerInterface) registry.lookup("RemoteServerInterface");
            self.setClient(stub);
            stub.repererClient(self);
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

	@Override
	public void recevoirPartageTableau(String n, Ustandard u) throws RemoteException {
		client.recevoirPartageTableau(n, u);
	}
}
