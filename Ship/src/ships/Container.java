package ships;

public class Container {

	private int Product;	//1 for flour
							//2 for sugar
							//3 for salt
	
	public Container(int typeProduct){
		this.Product = typeProduct;
	}

	public int getTypeProduct() {
		return Product;
	}

}
