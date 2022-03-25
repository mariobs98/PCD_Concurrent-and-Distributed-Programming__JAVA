package ships;
import java.util.concurrent.*;

public class OilCargoArea {
	Semaphore mutex;
	
	LiquidContainer LCOil11= new LiquidContainer(1,1000);	//One liquid container for each ship
	LiquidContainer LCOil12= new LiquidContainer(1,1000);
	LiquidContainer LCOil13= new LiquidContainer(1,1000);
	LiquidContainer LCOil14= new LiquidContainer(1,1000);
	LiquidContainer LCOil15= new LiquidContainer(1,1000);
	
	CyclicBarrier cb = new CyclicBarrier(6);	//with 6 await of cb, they will be all released, and the counter again
												//start at zero
	
	LiquidContainer LCWater= new LiquidContainer(2,99999);	//One liquid container for the 5 ships
	Semaphore lcw;
	
	Phaser phaser = new Phaser(5);	//when ships have done 5 arriveandwaitadvance, they will be released
	int waiting = 0;

	Semaphore filler;
	int cont = 0;
	
	public OilCargoArea() {		
		mutex = new Semaphore(1);

		filler = new Semaphore(0);
	}
	
	public void waitforships(OilShip os) {
		try{
			mutex.acquire();
		
		while(waiting < 5) {
			switch(waiting) {
		case 0:
			waiting++;
			os.control.entryPermission(os);
			os.gate.enter(os.getId());
			os.control.entryNotification(os);
			mutex.release();
			System.out.println("*Oil Ship " + os.getId() + " waiting other ships");
			phaser.arriveAndAwaitAdvance();
			break;
		case 1:
			waiting++;
			os.control.entryPermission(os);
			os.gate.enter(os.getId());
			os.control.entryNotification(os);
			mutex.release();
			System.out.println("*Oil Ship " + os.getId() + " waiting other ships");
			phaser.arriveAndAwaitAdvance();
			break;
		case 2:
			waiting++;
			os.control.entryPermission(os);
			os.gate.enter(os.getId());
			os.control.entryNotification(os);
			mutex.release();
			System.out.println("*Oil Ship " + os.getId() + " waiting other ships");
			phaser.arriveAndAwaitAdvance();
			break;
		case 3:
			waiting++;
			os.control.entryPermission(os);
			os.gate.enter(os.getId());
			os.control.entryNotification(os);
			mutex.release();
			System.out.println("*Oil Ship " + os.getId() + " waiting other ships");
			phaser.arriveAndAwaitAdvance();
		break;
		case 4:
			waiting++;
			os.control.entryPermission(os);
			os.gate.enter(os.getId());
			os.control.entryNotification(os);
			mutex.release();
			phaser.arriveAndAwaitAdvance();
			System.out.println("*Oil Ships have arrived*");
			break;
			}
		}

		}catch(Exception e) {}
	}
	
	public void getOil(OilShip os) {
		
			switch(os.getId()) {
		case 11:
				LCOil11.decrementAmount(1000);
				os.LCOil.incrementAmount(1000);
				System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of oil");
				if(LCOil11.getAmount() == 0) {
					try {
						if(os.LCOil.getAmount() == 3000) {
							cont++;
						}
						filler.release();
						try {
							cb.await();
						} catch (BrokenBarrierException e) {e.printStackTrace();}
					}catch (InterruptedException e) {}
				}
			break;
		case 12:
			LCOil12.decrementAmount(1000);
			os.LCOil.incrementAmount(1000);
			System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of oil");
			if(LCOil12.getAmount() == 0) {
				try {
					if(os.LCOil.getAmount() == 3000) {
						cont++;
					}
					filler.release();
					try {
						cb.await();
					} catch (BrokenBarrierException e) {e.printStackTrace();}
				} catch (InterruptedException e) {}
			}
			break;
		case 13:
			LCOil13.decrementAmount(1000);
			os.LCOil.incrementAmount(1000);
			System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of oil");
			if(LCOil13.getAmount() == 0) {
				try {
					if(os.LCOil.getAmount() == 3000) {
						cont++;
					}
					filler.release();
					try {
						cb.await();
					} catch (BrokenBarrierException e) {e.printStackTrace();}
				}catch (InterruptedException e) {}
			}
			break;
		case 14:
			LCOil14.decrementAmount(1000);
			os.LCOil.incrementAmount(1000);
			System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of oil");
			if(LCOil14.getAmount() == 0) {
				try {
					if(os.LCOil.getAmount() == 3000) {
						cont++;
					}
					filler.release();
					try {
						cb.await();
					} catch (BrokenBarrierException e) {e.printStackTrace();}
				} catch (InterruptedException e) {}
			}
		break;
		case 15:
			LCOil15.decrementAmount(1000);
			os.LCOil.incrementAmount(1000);
			System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of oil");
			if(LCOil15.getAmount() == 0) {
				try {
					if(os.LCOil.getAmount() == 3000) {
						cont++;
					}
					filler.release();
					try {
						cb.await();
					} catch (BrokenBarrierException e) {e.printStackTrace();}
				}catch (InterruptedException e) {}
			}
			break;
			}

	}
	
	public void getWater(OilShip os) {
		try {
			mutex.acquire();
			
			LCWater.decrementAmount(1000);
			os.LCWater.incrementAmount(1000);
			System.out.println("**OilShip " + os.getId() + " filled with 1000 amount of water");
			
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();}
	}
	
	public void fillOil(Filler f) {

		try {
			for(int i = 0; i<5; i++){
				filler.acquire();}
		} catch (InterruptedException e) {}	
		
		if(cont == 5) {
			f.finish = true;
				try {
					cb.await();
				} catch (InterruptedException e) {e.printStackTrace();
				} catch (BrokenBarrierException e) {e.printStackTrace();}
	}else {
			LCOil11.incrementAmount(1000);
			LCOil12.incrementAmount(1000);
			LCOil13.incrementAmount(1000);
			LCOil14.incrementAmount(1000);
			LCOil15.incrementAmount(1000);
			System.out.println("***Filler refilled 1000 amount of oil for each container");
			try {
				cb.await();
			} catch (InterruptedException e) {e.printStackTrace();
			} catch (BrokenBarrierException e) {e.printStackTrace();}
		}

	}
	
}
