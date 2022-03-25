package ships;

import java.net.InetAddress; 
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements iClient{

	public static void main(String[] args) {	

		String myIp="localhost";
		
		if (args.length <2) {
	    	System.out.println("Error en argumentos: formato de uso");
	    	System.out.println("java Client <host_servidor> <nombre_objeto_servidor>");
	    } else {   	
		    	try {
		    		/**
		    		 * Nos preparamos para recibir invocaciones remotas
		    		 */
		    		Client obj = new Client();
		    		System.setProperty("java.rmi.server.hostname", myIp ) ;		    		
		    		iClient yo = (iClient) UnicastRemoteObject.exportObject(obj, 0);
		    		
		    		/**
		    		 * Localizamos el servicio
		    		 */
		    		Registry registry = LocateRegistry.getRegistry(args[0]);
					iCounter servidor =(iCounter) registry.lookup(args[1]);						
					
					/**
		    		 * Invocamos a los métodos del servidor. 
		    		 * En este caso, el servidor tiene un método con el que registra mi
		    		 * referencia y puede realizar un CallBack
		    		 */
					servidor.registrar(yo);
					System.out.println("Registrado en servidor//ejecucion");
					
					
				} catch (Exception e) {
					System.err.println("Client exception: " + e.toString());
	                e.printStackTrace();
				}
	    }

	}
	
	public void respuesta(String nombre)
	{
		System.out.println("Invocación a este metodo desde el servidor " + nombre);
	}
	
	public void aviso(int contador) throws RemoteException{
		System.out.println("There are " + contador + " ships inside");
	}

	@Override
	public void avisoFlour(int Flour) throws RemoteException {
		System.out.println("There are " + Flour + " containers of flour inside");
	}

	@Override
	public void avisoSugar(int Sugar) throws RemoteException {
		System.out.println("There are " + Sugar + " containers of sugar inside");
	}

	@Override
	public void avisoSalt(int Salt) throws RemoteException {
		System.out.println("There are " + Salt + " containers of salt inside");
	}
	
	
}
