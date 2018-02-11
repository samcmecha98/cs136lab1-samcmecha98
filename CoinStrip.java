import java.util.Random;
import java.util.Scanner;

public class CoinStrip {

  private boolean [] coinArray = new boolean[15]; //the array of coins
  private String coin = new String ("| $ "); //string representation of coin
  private String space = new String ("|   "); //string representation of empty space
  private int gameBoardLength = 15;

  public CoinStrip () {
    Random random = new Random(); //generates numbe of coins on board
    for (int i = 0; i < gameBoardLength; i++) {
      int nextInt = random.nextInt(13);
      if (nextInt < 4) {
        coinArray[i] = true; // represents coin
      } else {
        coinArray[i] = false; // represents blank space
      }
    }
  }

  //tests to see whether move is legal
  public boolean legalMove (int first, int last) {
    if (last < first) { // if the desired position is less than current position
      for (int i = last; i < first; i++) { //loop checks for any coin in between
        //desired and current positon
        if (coinArray[i]) {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

  public void move (int first, int last) { //moves coin to desired position
    if (legalMove (first, last)) {
      coinArray[first] = false;
      coinArray[last] = true;

    }
  }

  public String toString () { //represents boolean array as string
    String coinStrip = "";
    for (int i = 0; i < 15; i++) {
      if (coinArray[i]) {
        coinStrip += coin;
      } else {
        coinStrip += space;
      }
    }
    return coinStrip;
  }

  public boolean[] getCoinArray () { //returns array of boolean values
    return coinArray;
  }

  public int numCoins () { //returns number of coins in array
    int coins = 0;
    for (int i = 0; i < 15; i++) {
      if (coinArray[i]) {
        coins++;
      }
    }
    return coins;
  }

  public boolean gameOver() { //checks to see if game is over
    int coins = this.numCoins ();
    for (int i = 0; i < coins; i++)  {
      if (!coinArray[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main (String [] args) {
    String gameSide = new String ("+---");
    String gameLength = new String ("");
    CoinStrip game = new CoinStrip();
    String gameCoins = new String (game.toString());
    for (int i = 0; i < 15; i++) {
      gameLength = gameLength + gameSide;
    }

    //prints game board with coins placed within board
    System.out.println(gameLength + "\n" + game.toString() + "\n" + gameLength + "\n");

    Scanner in = new Scanner (System.in);


    while (!game.gameOver()) { //while the game is not over
      System.out.println ("Which coin would you like to move?" + "\n");
      int firstPosition = in.nextInt();

      if (game.getCoinArray()[firstPosition]) { //if there is a coin in position
        System.out.println ("Where would you like to move this coin?" + "\n");
        int secondPosition = in.nextInt();

        if (game.legalMove(firstPosition, secondPosition)) { //if it is a legal move
          game.move(firstPosition, secondPosition); //moves coin to desired position
          System.out.println (gameLength + "\n" +game.toString() + "\n" + gameLength + "\n");

        } else {
          System.out.println ("Invalid move!" + "\n");
          //notifies user of invalid move
        }
      } else {
        System.out.println ("There is no coin in this position!" + "\n");
        //notifies user of absence of coin in position
      }


    }

  }
}
