public class Game {

    final Questions questions = new Questions();
    final Players players = new Players();
    private final QuestionFieldRepartition questionFieldRepartition = new QuestionFieldRepartition();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        questions.setQuestions();
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

                players.movePlayer(roll, this.currentPlayer);
                displayLocationAndQuestionCategory();
                questions.askQuestionAbout(questionFieldRepartition.categoryOfCase(players.getPlaces()[currentPlayer]));
            } else {
                System.out.println(players.getPlayers().get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            players.movePlayer(roll, this.currentPlayer);
            displayLocationAndQuestionCategory();
            questions.askQuestionAbout(questionFieldRepartition.categoryOfCase(players.getPlaces()[currentPlayer]));
        }

    }

    private void displayLocationAndQuestionCategory() {
        System.out.println(players.getPlayers().get(currentPlayer)
                + "'s new location is "
                + players.getPlaces()[currentPlayer]);
        System.out.println("The category is " + questionFieldRepartition.categoryOfCase(players.getPlaces()[currentPlayer]));
    }


    public boolean wasCorrectlyAnswered() {
        if (players.getInPenaltyBox()[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                players.playerGotCorrectAnswer(this.currentPlayer);

                displayPlayerStatus();

                boolean winner = players.didPlayerWin(currentPlayer);

                players.nextPlayer(this);

                return winner;
            } else {
                players.nextPlayer(this);
                return true;
            }

        } else {

            System.out.println("Answer was correct!!!!");
            players.playerGotCorrectAnswer(this.currentPlayer);
            displayPlayerStatus();

            boolean winner = players.didPlayerWin(currentPlayer);
            players.nextPlayer(this);

            return winner;
        }
    }

    private void displayPlayerStatus() {
        System.out.println(players.getPlayers().get(currentPlayer)
                + " now has "
                + players.getPurses()[currentPlayer]
                + " Gold Coins.");
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getPlayers().get(currentPlayer) + " was sent to the penalty box");
        players.getInPenaltyBox()[currentPlayer] = true;

        players.nextPlayer(this);
        return true;
    }


}
