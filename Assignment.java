/*
   Source Filename:	 Assignment.java
   Name:	LAM Sau Cheung
   Class:	IT114105/1A
   Student Number:220014918
   Description:	This program is game called "Connect4". it is a two-player connection board game, 
   in which the players choose a digital and then take turns dropping colored tokens into a seven-column, 
   six-row vertically suspended grid. The pieces fall straight down, occupying the lowest available space within the column. 
   The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own tokens.
*/

import java.util.Scanner;

public class Assignment{
 public static void main( String[] args ) {
 Scanner input = new Scanner(System.in);

 char[][] mTable= new char [6][7];
 //player
 	int turn = 0;
	char player = '1';
	boolean winner = false;	

//initialize array
for (int row = 0; row < mTable.length; row++){
	for (int col = 0; col < mTable[0].length; col++){
		mTable[row][col] = '0';
	}
}

//play a turn
		while (winner == false && turn <= 42){
			int play;
			if(turn == 0){
				drawGrid(mTable);
			}
			do {
				System.out.print("Player " + player + " type a column <0-6> or 9 to quit current game:");
				play = input.nextInt();

				if (getError(play, mTable)==false){
					continue;
				}
				
			}while (validPlay(play,mTable) == false);

			if (play == 9){
				System.out.println("Bye Bye");
				break;
			}

                //drop the checker and show the table
				if(play>=0 && play <=6){
				for (int row = 0; row <= 5; row++){
					if(mTable[row][play] == '0'){
						mTable[row][play] = player;
						turn++;
						drawGrid(mTable);
						break;
					}
				}

			//check tie
			if (turn == 42){
				System.out.println("Tie game");
				break;
			}
			}
			
            //determine if there is a winner
			winner = isWinner(player,mTable);
			
    //switch players
			if (player == '1' && mTable[5][play] == '0'){
				player = '2';
			}else{
				player = '1';
			}
            
  }
//check winner
  if (winner){
	if (player=='1'){
		System.out.println("Player 2 win this game!");
	}else{
		System.out.println("Player 1 win this game!");
	}
}
 }
 //table
 public static void drawGrid(char[][] mTable){
	   for(int i =5; i >= 0; i--){
			   System.out.println();
				   System.out.print(i+" :"+" \t");
	   for(int j =0; j <= 6; j++){
	   System.out.print(mTable[i][j]+" \t");
		}
	   }
	   System.out.println();
	   for(int i =0; i < 15; i++){
			   System.out.print("----");
		   }
			   System.out.println();
			   System.out.print("\t");
		   for(int i =0; i <= 6; i++){
			   System.out.print(i + " \t");
		   }
			   System.out.println();
		   }
public static boolean getError(int play, char[][] mTable){
		//Exit
		if (play == 9){
		return true;
		}
		//valid column?
		if (play < 0 || play > 6){
			System.out.println("Range of column should be 0 to 6!");
			return false;
		}

		//full column?
		if (mTable[5][play] != '0'){
			System.out.println("Column "+ play + " is full!");
			return false;
		}
		return true;
	}
//only check valid
	public static boolean validPlay(int play, char[][] mTable){
		//Exit
		if (play == 9){
			return true;
		}
		//valid column?
		if (play < 0 || play > 6){
			return false;
		}
		
		//full column?
		if (mTable[5][play] != '0'){
			return false;
		}
		return true;
	}

    public static boolean isWinner(char player, char[][] mTable){
		//check for 4 across
		for(int row = 0; row<mTable.length; row++){
			for (int col = 0;col < mTable[0].length - 3;col++){
				if (mTable[row][col] == player   && 
					mTable[row][col+1] == player &&
					mTable[row][col+2] == player &&
					mTable[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < mTable.length - 3; row++){
			for(int col = 0; col < mTable[0].length; col++){
				if (mTable[row][col] == player   && 
					mTable[row+1][col] == player &&
					mTable[row+2][col] == player &&
					mTable[row+3][col] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 3; row < mTable.length; row++){
			for(int col = 0; col < mTable[0].length - 3; col++){
				if (mTable[row][col] == player   && 
					mTable[row-1][col+1] == player &&
					mTable[row-2][col+2] == player &&
					mTable[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 0; row < mTable.length - 3; row++){
			for(int col = 0; col < mTable[0].length - 3; col++){
				if (mTable[row][col] == player   && 
					mTable[row+1][col+1] == player &&
					mTable[row+2][col+2] == player &&
					mTable[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
}