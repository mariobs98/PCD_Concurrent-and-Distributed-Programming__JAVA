package ships;
import java.rmi.RemoteException;
import java.util.ArrayList; 


public class MerchantShip extends Ship {

	private ArrayList<Container> Containers = new ArrayList<Container>();
	private Platform platform;
	
	public MerchantShip(Gate gate, Control control, Platform platform, int id, int direction, iClient client,Counter counter) {
		super(gate, control, id, direction, client,counter);
		this.platform = platform;
		fillWithFlour();
		fillWithSugar();
		fillWithSalt();
	}
	
	private void fillWithFlour() {
		for(int i=0;i<20;i++) {
			Container c = new Container (1);
			Containers.add(c);
		}
	}
	
	private void fillWithSugar() {
		for(int i=0;i<12;i++) {
			Container c = new Container (2);
			Containers.add(c);
		}
	}
	
	private void fillWithSalt() {
		for(int i=0;i<5;i++) {
			Container c = new Container (3);
			Containers.add(c);
		}
	}
	
	public boolean getIfEmpty() {
		boolean AmIEmpty = false;
		if(Containers.size()==0)
			AmIEmpty = true;
		return AmIEmpty; 
		}
	
	public Container getRandomContainer() {
		int select = 0;
		//select = select randomly a container from the list
		select = (int) (Math.random()*Containers.size());
		Container container = Containers.get(select);
		Containers.remove(select);
		return container;
	}
	

	@Override
	public void run() {
		//Enters to the cargo area
		control.entryPermission(this);
		gate.enter(id);
		control.entryNotification(this);
		
		try {
			counter.plusOne();
			counter.notificarBarcos(client);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		while(!getIfEmpty()){
			platform.loadPlatform(this);
		}
		if(getIfEmpty())
		System.out.printf("------------------------Merchant Ship Empty-----------------------------\n");
		
		control.exitPermission(this);
		gate.exit(id);
		control.exitNotification(this);
		
		try {
			counter.minusOne();
			counter.notificarBarcos(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}