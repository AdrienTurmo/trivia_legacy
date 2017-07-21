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

                movePlayer(roll);
                displayLocationAndQuestionCategory();
                questions.askQuestionAbout(questionFieldRepartition.categoryOfCase(players.getPlaces()[currentPlayer]));
            } else {
                System.out.println(players.getPlayers().get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            movePlayer(roll);
            displayLocationAndQuestionCategory();
            questions.askQuestionAbout(questionFieldRepartition.categoryOfCase(players.getPlaces()[currentPlayer]));
        }

    }

    private void movePlayer(int roll) {
        players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] + roll;
        if (players.getPlaces()[currentPlayer] > 11) players.getPlaces()[currentPlayer] = players.getPlaces()[currentPlayer] - 12;
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
                playerGotCorrectAnswer();

                displayPlayerStatus();

                boolean winner = didPlayerWin();

                nextPlayer();

                return winner;
            } else {
                nextPlayer();
                return true;
            }

        } else {

            System.out.println("Answer was correct!!!!");
            playerGotCorrectAnswer();
            displayPlayerStatus();

            boolean winner = didPlayerWin();
            nextPlayer();

            return winner;
        }
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.getPlayers().size()) currentPlayer = 0;
    }

    private void displayPlayerStatus() {
        System.out.println(players.getPlayers().get(currentPlayer)
                + " now has "
                + players.getPurses()[currentPlayer]
                + " Gold Coins.");
    }

    private void playerGotCorrectAnswer() {
        players.getPurses()[currentPlayer]++;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getPlayers().get(currentPlayer) + " was sent to the penalty box");
        players.getInPenaltyBox()[currentPlayer] = true;

        nextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.getPurses()[currentPlayer] == 6);
    }

}
