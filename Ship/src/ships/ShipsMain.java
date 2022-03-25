package ships;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ShipsMain {

	public static void main(String args[]) {

		String identificador;

		try {
			identificador = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			identificador = "Counter";
            System.out.println("\nNo se ha especificado nombre objeto servidor");
            System.out.println("   usando nombre por defecto " + identificador);
		}

		try {
			Counter obj = new Counter();
			iCounter stub = (iCounter) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(identificador, stub);
			System.out.println("Server ready esperando callback");	

		}

		catch (RemoteException e) {
			System.err.println("Host no alcanzable/error de comunicación");
		} catch (AlreadyBoundException e) {
			System.err.println("Ya hay inscrito un objeto con ese nombre");
		}
	}

}
