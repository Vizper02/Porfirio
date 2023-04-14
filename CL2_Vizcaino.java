/* Fernando Noel Vizcaino Perales
   [CS1101] Comprehensive Lab 2
   This work is to be done individually. It is not permitted to. 
   share, reproduce, or alter any part of this assignment for any 
   purpose. Students are not permitted from sharing code, uploading 
   this assignment online in any form, or viewing/receiving/
   modifying code written from anyone else. This assignment is part. 
   of an academic course at The University of Texas at El Paso and 
   a grade will be assigned for the work produced individually by 
   the student.
*/
   
import java.util.Scanner; 
import java.util.Arrays; 

public class CL2_Vizcaino{
	//create three global variables using static to make it accessible among methods
	static int pairNumber=1, rows=0, colums=0;
	//
	
	//create global scanner object using static to make it accessible among methods
	static Scanner scn = new Scanner(System.in);
	//
	
	//Main method
	public static void main(String[] args){	
		
		//create board
		int[][] board = setupBoard();
		//

		//Create the playing board with the same size as the board array
		String[][] playingBoard = new String[board.length][board[0].length];
		//
		
		//Initialize the playing board cells to '?'
		for(int i=0;i<playingBoard.length;i++){
			for(int j=0;j<playingBoard[0].length;j++){
				playingBoard[i][j]="?";
			}
		}
		//
		
		//Create variable to play for a certain number of attempts according to the global var pairNumber
		int maxAttempts = pairNumber+1;
		//
		
		//Display menu and ask user to select an option
		displayMainMenu(maxAttempts);
		//confirm its a number input and not a string
		scn.nextLine();
		while(!scn.hasNextInt()){
			System.out.print("Error input must be a number: ");
			scn.nextLine();
		}
		//
		int menu = scn.nextInt();
		//
		
		//ask for input again while no playing option selected
		boolean notOptionSelected = true;
		do{
			//start playing a game mode
			switch(menu){
				//Case user selects to play with attempts
				case 1:
					int attempt=0;
					System.out.println("Number of attempts left: "+Integer.toString(maxAttempts-attempt));
					while(runGame(playingBoard,board)==0){
						attempt++;
						if(attempt==maxAttempts){
							break;
						} 
						System.out.println("Number of attempts left: "+Integer.toString(maxAttempts-attempt));
					}
					System.out.println("GameOver");
					notOptionSelected=false;
					break;
				//
				
				//Case user selects to play with unlimited attempts
				case 2: 
					while(runGame(playingBoard,board)==0);
					notOptionSelected=false;
					System.out.println("GameOver");
					break;
				//
				
				//Case user wants to get out of game
				case 3: 
					System.out.println("GameOver");
					notOptionSelected=false;
					break;
				//
				
				//Case user selected incorrect option which turns out to repeat loop again
				default:
					System.out.println("No valid option selected");
					System.out.print("Please enter value again: ");
					scn.nextLine();
					while(!scn.hasNextInt()){
						System.out.print("Error input must be a number: ");
						scn.nextLine();
					}
					menu = scn.nextInt();
				//
			}
			//
		}while(notOptionSelected);
		//
	}
	//
	
	
	//setupBoard method called in Main
	public static int[][] setupBoard(){
		int boardSize=0;
		
		//ask for size of board
		do{
			System.out.print("Enter the size of the board (even number and higher or equal to 4): ");
			//confirm is a number input and not a string
			while(!scn.hasNextInt()){
				System.out.print("Error input must be a number: ");
				scn.nextLine();
			}
			//
			boardSize = scn.nextInt();
		}while(boardSize%2==1 || boardSize<4);
		//

		//Get how many rows and columns
		getRowColum(boardSize);
		//

		//Create new 2d array
		int[][] board = new int[rows][colums];
		//

		//initialize 2d array elements to cero
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				board[i][j]=0;
			}
		}
		//

		//Setup pairs on board
		placePairs(board);
		//

		return board;
	}
	//
	
	
	//My own getRowColum Method for setupBoard
	public static void getRowColum(int x){
		//convert value to float
		float num = x;
		//
		
		float square = (float)Math.sqrt(num);
		//go into condition in case it's not a perfect square
		if(square%Math.floor(square)!=0){
			int countFactors = 0;
			//start dividing the number until it gets to 1 and get factors
			while(num>1){
				if(num%2==0){
					countFactors++;
					num /=2;
				}
				else if(num%3==0){
					countFactors++;
					num /=3;
				}
				else if(num%4==0){
					countFactors++;
					num /=4;
				}
				else if(num%5==0){
					countFactors++;
					num /=5;
				}
				else{
					countFactors++;
					num /= num;
				}
			}
			//
			
			
			//get half and get the next number in case it's odd
			int half = (int)Math.ceil(countFactors/2.0);
			//
			
			//just some temporary variables
			int a=1, b=1, subcount=0;
			//
			
			//restart number
			num = x;
			//
			
			//Now repeat process and get Rows and Columns
			while(subcount<countFactors){
				//get and multiply half factors
				while(subcount<half){
					if(num%2==0){
						subcount++;
						num /=2;
						a *=2;
					}
					else if(num%3==0){
						subcount++;
						num /=3;
						a *=3;
					}
					else if(num%4==0){
						subcount++;
						num /=4;
						a *=4;
					}
					else if(num%5==0){
						subcount++;
						num /=5;
						a *=5;
					}
					else{
						subcount++;
						num /=num;
						a *=num;
					}
				}
				//
				
				
				//Now get the other half
				if(num%2==0){
					subcount++;
					num /=2;
					b *=2;
				}
				else if(num%3==0){
					subcount++;
					num /=3;
					b *=3;
				}
				else if(num%4==0){
					subcount++;
					num /=4;
					b *=4;
				}
				else if(num%5==0){
					subcount++;
					num /=5;
					b *=5;
				}
				else{
					subcount++;
					num /=num;
					b *=num;
				}
				//
			}
			
			//assign higher half to columns and the other to rows
			if(a>b){
				colums = a;
				rows = b;
			}
			else{
				colums = b;
				rows = a;
			}
			//
			
			//
		}
		//
		//In case it is a perfect square
		else{
			colums = (int)square;
			rows = (int)square;
		}
	}
	//

	//placePairs Method for setupBoard
	public static void placePairs(int[][] arr){
		//create variables
		int initializedCells=0,counter=1,randNum=0;
		//
		
		//Do loop until all cells have been initialized with a pair
		do{
			//Get a random number in rows
			int randRowNum = (int)Math.round((arr.length-1)*Math.random());
			//
			//Get a random number in columns
			int randColumNum = (int)Math.round((arr[randRowNum].length-1)*Math.random());
			//
			
			//Assign pair to empty cell
			if(arr[randRowNum][randColumNum]==0){
				//assign global variable pairNumber
				arr[randRowNum][randColumNum]=pairNumber;
				//
				
				//keep track of pairs
				counter++;
				if(counter>2){
					counter = 1;
					pairNumber++;
				}
				//
				
				//keep track of initialized cells
				initializedCells++;
				//

			}
			//
		}while(initializedCells<(arr.length*arr[0].length));
		//
	}
	//
	
	//displayMainMenu Method called in Main
	public static void displayMainMenu(int attempts){
		System.out.print("Do you wish to play for:\n1. "+attempts+" attempts\n2. Unlimited number of attempts\n3. Exit game\n");
	}
	//
	
	//runGame Method
	public static int runGame(String[][] playingBoard, int[][] board){
		displayBoard(playingBoard);
		
		int cellRowNum1=0, cellColNum1=0;
		
		do{
			System.out.print("Enter cell column number: ");
			scn.nextLine();
			while(!scn.hasNextInt()){
				System.out.print("Error input must be a number: ");
				scn.nextLine();
			}
			cellColNum1 = scn.nextInt();
			System.out.print("Enter cell row number: ");
			scn.nextLine();
			while(!scn.hasNextInt()){
				System.out.print("Error input must be a number: ");
				scn.nextLine();
			}
			cellRowNum1 = scn.nextInt();
		}while(!isWithinBounds(board.length,board[0].length,cellRowNum1,cellColNum1) || isCellSelected(playingBoard,cellRowNum1,cellColNum1));
		
		System.out.println("You have discovered: "+board[cellRowNum1][cellColNum1]);
		clearCell(playingBoard, cellRowNum1,cellColNum1,board[cellRowNum1][cellColNum1]);
		System.out.println("Where is the matching pair?");
		displayBoard(playingBoard);
		
		int cellRowNum2=0, cellColNum2=0;
		
		do{
			System.out.print("Enter cell column number: ");
			scn.nextLine();
			while(!scn.hasNextInt()){
				System.out.print("Error input must be a number: ");
				scn.nextLine();
			}
			cellColNum2 = scn.nextInt();
			System.out.print("Enter cell row number: ");
			scn.nextLine();
			while(!scn.hasNextInt()){
				System.out.print("Error input must be a number: ");
				scn.nextLine();
			}
			cellRowNum2 = scn.nextInt();
		}while(!isWithinBounds(board.length,board[0].length,cellRowNum2,cellColNum2) || isCellSelected(playingBoard,cellRowNum2,cellColNum2));
		clearCell(playingBoard, cellRowNum2,cellColNum2,board[cellRowNum2][cellColNum2]);
		
		if(playingBoard[cellRowNum1][cellColNum1].equals(playingBoard[cellRowNum2][cellColNum2])){
			System.out.println("You have found a pair!");
			if(isBoardCleared(playingBoard)) return 3;
			else return 0;
		}
		else{
			System.out.println("You uncovered: "+playingBoard[cellRowNum2][cellColNum2]);
			playingBoard[cellRowNum1][cellColNum1] = "?";
			playingBoard[cellRowNum2][cellColNum2] = "?";
			return 0;
		}
	}
	//
	
	//displayBoard Method called in runGame
	public static void displayBoard(String[][] board){
		//display index of columns
		System.out.print("  |");
		for(int i=0;i<board[0].length;i++){
			System.out.print(i+"|");
		}
		//
		System.out.println("\n------------------------------------");
		//display rows
		for(int i=0;i<board.length;i++){
			System.out.print(i+"| ");
			for(int j=0;j<board[i].length;j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		//
	}
	//
	
	//isWithinBounds Method called in runGame
	public static boolean isWithinBounds(int boardRowLength, int boardColumnLength, int cellRow, int cellColumn){
		if(cellRow>=0&&cellRow<boardRowLength){
			if(cellColumn>=0&&cellColumn<boardColumnLength) return true;
			else return false;
		}
		else return false;
	}
	//
	
	//isCellSelected Method called in runGame
	public static boolean isCellSelected(String[][] board, int cellRow, int cellColum){
		if(board[cellRow][cellColum].equals("?")) return false;
		else return true;
	}
	//
	
	//clearCell Method called in runGame
	public static void clearCell(String[][] board, int cellRow, int cellColum, int value){
		board[cellRow][cellColum] = Integer.toString(value);
	}
	//
	
	//isBoardCleared Method called in runGame
	public static boolean isBoardCleared(String[][] board){
		//Check if a cell hasn't been cleared out
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if(board[i][j].equals("?")) return false;
			}
		}
		//
		
		//after then it means all cells have been cleared out 
		return true;
		//
	}
	//
	
} 