# RankedChoiceSprung

Author: Whitney Sprung
Date last altered: 10.8.2021

Hello!

Welcome to RankedChoice, a Java class that allows you to calculate the winner of an election using Borda count and Instant Run Off count, two types of ranked voting.

Methods:

public ArrayList<String> candidateList()
 *Returns the list of candidates in your election
 *Since calcWinnerInstantRunOff will adjust your passed list of candidates, it is important to use this method if you want to get your list of candidates

public int getNumCandidates()
 *Returns the number of candidates in your election

public String calcWinnerBorda()
 *Calculates the winner of your election using Borda Count
   *The winner is the candidate with the most TOTAL WEIGHTED votes from each rank
   *A vote in rank one is considered 3 points if you have 3 ranks, rank two would be 2 points, etc...

public String calcWinnerInstantRunOff()
 *Calculates the winner of your election using Instant Run Off Count
   *Looks at rank 1, if there is a candidate with the majority (>=50%, no ties) they are the winner
   *If not, if there is a candidate with less votes than the rest, they are removed from all ballots
	*If two+ candidates are tied for the least amount of votes, they are all removed from all the ballots
	*If no candidate has the least amount of votes (all tied), the program moves on to the next rank
 *Keeps going until a candidate has the majority votes in its rank, and if not, a message indicating a tie is returned

 *****NOTE: This method will alter your passed list of candidates. Use getNumCandidates() to recover your original list of candidates.*****

private int getNumVotes(String candidate, int rank)
 *Helper method

private String mostVotes(ArrayList<Integer> calced)
 *Helper method

private String leastVotes(ArrayList<Integer> calced)
 *Helper method
