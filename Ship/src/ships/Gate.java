package ships;

public class Gate {
	
	//Gate Singleton
	private static Gate gate = null;
	
	//getInstance for singleton
	synchronized public static Gate getInstance() {
		if(gate == null) {
			gate=new Gate();
		}
		return gate;
	}
	
	//Method to print a message when the ship enter through the gate
	public void enter(int id) {
		for(int i = 0;i<3;i++){
			System.out.printf("Ship "+ id +" enter.\n");
		}
	}
	
	//Method to print a message when the ship exit through the gate
	public void exit(int id) {	
		for(int i = 0;i<3;i++){
			System.out.printf("			Ship "+ id +" exits.\n");
		}
	}
}
