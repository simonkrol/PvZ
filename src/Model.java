import java.util.Scanner; 
public class Model {
	boolean inProgress;
	public Level lvl;
	private Scanner scan;
	private Gui gui;
	
	public Model(){
		scan = new Scanner(System.in);
		
		while(inProgress == false){
			System.out.println("Enter (n)ew game or (q)uit:");
			String s = scan.next();
			if(s.equals("n")){
				this.startGame();
			}
		}
	}
	public void startGame()
	{
		this.inProgress = true;
		lvl = new Level(5, 5, 90);
		gui = new Gui(lvl);
		
		//ADD ZOMBIE IN QUEUE
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, null), 1);
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, null), 1);
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, null), 2);
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, null), 4);
		
		
		System.out.println("Game Started. Prepare defenses. Balance: 90");
		
		boolean editing = true;
		
		while(editing){	
			System.out.println("Commands: (p)lace plant, (e)nd turn, (n)ew game or (q)uit");
			String c = scan.next();
			switch (c)
			{
				case "p":
					System.out.println("Enter lane number: ");
					int laneNum = scan.nextInt() - 1;
					System.out.println("Enter spot position: ");
					int spot = scan.nextInt() - 1;
				
					System.out.println("Enter Type of Plant, (s)unflower or (p)eashooter: ");
					String type = scan.next();
					switch (type){
						case "s":
						lvl.placePlant(new Sunflower(lvl.getLane(laneNum)), laneNum, spot);
						break;
						case "p":
						lvl.placePlant(new Peashooter(new Lane(laneNum)), laneNum, spot);
						break;
						default:
						System.out.println("Invalid Plant Type.");
					}
				
					break;
				case "e":
					for(Lane lane: lvl.grid) 
					{
						lane.spawnZombieWave();
					}
					Entity.allTurn(lvl);
					gui.update();
			}
		
		
		
		
		}
	}
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Model();
		

	}

}
