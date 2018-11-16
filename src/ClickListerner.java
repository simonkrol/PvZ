import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListerner implements MouseListener{

	private View view;
	public ClickListerner(View view) {
		this.view = view;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point m = MouseInfo.getPointerInfo().getLocation();
		view.x = m.x;
		view.y = m.y;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
