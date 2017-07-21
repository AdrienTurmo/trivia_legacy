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

    public boolean addPlayer(String playerName) {

        players.players.add(playerName);
        players.places[howManyPlayers()] = 0;
        players.purses[howManyPlayers()] = 0;
        players.inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.players.size();
    }

    public void roll(int roll) {
        System.out.println(players.players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.players.get(currentPlayer) + " is getting out of the penalty box");
                players.places[currentPlayer] = players.places[currentPlayer] + roll;
                if (players.places[currentPlayer] > 11) players.places[currentPlayer] = players.places[currentPlayer] - 12;

                System.out.println(players.players.get(currentPlayer)
                        + "'s new location is "
                        + players.places[currentPlayer]);
                System.out.println("The category is " + questionFieldRepartition.currentCategory(players.places[currentPlayer]));
                questions.askQuestion(questionFieldRepartition.currentCategory(players.places[currentPlayer]));
            } else {
                System.out.println(players.players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            players.places[currentPlayer] = players.places[currentPlayer] + roll;
            if (players.places[currentPlayer] > 11) players.places[currentPlayer] = players.places[currentPlayer] - 12;

            System.out.println(players.players.get(currentPlayer)
                    + "'s new location is "
                    + players.places[currentPlayer]);
            System.out.println("The category is " + questionFieldRepartition.currentCategory(players.places[currentPlayer]));
            questions.askQuestion(questionFieldRepartition.currentCategory(players.places[currentPlayer]));
        }

    }


    public boolean wasCorrectlyAnswered() {
        if (players.inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                players.purses[currentPlayer]++;
                System.out.println(players.players.get(currentPlayer)
                        + " now has "
                        + players.purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.players.size()) currentPlayer = 0;
                return true;
            }

        } else {

            System.out.println("Answer was correct!!!!");
            players.purses[currentPlayer]++;
            System.out.println(players.players.get(currentPlayer)
                    + " now has "
                    + players.purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.players.get(currentPlayer) + " was sent to the penalty box");
        players.inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.purses[currentPlayer] == 6);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getPlaces() {
        return players.places;
    }
}
