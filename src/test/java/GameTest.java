import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game triviaGame;

    @Before
    public void setUp() throws Exception {
        triviaGame = new Game();
    }

    @Test
    public void game_should_have_50_questions_of_each_categories() throws Exception {
        assertThat(triviaGame.questions.popQuestions.size()).isEqualTo(50);
        assertThat(triviaGame.questions.scienceQuestions.size()).isEqualTo(50);
        assertThat(triviaGame.questions.sportsQuestions.size()).isEqualTo(50);
        assertThat(triviaGame.questions.rockQuestions.size()).isEqualTo(50);
    }

    @Test
    public void players_should_be_added_to_the_game() throws Exception {
        triviaGame.addPlayer("player1");
        triviaGame.addPlayer("player2");

        assertThat(triviaGame.howManyPlayers()).isEqualTo(2);
        assertThat(triviaGame.players).contains("player1");
        assertThat(triviaGame.players).contains("player2");
    }

    @Test
    public void all_players_should_start_with_0_points() throws Exception {
        triviaGame.addPlayer("player1");
        triviaGame.addPlayer("player2");
        triviaGame.addPlayer("player3");
        triviaGame.addPlayer("player4");
        triviaGame.addPlayer("player5");

        assertThat(triviaGame.purses).isEqualTo(new int[]{0, 0, 0, 0, 0, 0});
    }

    @Test
    public void all_players_should_start_at_the_first_place() throws Exception {
        triviaGame.addPlayer("player1");
        triviaGame.addPlayer("player2");
        triviaGame.addPlayer("player3");
        triviaGame.addPlayer("player4");
        triviaGame.addPlayer("player5");

        assertThat(triviaGame.purses).isEqualTo(new int[]{0, 0, 0, 0, 0, 0});
    }

    @Test
    public void all_players_should_start_out_of_the_penalty_box() throws Exception {
        triviaGame.addPlayer("player1");
        triviaGame.addPlayer("player2");
        triviaGame.addPlayer("player3");
        triviaGame.addPlayer("player4");
        triviaGame.addPlayer("player5");

        assertThat(triviaGame.inPenaltyBox).isEqualTo(new boolean[]{false, false, false, false, false, false});
    }

    @Test
    public void an_one_player_game_is_not_playable() throws Exception {
        triviaGame.addPlayer("plaver");

        assertThat(triviaGame.isPlayable()).isFalse();
    }

    @Test
    public void a_two_players_game_is_not_playable() throws Exception {
        triviaGame.addPlayer("plaver1");
        triviaGame.addPlayer("plaver2");

        assertThat(triviaGame.isPlayable()).isTrue();
    }

    @Test
    public void a_roll_should_advance_the_player() throws Exception {
        triviaGame.addPlayer("player1");

        triviaGame.roll(2);

        assertThat(triviaGame.places[0]).isEqualTo(2);
    }

    @Test
    public void a_player_should_go_back_to_place_0_if_it_overextends_over_12() throws Exception {
        triviaGame.addPlayer("player1");

        triviaGame.roll(13);

        assertThat(triviaGame.places[0]).isEqualTo(1);
    }

    @Test
    public void a_player_in_penalty_box_should_stay_in_it_if_he_roll_an_even_number() throws Exception {
        triviaGame.addPlayer("player");
        triviaGame.inPenaltyBox[0] = true;

        triviaGame.roll(3);

        assertThat(triviaGame.isGettingOutOfPenaltyBox).isTrue();
    }

    @Test
    public void a_player_in_penalty_box_should_go_out_of_it_if_he_roll_an_odd_number() throws Exception {
        triviaGame.addPlayer("player");
        triviaGame.inPenaltyBox[0] = true;

        triviaGame.roll(3);

        assertThat(triviaGame.isGettingOutOfPenaltyBox).isTrue();
    }

    @Test
    public void there_should_be_pop_question_on_case_0_4_and_8() throws Exception {
        triviaGame.addPlayer("player");

        Method method = Game.class.getDeclaredMethod("currentCategory",null);
        method.setAccessible(true);

        triviaGame.places[0]=0;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Pop");
        triviaGame.places[0]=4;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Pop");
        triviaGame.places[0]=8;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Pop");
    }

    @Test
    public void there_should_be_science_questions_on_case_1_5_and_9() throws Exception {
        triviaGame.addPlayer("player");

        Method method = Game.class.getDeclaredMethod("currentCategory",null);
        method.setAccessible(true);

        triviaGame.places[0]=1;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Science");
        triviaGame.places[0]=5;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Science");
        triviaGame.places[0]=9;
        assertThat(method.invoke(triviaGame,null)).isEqualTo("Science");
    }
}