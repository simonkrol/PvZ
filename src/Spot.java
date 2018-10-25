
public class Spot {
	private Plant plant;
	
	public Spot() {
		plant = null;
	}
	
	
	public boolean getOccupied() {
		return plant != null;
	}
	
	public Plant getPlant() {
		return plant;
	}
	
	public boolean addPlant(Plant toAdd) {
		if(!this.getOccupied()) 
		{
			this.plant = toAdd;
			return true;
		}
		return false;
		
	}
	
	public boolean killPlant() {
		if(this.getOccupied())
		{
			this.plant = null;
			return true;
		}
		return false;
	}
}