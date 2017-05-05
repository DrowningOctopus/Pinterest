package rmiside;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;
import rmiside.RemoteInterface;

public class RemoteClient {
	private static Ustandard client;
	
	private RemoteClient() {
		System.out.println("constructeur Client ok");
	}

	private static void setClient(RemoteInterface ri) throws RemoteException {
		System.out.println("on set le client...");
		client = new Ustandard(ri, "Test");
		System.out.println("... le client est set.");
	}
	
    public static void main(String[] args) {
    	System.out.println("debut du main");
        String host = (args.length < 1) ? null : args[0];
        try {
        	System.out.println("try");
            Registry registry = LocateRegistry.getRegistry(host);
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            
            System.out.println(stub.check());
            
            setClient(stub);
            
            System.out.println("stub repere client ?");
            
            System.out.println("***"+client);
            
            stub.repererClient(client);
            
            System.out.println("agissons !");
            client.agir();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
