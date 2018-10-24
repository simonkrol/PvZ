public class spot {
	private boolean occupied = false;
	private Object entity = null;
	
	public spot() {

	}
	
	public spot(Object entity) {
		this.entity = entity;
		occupied = true;
	}
	
	public boolean getOcc() {
		return occupied;
	}
	
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
		if(entity != null)
		{
			occupied = true;
		}
		else
		{
			occupied = false;
		}
	}
}
