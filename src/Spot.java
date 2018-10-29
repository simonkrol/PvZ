
public class Spot {
	private Plant plant;
	private boolean placeable;
	
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
		if(!placeable)
		{
			System.out.println("You may not place a plant in this column.");
			return false;
		}
		if(!this.getOccupied()) 
		{
			this.plant = toAdd;
			return true;
		}
		System.out.println("This Spot already has a plant in it.");
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