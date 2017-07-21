import java.util.ArrayList;

public class Players {
    private ArrayList<String> players = new ArrayList<>();
    private int[] places = new int[6];
    private int[] purses = new int[6];
    private boolean[] inPenaltyBox = new boolean[6];

    public Players() {
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public int[] getPlaces() {
        return places;
    }

    public int[] getPurses() {
        return purses;
    }

    public boolean[] getInPenaltyBox() {
        return inPenaltyBox;
    }

    public boolean addPlayer(String playerName, Game game) {
        players.add(playerName);
        places[game.howManyPlayers()] = 0;
        purses[game.howManyPlayers()] = 0;
        inPenaltyBox[game.howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    void nextPlayer(Game game) {
        game.currentPlayer++;
        if (game.currentPlayer == players.size()) game.currentPlayer = 0;
    }

    void playerGotCorrectAnswer(int currentPlayer) {
        purses[currentPlayer]++;
    }

    boolean didPlayerWin(int currentPlayer) {
        return !(purses[currentPlayer] == 6);
    }

    void movePlayer(int roll, Game game) {
        getPlaces()[game.currentPlayer] = getPlaces()[game.currentPlayer] + roll;
        if (getPlaces()[game.currentPlayer] > 11) getPlaces()[game.currentPlayer] = getPlaces()[game.currentPlayer] - 12;
    }
}