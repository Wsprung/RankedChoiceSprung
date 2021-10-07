import java.util.Arrays;
import java.util.ArrayList;

public class RankedChoice {
  private ArrayList<ArrayList<String>> ballots;
  private ArrayList<String> names;
  //list of all possible candidates to be voted for
  private ArrayList<String> names2 = new ArrayList<>();

  public RankedChoice(ArrayList<ArrayList<String>> ballots, ArrayList<String> names) {
    this.ballots = ballots;
    this.names = names;
    for(int i = 0; i < names.size(); i++) {
      names2.add(names.get(i));
      //creates a second list of names because you will be altering one of them
    }
  }

  public ArrayList<String> candidateList() {
    //Since you will be altering the candidate list the user passes, this method is important so the user still has access to the list of candidates
    return names2;
  }

  public int getNumCandidates() {
    return names2.size();
  }

  //helper method
  private int getNumVotes(String candidate, int rank) {
    int sum = 0;
    for(int i = 0; i < ballots.size(); i++) {
      if(ballots.get(i).get(rank-1).equals(candidate)) { sum++; }
    }
    //arrays does not have an indexOf method, only ArrayList does
    return sum;
  }

  //helper method
  private String mostVotes(int[] calced) {
    String winner = "";
    int ret = -1;
    for(int i = 0; i < calced.length; i++) {
      if(calced[i] == ret) { return "There is a tie and therefore no winner."; }
      else if(calced[i] > ret) {
        ret = calced[i];
        winner = names.get(i);
      }
    }

    return winner;
  }

  //helper method
  private String leastVotes(int[] calced) {
    String loser = "";
    int ret = calced[0];
    for(int i = 0; i < calced.length; i++) {
      if(calced[i] < ret) {
        ret = calced[i];
        loser = names.get(i);
      }
    }

    return loser; //returns the value of the least votes in calced, which may show up multiple times if there is a tie. Will return -1 if all candidates are tied.
  }

/**Calculates and returns the winner based off which candidate had the most TOTAL WEIGHTED votes from each rank.
  Using the "Borda Count" (an example of positional voting)**/
  public String calcWinnerBorda() {
    int[] calced = new int[names.size()];
    int sum = 0;
    int k = ballots.get(0).size();
    String winner = "";

    for(int i = 0; i < names.size(); i++) {
      for(int j = 0; j < ballots.get(0).size(); j++) {
        calced[i] = calced[i] + (getNumVotes(names.get(i), j + 1)*k);
        k--;
      }
      k = ballots.get(0).size();
    }
    if(!mostVotes(calced).equals("There is a tie and therefore no winner.")) {
      winner = mostVotes(calced);
      int ret = calced[names.indexOf(winner)];
      return "The winner using Borda Count is: " + winner + " with " + ret + " votes";
    }
    return "There is a tie and therefore no winner.";
  }

  public String calcWinnerInstantRunOff() {
    boolean winner = false;
    int[] calced = new int[names.size()];

    for(int j = 1; j <= ballots.get(0).size(); j++) {
      for(int i = 0; i < names.size(); i++) {//make while loop? use break once you get a winner?
      //calculating the number of votes for every candidate and placing it in names for rank i
        calced[i] = calced[i] + getNumVotes(names.get(i), j);
      //calced = num votes for each candidate (indexes aligning with names indexes) at rank i
    //if mostVotes returns one winner, that is the winner, if not:
      //find index in calced with least votes and account for cases where there is a tie
      //remove candidate(s) from names who have the least amount of votes
  }

    if(mostVotes(calced).equals("There is a tie and therefore no winner.")) {
      int indexOfLeast = names.indexOf(leastVotes(calced));
      int occurences = 0;

      if(indexOfLeast != -1) {
      int least = calced[indexOfLeast];
        //making sure there is a value which is the least, if not it just goes to the next rank as all are tied
        //if everything in the current rank is equal, indexOfLeast will = -1

      for(int i = 0; i < calced.length; i++) {
        if(calced[i] == least) { names.remove(i); }
        //removes all the candidate with the (tied) smallest vote tally from names, if all have this tally names will be left with nothing in it
      }
    }
      if(names.size() == 0) {break;}
      calced = new int[names.size()];
    }

    else {
      return "The winner using instant run off is " + mostVotes(calced);
    }
    }

    return "There is no winner, because there is a tie.";
  }
}
