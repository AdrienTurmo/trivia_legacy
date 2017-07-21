public class QuestionFieldRepartition {
    private final Game game;

    public QuestionFieldRepartition(Game game) {
        this.game = game;
    }

    String currentCategory() {
        if (game.getPlaces()[game.getCurrentPlayer()] == 0) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 4) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 8) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 1) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 5) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 9) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 2) return "Sports";
        if (game.getPlaces()[game.getCurrentPlayer()] == 6) return "Sports";
        if (game.getPlaces()[game.getCurrentPlayer()] == 10) return "Sports";
        return "Rock";
    }
}