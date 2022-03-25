package ships;  
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Platform {
	
	boolean full;
	Container container = null;
	
	Lock mutex = new ReentrantLock();
	Condition cCrane1 = mutex.newCondition();
	Condition cCrane2 = mutex.newCondition();
	Condition cCrane3 = mutex.newCondition();
	
	Condition cMerchantShip = mutex.newCondition();
	
	Counter counter;
	iClient client;
	//Platform Singleton
	private static Platform platform = null;
	
	//getInstance for singleton
	synchronized public static Platform getInstance(Counter counter,iClient client) {
		if(platform == null) {
			platform=new Platform(counter,client);
		}
		return platform;
	}
	
	public Platform(Counter counter, iClient client) {
		full = false;
		this.counter=counter;
		this.client=client;
	}
	
	public void loadPlatform(MerchantShip mership) {
		mutex.lock();
		try {
			while(full) {
				cMerchantShip.await();
			}
				container = mership.getRandomContainer();
				System.out.printf("++Container of type " + container.getTypeProduct() + " loaded at the platform\n");
				full = true;
				if(container.getTypeProduct() == 1) {
				cCrane1.signal();
				counter.plusOneSugar();
				counter.notificarSugar(client);
				}else {
					if(container.getTypeProduct() == 2) {
						cCrane2.signal();
						counter.plusOneFlour();
						counter.notificarFlour(client);
					}
					else{
					cCrane3.signal();
					counter.plusOneSalt();
					counter.notificarSalt(client);
					}
				}
			
		}catch(Exception e){
		}finally {
			mutex.unlock();
		}
	}
	
	public void unloadPlatform(Crane crane){
		mutex.lock();
		try {
			while((full && container.getTypeProduct() != crane.getId()) || (!full)) {
				if(crane.getId() == 1) {
					cCrane1.await();
				}else {
					if(crane.getId() == 2) {
						cCrane2.await();
					}else{
							cCrane3.await();
						}
				}
			}
				full = false;
				System.out.printf("--Crane of type " + crane.getId() + " got a container from the platform\n");	
				cMerchantShip.signal();
			
		}catch(Exception e){
		}finally {
			mutex.unlock();
		}
	}
	
}
