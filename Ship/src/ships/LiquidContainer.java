package ships;

public class LiquidContainer extends Container{

	//Product	//1 for Oil
				//2 for Water
	
	private int Amount;
	
	public LiquidContainer(int typeProduct, int amount){
		super(typeProduct);
		this.Amount = amount;
	}
	
	public int getAmount() {
		return Amount;
	}
	
	public void decrementAmount(int decrement) {
		Amount = Amount - decrement;
	}
	
	public void incrementAmount(int increment) {
		Amount = Amount + increment;
	}

}
