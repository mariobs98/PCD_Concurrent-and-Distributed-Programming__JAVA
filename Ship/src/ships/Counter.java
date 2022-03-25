package ships;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Counter implements iCounter{

	int counter = 0;
	
	int flour = 0;
	int sugar = 0;
	int salt = 0;
	
	@Override
	public void notificarBarcos(iClient cliente) throws RemoteException {
		cliente.aviso(counter);
	}
	
	@Override
	public void notificarFlour(iClient cliente) throws RemoteException {
		cliente.avisoFlour(flour);
	}

	@Override
	public void notificarSugar(iClient cliente) throws RemoteException {
		cliente.avisoSugar(sugar);
	}

	@Override
	public void notificarSalt(iClient cliente) throws RemoteException {
		cliente.avisoSalt(salt);
	}

	@Override
	public void plusOne() throws RemoteException {
		counter++;
	}

	@Override
	public void minusOne() throws RemoteException {
		counter--;
	}
	
	@Override
	public void plusOneFlour() throws RemoteException {
		flour++;
	}

	@Override
	public void plusOneSugar() throws RemoteException {
		sugar++;
	}

	@Override
	public void plusOneSalt() throws RemoteException {
		salt++;
	}
	
	public void registrar(iClient client)	{
		System.out.println("\n Cliente registrado.....");
		String localIP="";
		String localName="";
		try {
			localIP = InetAddress.getLocalHost().getHostAddress();
			localName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		
		
		try {

			runMain(client,this);
			
			System.out.println("Ejecucion realizada ");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void runMain(iClient client, Counter counter) throws RemoteException{
		
		ArrayList<Thread> Threads = new ArrayList<Thread>(); // List of created threads
		Gate gate = Gate.getInstance(); // gate that ships pass
		Control control = Control.getInstance(); // Control Structure for the gate
		Platform platform = Platform.getInstance(this,client); // platform for merchant ship
		OilCargoArea oca = new OilCargoArea();

		// creating 3 cranes
		for (int i = 1; i <= 3; i++) {
			Crane crane = new Crane(platform, i);
			Thread cr = new Thread(crane);
			Threads.add(cr);
		}

		// Creating Ships

		// merchant Ship
		int j = 10; // j = id
		MerchantShip ms = new MerchantShip(gate, control, platform, j, 0, client, counter); // 0 = enter

		// Oil ships
		for (int x = 11; x <= 15; x++) {
			OilShip os = new OilShip(gate, control, oca, x, 0, client, counter);
			Thread Tos = new Thread(os);
			Threads.add(Tos);
		}
		Filler filler = new Filler(oca);
		Thread tfiller = new Thread(filler);
		Threads.add(tfiller);

		// Normal Ships
		for (int i = 0; i < 10; i++) {
			Ship s = new Ship(gate, control, i, i % 2, client, counter);
			if((i % 2) == 1) {
				counter.plusOne();
			}
			Thread t = new Thread(s);
			Threads.add(t);
		}
		// Threads start

		// merchant Ship
		Thread mership = new Thread(ms);
		Threads.add(mership);

		// Threads
		for (int i = 0; i < Threads.size(); i++) {
			Threads.get(i).start();
		}
		
	}

}
