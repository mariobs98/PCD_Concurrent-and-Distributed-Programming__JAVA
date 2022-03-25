package ships;

public class TaskOil implements Runnable{

	private OilShip os;
	
	public TaskOil(OilShip os) {
		this.os = os;
	}
	
	@Override
	public void run() {

		while(os.LCOil.getAmount() < 3000) {
			os.oca.getOil(os);
		}
		
	}

}
