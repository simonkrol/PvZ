import java.util.ArrayList;

public class Spot {
	private ArrayList<Entity> entities;
	
	public Spot() {
		this.entities = new ArrayList<Entity>();
	}
	
	
	public boolean getOcc() {
		return entities.size() > 0;
	}
	
	protected ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
}