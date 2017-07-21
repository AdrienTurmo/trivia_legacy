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

        getPlayers().add(playerName);
        getPlaces()[game.howManyPlayers()] = 0;
        getPurses()[game.howManyPlayers()] = 0;
        getInPenaltyBox()[game.howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayers().size());
        return true;
    }
}