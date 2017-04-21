package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClient {
	private RemoteClient() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            System.out.println(stub.check());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
