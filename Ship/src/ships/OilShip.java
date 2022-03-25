package ships;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class OilShip extends Ship{

	OilCargoArea oca;
	LiquidContainer LCOil;
	LiquidContainer LCWater;
	
	private ThreadPoolExecutor executor;
	
	public OilShip(Gate gate, Control control, OilCargoArea oca, int id, int direction, iClient client,Counter counter) {
		super(gate, control, id, direction, client,counter);
		this.oca = oca;
		LCOil = new LiquidContainer(1,0);
		LCWater = new LiquidContainer(2,0);
		executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
	}
	
	@Override
	public void run() {
		
		try{
			oca.waitforships(this);
			
			counter.plusOne();
			counter.notificarBarcos(client);

			//executor can execute the two task at the same time, so, ships will get oil or water when they can, insted of waiting to get 
			//first oil and then water.
			TaskOil TO = new TaskOil(this);
			executor.execute(TO);
			TaskWater TW = new TaskWater(this);
			executor.execute(TW);

			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);

		control.exitPermission(this);
		gate.exit(id);
		control.exitNotification(this);
		counter.minusOne();
		counter.notificarBarcos(client);
		
		}catch(Exception e){}
	}
}
