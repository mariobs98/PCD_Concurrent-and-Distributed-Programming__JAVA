package ships;
import java.util.ArrayList;

public class Control {

private static Control control = null;	


private int entering = 0; //Ships entering at the same time.

private int exiting = 0; //Ships exiting at the same time.

private int exit = 0; //Ships that want to exit, number waiting.

private ArrayList<Ship> entryQueue = new ArrayList<Ship>();	 

private ArrayList<Ship> exitQueue = new ArrayList<Ship>();

synchronized public static Control getInstance() {
	if(control == null) {
		control=new Control();
	}
	return control;
}

public synchronized void entryPermission(Ship s) {
	System.out.printf("Ship " + s.getId() + " asking for permission to enter.\n" );
	entryQueue.add(s);
	while((exiting > 0 || exit > 0) || (s.getId() != entryQueue.get(0).getId())) 
		try{
			System.out.printf("Ship " + s.getId() + " wait.\n" );
			wait();
		}catch(InterruptedException e){;}
	entryQueue.remove(0);
	entering++;
}

public synchronized void exitPermission(Ship s) {
	System.out.printf("			Ship " + s.getId() + " asking for permission to exit.\n" );
	exitQueue.add(s);
	while((entering > 0) || (s.getId() != exitQueue.get(0).getId()))
		try {
			System.out.printf("			Ship " + s.getId() + " wait.\n" );
			exit++;
			wait();
			exit--;
		}catch(InterruptedException e) {;}
	exitQueue.remove(0);
	exiting++;
}

public synchronized void entryNotification(Ship s) {
	entering--;
	System.out.printf("Ship " + s.getId() + " has entered.\n" );
	if(entering == 0)
	notifyAll();
}

public synchronized void exitNotification(Ship s) {
	exiting--;
	System.out.printf("			Ship " + s.getId() + " has exited.\n" );
	if(exiting == 0)
	notifyAll();
}

}
