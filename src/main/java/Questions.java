import java.util.LinkedList;

public class Questions {
    private LinkedList popQuestions = new LinkedList();
    private LinkedList scienceQuestions = new LinkedList();
    private LinkedList sportsQuestions = new LinkedList();
    private LinkedList rockQuestions = new LinkedList();

    public Questions() {
    }

    void askQuestionAbout(String currentCategory) {
        if (currentCategory == "Pop")
            System.out.println(getPopQuestions().removeFirst());
        if (currentCategory == "Science")
            System.out.println(getScienceQuestions().removeFirst());
        if (currentCategory == "Sports")
            System.out.println(getSportsQuestions().removeFirst());
        if (currentCategory == "Rock")
            System.out.println(getRockQuestions().removeFirst());
    }

    public LinkedList getScienceQuestions() {
        return scienceQuestions;
    }

    public LinkedList getPopQuestions() {
        return popQuestions;
    }

    public LinkedList getSportsQuestions() {
        return sportsQuestions;
    }

    public LinkedList getRockQuestions() {
        return rockQuestions;
    }

    void setQuestions() {
        for (int i = 0; i < 50; i++) {
            getPopQuestions().addLast("Pop Question " + i);
            getScienceQuestions().addLast(("Science Question " + i));
            getSportsQuestions().addLast(("Sports Question " + i));
            getRockQuestions().addLast("Rock Question " + i);
        }
    }
}