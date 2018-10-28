import java.util.Scanner; 
public class Model {
	boolean inProgress;
	public Level lvl;
	private Scanner scan;
	
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
	public void startGame(){
		this.inProgress = true;
		lvl = new Level(5, 5, 90);
		System.out.println("Game Started. Prepare defenses. Balance: 90");
		
		boolean suffFunds = true;
		boolean editing = true;
		
	while(editing){	
		System.out.println("Commands: (p)lace plant, (e)nd turn, (n)ew game or (q)uit");
		String c = scan.next();
		switch (c){
		case "p":
			if(lvl.balance <= 40){suffFunds = false;}
			if (suffFunds){
			
				System.out.println("Enter lane number: ");
				int lane = scan.nextInt();
				System.out.println("Enter spot position: ");
				int spot = scan.nextInt();
			
				System.out.println("Enter Type of Plant, (s)unflower or (p)eashooter: ");
				String type = scan.next();
				Plant plantToAdd;
				switch (type){
				case "s":
				plantToAdd = new Sunflower(new Lane(lane));
				lvl.placePlant(plantToAdd, lane, spot);
				break;
				case "p":
				plantToAdd = new Peashooter(new Lane(lane));
				lvl.placePlant(plantToAdd, lane, spot);
				break;
				default:
				System.out.println("Invalid Plant Type.");
				}
			}else{
				System.out.println("Sorry insufficient funds to place plant.");
				editing = false;
			}
			
		break;
		case "e":
			for (Lane l : lvl.grid){
				for(int i = 0; i<5;i++){
					System.out.println("Lane info: "+ l.spots[i].getPlant());
			}
			}
			
		
		
		
		
		}
	}
	}
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Model();

	}

}
