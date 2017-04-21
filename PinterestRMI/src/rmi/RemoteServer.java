package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import example.hello.Hello;
import example.hello.Server;

public class RemoteServer implements RemoteInterface {
	public RemoteServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
        try {
            RemoteServer self = new RemoteServer();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(self, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
