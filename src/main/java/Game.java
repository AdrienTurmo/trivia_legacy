public class Game {

    final Questions questions = new Questions();
    final Players players = new Players();
    private final QuestionFieldRepartition questionFieldRepartition = new QuestionFieldRepartition();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            questions.getPopQuestions().addLast("Pop Question " + i);
            questions.getScienceQuestions().addLast(("Science Question " + i));
            questions.getSportsQuestions().addLast(("Sports Question " + i));
            questions.getRockQuestions().addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public int howManyPlayers() {
        return players.getPlayers().size();
    }

    public void roll(int roll) {
        System.out.println(players.getPlayers().get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.getInPenaltyBox()[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.getPlayers().get(currentPlayer) + " is getting out of the penalty box");
                players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] + roll;
                if (players.getPlaces()[currentPlayer] > 11) players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] - 12;

                System.out.println(players.getPlayers().get(currentPlayer)
                        + "'s new location is "
                        + players.getPlaces()[currentPlayer]);
                System.out.println("The category is " + questionFieldRepartition.currentCategory(players.getPlaces()[currentPlayer]));
                questions.askQuestion(questionFieldRepartition.currentCategory(players.getPlaces()[currentPlayer]));
            } else {
                System.out.println(players.getPlayers().get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] + roll;
            if (players.getPlaces()[currentPlayer] > 11) players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] - 12;

            System.out.println(players.getPlayers().get(currentPlayer)
                    + "'s new location is "
                    + players.getPlaces()[currentPlayer]);
            System.out.println("The category is " + questionFieldRepartition.currentCategory(players.getPlaces()[currentPlayer]));
            questions.askQuestion(questionFieldRepartition.currentCategory(players.getPlaces()[currentPlayer]));
        }

    }


    public boolean wasCorrectlyAnswered() {
        if (players.getInPenaltyBox()[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                players.getPurses()[currentPlayer]++;
                System.out.println(players.getPlayers().get(currentPlayer)
                        + " now has "
                        + players.getPurses()[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.getPlayers().size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.getPlayers().size()) currentPlayer = 0;
                return true;
            }

        } else {

            System.out.println("Answer was correct!!!!");
            players.getPurses()[currentPlayer]++;
            System.out.println(players.getPlayers().get(currentPlayer)
                    + " now has "
                    + players.getPurses()[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.getPlayers().size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getPlayers().get(currentPlayer) + " was sent to the penalty box");
        players.getInPenaltyBox()[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.getPlayers().size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.getPurses()[currentPlayer] == 6);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getPlaces() {
        return players.getPlaces();
    }
}
