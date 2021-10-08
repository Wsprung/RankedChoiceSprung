import java.util.Arrays;
import java.util.ArrayList;

public class RankedChoice {
  private ArrayList<ArrayList<String>> ballots;
  private ArrayList<String> names;
  //list of all possible candidates to be voted for
  private ArrayList<String> originalCandidates = new ArrayList<>();

  public RankedChoice(ArrayList<ArrayList<String>> ballots, ArrayList<String> names) {
    this.ballots = ballots;
    this.names = names;
    for(int i = 0; i < names.size(); i++) {
      originalCandidates.add(names.get(i));
      //creates a second list of names because you will be altering one of them
    }
  }

  public ArrayList<String> candidateList() {
    //Since you will be altering the candidate list the user passes, this method is important so the user still has access to the list of candidates
    return originalCandidates;
  }

  public int getNumCandidates() {
    return originalCandidates.size();
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
  private String mostVotes(ArrayList<Integer> calced) {
    String winner = "";
    int ret = -1;
    for(int i = 0; i < calced.size(); i++) {
      //error (*was fixed*): it will return no winner if there is any tie, but this should only happen when there is a tie for the largest amount
    if(calced.get(i) > ret) {
        ret = calced.get(i);
        winner = names.get(i);
      }
    }
    for(int i = 0; i < calced.size(); i++) {
      if(calced.get(i) == ret && i!=(names.indexOf(winner))) {
        return "There is a tie and therefore no winner.";
      }
    }

    return winner;
  }

  //helper method
  private String leastVotes(ArrayList<Integer> calced) {
    int ret = calced.get(0);
    String loser = names.get(0);
    int occurences = 0;
    for(int i = 0; i < calced.size(); i++) {
      if(calced.get(i) <= ret) {
        ret = calced.get(i);
        loser = names.get(i);
        occurences++;
      }
    }
    //if everything in the rank has equal votes, you do not want all your candidates to be removed
    //so, returning a random String will ensure that nothing in names is removed
    if(occurences == calced.size()) { return "lalalallalalala"; }
    return loser; //returns the value of the least votes in calced, which may show up multiple times if there is a tie. Will return -1 if all candidates are tied.
  }

/**Calculates and returns the winner based off which candidate had the most TOTAL WEIGHTED votes from each rank.
  Using the "Borda Count" (an example of positional voting)**/
  public String calcWinnerBorda() {

    ArrayList<Integer> calced = new ArrayList<>();
    for(int i = 0; i < names.size(); i++) {
      calced.add(0);
    }
    int sum = 0;
    int numRanks = ballots.get(0).size();
    String winner = "";

    for(int i = 0; i < names.size(); i++) {
      for(int j = 0; j < ballots.get(0).size(); j++) {
        int sumCalcedNumVotes = calced.get(i) + (getNumVotes(names.get(i), j + 1)*numRanks);
        calced.set(i, sumCalcedNumVotes);
        numRanks--;
      }
      numRanks = ballots.get(0).size();
    }
    if(!mostVotes(calced).equals("There is a tie and therefore no winner.")) {
      winner = mostVotes(calced);
      int ret = calced.get(names.indexOf(winner));
      return "The winner using Borda Count is: " + winner + " with " + ret + " votes";
    }
    return "There is a tie and therefore no winner.";
  }

  public String calcWinnerInstantRunOff() {
    boolean winner = false;
    ArrayList<Integer> calced = new ArrayList<>();
    for(int i = 0; i < names.size(); i++) {
      calced.add(0);
    }

    for(int j = 1; j <= ballots.get(0).size(); j++) {
      for(int i = 0; i < names.size(); i++) {
      //calculating the number of votes for every candidate and placing it in calced for rank j
        calced.set(i, getNumVotes(names.get(i), j));
      //calced = num votes for each candidate (indexes aligning with names indexes) at rank j
    //if one candidate has the majority, that is the winner, if not:
      //find index in calced with least votes and account for cases where there is a tie (remove all with least)
      //look at the next rank for a majority
  }

   double total = 0;
   for(int i = 0; i < calced.size(); i++) {
     total = total + calced.get(i);
   }


   String mostVotesCalced = mostVotes(calced);

   if(!mostVotesCalced.equals("There is a tie and therefore no winner.")) {
     if(calced.get(names.indexOf(mostVotesCalced))/total >= .5) { return "The winner using instant run off is " + mostVotes(calced); }
   }

   else {
     //code should work whether or not there is a tie in the rank, should remove all candidates who had the lowest amount of votes (multiple if there was a tie)
      int indexOfLeast = names.indexOf(leastVotes(calced));
      int occurences = 0;

      if(indexOfLeast != -1) {
      int least = calced.get(indexOfLeast);
        //making sure there is a value which is the least, if not it just goes to the next rank as all are tied
        //if everything in the current rank is equal, indexOfLeast will = -1
      for(int i = 0; i < calced.size(); i++) {
        if(calced.get(i) == least||calced.get(i) == 0) { names.set(i, ""); }
        //removes all the candidate(s) with the (tied) smallest vote tally from names, if all have this tally names will be left with nothing in it
      }

      for(int i = 0; i < names.size(); i++) {
        if(names.get(i).equals("")) { names.remove(i); i--; }
      }

    }
      if(names.size() == 0) { break; }
      //if all the candidates are tied so you removed all the candidates in names, break out of the current iteration
      calced = new ArrayList<>();
      for(int i = 0; i < names.size(); i++) {
        calced.add(0);
      }
    }
  }
  return "There is no winner because there is a tie.";
}
}
