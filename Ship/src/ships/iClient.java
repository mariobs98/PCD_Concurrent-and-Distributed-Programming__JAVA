package ships;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iClient extends Remote{

	public void respuesta(String nombre) throws RemoteException;
	
	public void aviso(int contador) throws RemoteException;
	
	public void avisoFlour(int Flour) throws RemoteException;
	public void avisoSugar(int Sugar) throws RemoteException;
	public void avisoSalt(int Salt) throws RemoteException;
}
