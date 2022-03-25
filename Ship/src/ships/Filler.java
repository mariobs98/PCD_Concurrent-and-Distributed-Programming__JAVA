package ships;

public class Filler implements Runnable{

	boolean finish = false;
	OilCargoArea oca;
	
	public Filler(OilCargoArea oca) {
		this.oca = oca;
	}

	@Override
	public void run() {
		
		while(!finish) {
		oca.fillOil(this);
		}
		System.out.println("****Filler end his job****");	
	}
	
}
