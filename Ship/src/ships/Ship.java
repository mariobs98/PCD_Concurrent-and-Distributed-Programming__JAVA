package ships;

import java.rmi.RemoteException;

public class Ship implements Runnable{
	
	protected int id;
	protected int direction;	//0=enter, 1=exit
	protected Gate gate = null;
	protected Control control = null;
	protected Counter counter = null;
	protected iClient client = null;
	
	public Ship(Gate gate, Control control, int id, int direction, iClient client,Counter counter) {
		this.gate = gate;
		this.control = control;
		this.id = id;
		this.direction = direction;		
		this.counter = counter;
		this.client = client;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getDirection() {
		return this.direction;		
	}
	
	public void enter() {
		gate.enter(id);
	}
	
	public void exit() {
		gate.exit(id);
	}
	
	public void run(){
		if(this.direction==0) {
			control.entryPermission(this);
			gate.enter(id);
			control.entryNotification(this);
			try {
				counter.plusOne();
				counter.notificarBarcos(client);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		else {
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
	
}
