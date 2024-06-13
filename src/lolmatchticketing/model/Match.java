package lolmatchticketing.model;

public class Match {
    private int matchId;
    private String name;
    private String team1;
    private String team2;
    private int ticketPrice;
    
    public Match(int matchId, String name, String team1, String team2, int ticketPrice) {
        this.matchId = matchId;
        this.name = name;
        this.team1 = team1;
        this.team2 = team2;
        this.ticketPrice = ticketPrice;
    }
    
    public int getMatchId() {
        return matchId;
    }
    public String getName() {
        return name;
    }
    public String getTeam1() {
        return team1;
    }
    public String getTeam2() {
        return team2;
    }
    public int getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return matchId + ", " + name + ", " + team1 + " vs " + team2 + ", " + ticketPrice + "원";
    }
}
