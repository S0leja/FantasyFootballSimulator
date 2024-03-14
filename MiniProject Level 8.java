/* ***************************************
  @author    S0leja
  @date      30 October 2023
  @version   1
  @level     1

    Fantasy Football League Game
   ****************************************/

import java.io.*;
import java.util.Scanner;
import java.util.Random;
class GamesWon{
    String team1;
    String team2;
    int matchesPlayed = 0;
    int team1Wins = 0;
    int team2Wins = 0;
    int draws = 0;
}

class FootballGame {
    //main method
    public static void main (String[]a)throws IOException{
        intro();
        seriesOfGames();
    }
    //Prints the introduction
    public static void intro(){
        System.out.println("Welcome to the Fantasy Football League Game");
        System.out.println("Please enter the team you would like to choose: ");
        

    }
    //Simulates a series of games
    public static void seriesOfGames() throws IOException{
        Scanner sc = new Scanner(System.in);        
        final int maxGames = 5;
        String condition = "y";
        String teamName1 = askTeamName();
        GamesWon teamSpain = new GamesWon();
        teamSpain = setTeam1(teamSpain, teamName1);
        teamSpain = setTeam2(teamSpain, "Spain");
        GamesWon teamMongolia = new GamesWon();
        teamMongolia = setTeam1(teamMongolia, teamName1);
        teamMongolia = setTeam2(teamMongolia, "Mongolia");
        GamesWon teamFrance = new GamesWon();
        teamFrance = setTeam1(teamFrance, teamName1);
        teamFrance = setTeam2(teamFrance, "France");
        GamesWon teamItaly = new GamesWon();
        teamItaly = setTeam1(teamItaly, teamName1);
        teamItaly = setTeam2(teamItaly, "Italy");
        GamesWon[] array = ReadData(teamSpain, teamItaly, teamFrance, teamMongolia);
        teamSpain = array[0];
        teamItaly = array[1];
        teamFrance = array[2];
        teamMongolia = array[3];
        while(condition.equalsIgnoreCase("y")){
            String[] team2 = {"Mongolia","Spain","Italy","France"};
            String teamName2 = randomTeam(team2);
            int scoreTeam1;
            int scoreTeam2;
            int team1Wins = 0;
            int team2Wins = 0;
            System.out.println(teamName1 + " will play "+teamName2);
            System.out.println("-------------------------");
        
            for (int game = 0; game <maxGames; game++){
                System.out.println("Game "+(game+1));
                scoreTeam1 = randomScore();
                scoreTeam2 = randomScore();
                System.out.println(teamName1+": "+scoreTeam1+" "+teamName2+": "+ scoreTeam2);
                String winner = decideWinner(teamName1,teamName2,scoreTeam1,scoreTeam2);
                if (teamName2.equalsIgnoreCase("Spain")){
                    teamSpain = setMatchesPlayed(teamSpain);
                    if (winner.equalsIgnoreCase(teamName1)){
                        team1Wins = team1Wins + 2;
                        teamSpain  = setTeam1Wins(teamSpain);
                    }
                    else if (winner.equalsIgnoreCase(teamName2)){
                        team2Wins = team2Wins + 2;
                        teamSpain = setTeam2Wins(teamSpain);
                    }
                    else{
                        team1Wins++;
                        team2Wins++;
                        teamSpain = setDraws(teamSpain);
                    }
                }
                else if (teamName2.equalsIgnoreCase("Italy")){
                    teamItaly = setMatchesPlayed(teamItaly);
                    if (winner.equalsIgnoreCase(teamName1)){
                        team1Wins = team1Wins + 2;
                        teamItaly  = setTeam1Wins(teamItaly);
                    }
                    else if (winner.equalsIgnoreCase(teamName2)){
                        team2Wins = team2Wins + 2;
                        teamItaly = setTeam2Wins(teamItaly);
                    }
                    else{
                        team1Wins++;
                        team2Wins++;
                        teamItaly = setDraws(teamItaly);
                    }
                }
                else if (teamName2.equalsIgnoreCase("France")){
                    teamFrance = setMatchesPlayed(teamFrance);
                    if (winner.equalsIgnoreCase(teamName1)){
                        team1Wins = team1Wins + 2;
                        teamFrance  = setTeam1Wins(teamFrance);
                    }
                    else if (winner.equalsIgnoreCase(teamName2)){
                        team2Wins = team2Wins + 2;
                        teamFrance = setTeam2Wins(teamFrance);
                    }
                    else{
                        team1Wins++;
                        team2Wins++;
                        teamFrance = setDraws(teamFrance);
                    }
                }
                else if (teamName2.equalsIgnoreCase("Mongolia")){
                    teamMongolia = setMatchesPlayed(teamMongolia);
                    if (winner.equalsIgnoreCase(teamName1)){
                        team1Wins = team1Wins + 2;
                        teamMongolia  = setTeam1Wins(teamMongolia);
                    }
                    else if (winner.equalsIgnoreCase(teamName2)){
                        team2Wins = team2Wins + 2;
                        teamMongolia = setTeam2Wins(teamMongolia);
                    }
                    else{
                        team1Wins++;
                        team2Wins++;
                        teamMongolia = setDraws(teamMongolia);
                    }
                }
            }
            System.out.println(teamName1+ " has "+team1Wins+" points");
            System.out.println(teamName2+" has "+team2Wins+" points");
            System.out.println("Simulate with a random team again? ");
            condition = sc.nextLine();
        }
        
        System.out.println("Overall results from the simulation: ");
        System.out.println("----------------");
        outputSummary(teamFrance);
        System.out.println("----------------");
        outputSummary(teamItaly);  
        System.out.println("----------------");
        outputSummary(teamSpain);
        System.out.println("----------------");
        outputSummary(teamMongolia);
        WriteData(teamSpain,teamItaly,teamFrance,teamMongolia);

        
    }
    //asks the user to input their desired team name
    public static String askTeamName(){
        Scanner sc = new Scanner(System.in);
        String teamName = sc.nextLine();
        System.out.println("You have chosen "+ teamName);
        return teamName;
    }
    //outputs the summary of games
    public static void outputSummary(GamesWon t){
        String team1 = returnTeam1(t);
        int team1Wins = returnTeam1Wins(t);
        String team2 = returnTeam2(t);
        int team2Wins = returnTeam2Wins(t);
        int draws = returnDraws(t);
        int matchesPlayed = returnMatchesPlayed(t);

        System.out.println(team1+" has won " + team1Wins+ " games out of "+ matchesPlayed+" matches played");
        System.out.println(team2+" has won " + team2Wins+ " games out of "+ matchesPlayed+" matches played");
        System.out.println(draws + " matches were drawn");
    }
    //generates a random score between teams
    public static int randomScore(){
        Random dice = new Random();
        int score = dice.nextInt(5);
        return score;
    }
    //returns a random team from an array of teams
    public static String randomTeam(String[] teams){
        Random dice = new Random();
        int index = dice.nextInt(4);
        return teams[index];
    }
    //Decides the winner between two teams given the score
    public static String decideWinner(String teamName1,String teamName2, int scoreTeam1,int scoreTeam2){
        if (scoreTeam1>scoreTeam2){
            System.out.println(teamName1+" is the winner");
            System.out.println("-------------------------");
            return teamName1;
        }
        else if(scoreTeam2>scoreTeam1){
            System.out.println(teamName2+" is the winner");
            System.out.println("-------------------------");
            return teamName2;
        }
        else{
            System.out.println("Draw");
            System.out.println("-------------------------");
            return "None";
        }
    }
    public static GamesWon createRecord (String team1Name, String team2Name){
        GamesWon t = new GamesWon();
        t = setTeam1(t, team1Name);
        t = setTeam2(t, team2Name);
        return t;
    }
    public static GamesWon changeWins(GamesWon t, int teamNumber){
        if (teamNumber ==1){
            setTeam1Wins(t);
        }
        else if (teamNumber ==2){
            setTeam2Wins(t);
        }
        return t;
    }
    //returns team 1 name
    public static String returnTeam1(GamesWon t){
        return t.team1;
    }
    //returns the team 1 wins
    public static int returnTeam1Wins(GamesWon t){
        return t.team1Wins;
    }
    //returns the team 2 name
    public static String returnTeam2(GamesWon t){
        return t.team2;
    }
    //returns the team 2 wins
    public static int returnTeam2Wins(GamesWon t){
        return t.team2Wins;
    }
    //returns the number of matches played between team1 and team2
    public static int returnMatchesPlayed(GamesWon t){
        return t.matchesPlayed;
    }
    //returns the number of draws between team1 and team2
    public static int returnDraws(GamesWon t){
        return t.draws;
    }
    //sets the team1 name
    public static GamesWon setTeam1(GamesWon t, String team1Name){
        t.team1 = team1Name;
        return t;
    }
    //sets the team2 name
    public static GamesWon setTeam2(GamesWon t, String team2Name){
        t.team2 = team2Name;
        return t;
    }
    //sets team1 wins
    public static GamesWon setTeam1Wins(GamesWon t){
        t.team1Wins++;
        return t;
    }
    //sets team2 wins
    public static GamesWon setTeam2Wins(GamesWon t){
        t.team2Wins++;
        return t;
    }
    //sets the number of matches played
    public static GamesWon setMatchesPlayed(GamesWon t){
        t.matchesPlayed++;
        return t;
    }
    //sets the number of draws
    public static GamesWon setDraws(GamesWon t){
        t.draws++;
        return t;
    }
    //writes all the data to a file
    public static void WriteData(GamesWon spain, GamesWon italy, GamesWon france, GamesWon mongolia) throws IOException{
        PrintWriter outputStream = new PrintWriter(new FileWriter("FantasyFootball.txt"));
        outputStream.println(spain.team1);
        outputStream.println(spain.team2);
        outputStream.println(spain.matchesPlayed);
        outputStream.println(spain.team1Wins);
        outputStream.println(spain.team2Wins);
        outputStream.println(spain.draws);
        outputStream.println(italy.team1);
        outputStream.println(italy.team2);
        outputStream.println(italy.matchesPlayed);
        outputStream.println(italy.team1Wins);
        outputStream.println(italy.team2Wins);
        outputStream.println(italy.draws);
        outputStream.println(france.team1);
        outputStream.println(france.team2);
        outputStream.println(france.matchesPlayed);
        outputStream.println(france.team1Wins);
        outputStream.println(france.team2Wins);
        outputStream.println(france.draws);
        outputStream.println(mongolia.team1);
        outputStream.println(mongolia.team2);
        outputStream.println(mongolia.matchesPlayed);
        outputStream.println(mongolia.team1Wins);
        outputStream.println(mongolia.team2Wins);
        outputStream.println(mongolia.draws);
        outputStream.close();
        System.out.println("All Data written to file");
    }
    //reads all the data from a file and loads it into the game
    public static GamesWon[] ReadData(GamesWon spain, GamesWon italy, GamesWon france, GamesWon mongolia)throws IOException{
        GamesWon[] array = {spain,italy,france,mongolia};
        int i = 0;
        BufferedReader inputStream = new BufferedReader(new FileReader("FantasyFootball.txt"));
        String line = inputStream.readLine();
        while (line != null && i <4){
            array[i].team1 = line;
            line = inputStream.readLine();
            array[i].team2 = line;
            line = inputStream.readLine();
            array[i].matchesPlayed = Integer.parseInt(line);
            line = inputStream.readLine();
            array[i].team1Wins = Integer.parseInt(line);
            line = inputStream.readLine();
            array[i].team2Wins = Integer.parseInt(line);
            line = inputStream.readLine();
            array[i].draws = Integer.parseInt(line);
            line = inputStream.readLine();
            i++;
        }
        inputStream.close();
        return array;
    }
}

