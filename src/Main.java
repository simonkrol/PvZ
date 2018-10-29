import java.io.IOException;
import java.util.Scanner; 
public class Main {
	boolean inProgress;
	public Level lvl;
	private Scanner scan;
	private Gui gui;
	
	public Main() throws IOException{
		scan = new Scanner(System.in);
		
		while(inProgress == false){
			System.out.println("Enter (n)ew game or (q)uit:");
			String s = scan.next();
			if(s.equals("n")){
				this.startGame();
			}
		}
	}
	public void startGame() throws IOException
	{
		this.inProgress = true;
		lvl = new Level(8, 6, 125, "Level.txt");
		gui = new Gui(lvl);
		gui.update();
		
		System.out.println("Game Started. Prepare defenses. Balance: " + lvl.balance);
		
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
				
					System.out.println("Enter Type of Plant, (s)unflower[" + Sunflower.DEFAULT_VALUE + "] or (p)eashooter[" + Peashooter.DEFAULT_VALUE +"]: ");
					String type = scan.next();
					switch (type){
						case "s":
						lvl.placePlant(new Sunflower(lvl.getLane(laneNum)), laneNum, spot);
						gui.update();
						System.out.println("Current Balance:" + lvl.balance);
						break;
						case "p":
						lvl.placePlant(new Peashooter(lvl.getLane(laneNum)), laneNum, spot);
						gui.update();
						break;
						default:
						System.out.println("Invalid Plant Type.");
					}
				
					break;
				case "e":
					lvl.allTurn();
					lvl.spawnZombies();
					if(checkWin())
					{
						gui.update();
						System.out.println("You Killed all the zombies! \n Congratulations you won!");
						return;
					}
					if(checkFail())
					{
						gui.update();
						System.out.println("Zombies have gotten past! \nGame over! ");
						return;
					}
					else
					{
						gui.update();
					}
					break;
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
			if(lvl.grid[i].endState==2)
			{
				return true;
			}
		}
		return false;
	}
	private boolean checkWin()
	{
		for(int i = 0; i < lvl.grid.length; i++)
		{
			if(lvl.grid[i].liveZombies.size() != 0 || lvl.curInstruction != null)return false;
		}
		return true;
	}
	
	
	
	
	

	public static void main(String[] args) throws IOException {
	
		new Main();
		

	}

}
