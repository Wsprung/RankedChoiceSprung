import java.util.Arrays;
import java.util.ArrayList;

public class RunRankedChoice {
  public static void main(String[] args) {
    //testing RankedChoice with ice cream flavor ranked choice vote
    String[][] numVotesIceCream = {{"chocolate","mint chip", "peanut butter cup", "chocolate"}, {"peanut butter cup", "mint chip", "chocolate", "cookie dough"}, {"peanut butter cup", "cookie dough", "chocolate","mint chip"},{"chocolate", "mint chip", "peanut butter cup","cookie dough"}};
    ArrayList<String> flavors = new ArrayList<>(Arrays.asList("mint chip", "cookie dough", "peanut butter cup", "chocolate"));
    ArrayList<ArrayList<String>> ballots = new ArrayList<ArrayList<String>>();

    //adding the individual ballots to my 2D ArrayList ballots
    for(String[] array: numVotesIceCream) {
      ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(array));
      //first create a temp ArrayList with the values in one of your array ballots you want to add
      ballots.add(tmp);
      //then add this to ballots -> filling up your 2D ArrayList
      //I split up the steps as otherwise it would not let me add ArrayLists to my 2D ArrayList ballots
    }

    System.out.println(ballots);

    RankedChoice iceCreamVote = new RankedChoice(ballots, flavors);
    System.out.println(iceCreamVote.calcWinnerInstantRunOff());
    flavors = iceCreamVote.candidateList();
    iceCreamVote = new RankedChoice(ballots, flavors);
    System.out.println(iceCreamVote.calcWinnerBorda());
    System.out.println(iceCreamVote.getNumCandidates());
    System.out.println(iceCreamVote.candidateList());
  }
}
