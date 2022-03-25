package ships;

public class Crane implements Runnable{
	
	private Platform platform;
	private int id;	//1, 2 and 3 for cranes of platform, same at the type of product it can unload
	
	public Crane(Platform platform, int id) {
		this.platform = platform;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void run() {
		
		if(id == 1) {			
			for(int containers = 0; containers<20; containers++) {
				platform.unloadPlatform(this);	
			}
		}else {
				if(id == 2) {
					for(int containers = 0; containers<12; containers++) {
						platform.unloadPlatform(this);	
					}
				}else {
					for(int containers = 0; containers<5; containers++) {
						platform.unloadPlatform(this);	
					}
				}
			}
	}
	
}
