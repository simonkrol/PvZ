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
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, lvl.getLane(1)));
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, lvl.getLane(1)));
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, lvl.getLane(2)));
		lvl.addToQ(new Zombie(10, 2, 1, 250, 1, lvl.getLane(4)));
		
		
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
					lvl.allTurn();
					if(checkFail())
					{
						System.out.println("_________uu$$$$$$$$$$$$$$$$$uu__________\n" + 
								"_________u$$$$$$$$$$$$$$$$$$$$$u_________\n" + 
								"________u$$$$$$$$$$$$$$$$$$$$$$$u________\n" + 
								"_______u$$$$$$$$$$$$$$$$$$$$$$$$$u_______\n" + 
								"_______u$$$$$$$$$$$$$$$$$$$$$$$$$u_______\n" + 
								"_______u$$$$$$”___”$$$”___”$$$$$$u________\n" + 
								"_______”$$$$”______u$u_______$$$$”________\n" + 
								"________$$$———u$u_______u$$$________\n" + 
								"________$$$u______u$$$u______u$$$________\n" + 
								"_________”$$$$uu$$$___$$$uu$$$$”_________\n" + 
								"__________”$$$$$$$”___”$$$$$$$”__________\n" + 
								"____________u$$$$$$$u$$$$$$$u____________\n" + 
								"_____________u$”$”$”$”$”$”$u______________\n" + 
								"__uuu________$$u$_$_$_$_$u$$_______uuu__\n" + 
								"_u$$$$________$$$$$u$u$u$$$_______u$$$$_\n" + 
								"__$$$$$uu______”$$$$$$$$$”_____uu$$$$$$__\n" + 
								"u$$$$$$$$$$$uu____”\"”\"”____uuuu$$$$$$$$$$\n" + 
								"$$$$”\"”$$$$$$$$$$uuu___uu$$$$$$$$$”\"”$$$”\n" + 
								"_”\"”______”\"$$$$$$$$$$$uu_”\"$”\"”___________\n" + 
								"___________uuuu_”\"$$$$$$$$$$uuu___________\n" + 
								"__u$$$uuu$$$$$$$$$uu_”\"$$$$$$$$$$$uuu$$$__\n" + 
								"__$$$$$$$$$$”\"”\"___________”\"$$$$$$$$$$$”__\n" + 
								"___”$$$$$”______________________”\"$$$$”\"__");
						System.out.println("Zombies have gotten past! \nGame over! ");
						
						return;
					}
					else
					{
						gui.update();
					}
				case "n":
				System.out.println("Not implimented in this milestone");
				break;
				
				case "q":
				return;
			}
		
		
		
		
		}
	}
	
	private boolean checkFail()
	{
		for(int i = 0; i < lvl.grid.length; i++)
		{
			if(lvl.grid[i].end)
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Model();
		

	}

}
