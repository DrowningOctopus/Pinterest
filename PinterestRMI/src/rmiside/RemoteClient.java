package rmiside;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;
import rmiside.RemoteServerInterface;

public class RemoteClient {
	private static Ustandard client;
	
	private RemoteClient() {
		System.out.println("constructeur Client ok");
	}

	private static void setClient(RemoteServerInterface ri) throws RemoteException {
		client = new Ustandard(ri);
	}
	
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            RemoteServerInterface stub = (RemoteServerInterface) registry.lookup("RemoteInterface");
            setClient(stub);
            stub.repererClient(client);
            while (true) {
            	client.agir();
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
