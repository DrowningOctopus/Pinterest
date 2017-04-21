package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pinterest.Epingle;
import pinterest.Tableau;
import utilisateurs.Ustandard;

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

	@Override
	public void validerCreationTableau(Tableau t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerModificationTableau(Tableau t, String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerPartageTableau(Tableau t, Ustandard u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerCreationEpingle(Epingle e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerAjoutEpingle(Epingle e, Tableau t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerSuppressionEpingle(Epingle e, Tableau t) {
		// TODO Auto-generated method stub
		
	}
}
