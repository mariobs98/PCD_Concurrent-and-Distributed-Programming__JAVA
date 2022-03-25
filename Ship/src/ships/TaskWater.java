package ships;

public class TaskWater implements Runnable{

	private OilShip os;
	
	public TaskWater(OilShip os) {
		this.os = os;
	}
	
	@Override
	public void run() {

		while(os.LCWater.getAmount() < 5000){
			os.oca.getWater(os);
		}
		
	}

}
