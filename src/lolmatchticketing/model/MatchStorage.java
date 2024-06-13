package lolmatchticketing.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatchStorage {
    ArrayList<Match> matchList = new ArrayList<>();
    final int MAX_QUANTITY = 10;
    private String matchFilename = "match.txt";
    private int lastId;
    private boolean isSaved;
    
    public MatchStorage() throws IOException {
        loadMatchListFromFile();
        generateLastId();
        isSaved = true;
    }

    private void generateLastId() {
        lastId = 0;
        for (Match match : matchList) {
            int id = match.getMatchId();
            if (id > lastId) lastId = id;
        }
    }

    private void loadMatchListFromFile() throws IOException {
        FileReader fr;
        try {
            fr = new FileReader(matchFilename);
            BufferedReader br = new BufferedReader(fr);
            String idStr;
            while ((idStr = br.readLine()) != null && !idStr.equals("")) {
                int id = Integer.parseInt(idStr);
                String name = br.readLine();
                String team1 = br.readLine();
                String team2 = br.readLine();
                int ticketPrice = Integer.parseInt(br.readLine());
                matchList.add(new Match(id, name, team1, team2, ticketPrice));
            }
            fr.close();
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumMatches() {
        return matchList.size();
    }

    public String getMatchInfo(int i) {
        return matchList.get(i).toString();
    }

    public boolean isValidMatch(int matchId) {
        for (Match match : matchList) {
            if (match.getMatchId() == matchId) return true;
        }
        return false;
    }

    public Match getMatchById(int matchId) {
        for (Match match : matchList) {
            if (match.getMatchId() == matchId)
                return match;
        }
        return null;
    }

    public int getMaxQuantity() {
        return MAX_QUANTITY;
    }

    public boolean isEmpty() {
        return matchList.isEmpty();
    }

    public void deleteItem(int matchId) {
        matchList.remove(getMatchById(matchId));
        isSaved = false;
    }

    public void addMatch(String name, String team1, String team2, int ticketPrice) {
        Match match = new Match(++lastId, name, team1, team2, ticketPrice);
        matchList.add(match);
        isSaved = false;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void saveMatchList2File() {
        try {
            FileWriter fw = new FileWriter(matchFilename);
            for (Match match : matchList) {
                fw.write(match.getMatchId() + "\n");
                fw.write(match.getName() + "\n");
                fw.write(match.getTeam1() + "\n");
                fw.write(match.getTeam2() + "\n");
                fw.write(match.getTicketPrice() + "\n");
            }
            fw.close();
            isSaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
