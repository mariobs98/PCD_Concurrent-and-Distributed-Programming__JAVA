package ships;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface iCounter extends Remote{

	void notificarBarcos(iClient cliente) throws RemoteException;
	void notificarFlour(iClient cliente) throws RemoteException;
	void notificarSugar(iClient cliente) throws RemoteException;
	void notificarSalt(iClient cliente) throws RemoteException;
	
	void plusOne() throws RemoteException;
	void minusOne() throws RemoteException;
	
	void plusOneFlour() throws RemoteException;
	void plusOneSugar() throws RemoteException;
	void plusOneSalt() throws RemoteException;
	
	void runMain(iClient client, Counter counter) throws RemoteException;
	
	void registrar(iClient callback) throws RemoteException;
	
}
