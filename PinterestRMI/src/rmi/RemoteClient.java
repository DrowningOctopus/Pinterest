package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utilisateurs.Ustandard;

public class RemoteClient {
	private static Ustandard client;
	
	private RemoteClient() {}

	private static void setClient(RemoteInterface ri) {
		
	}
	
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            
            System.out.println(stub.check());
            
            setClient(stub);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
