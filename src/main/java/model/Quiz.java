package model;
import java.util.*;

public class Quiz {
    private String[] questions = {
        "3, 1, 4, 1, 5",
        "1, 1, 2, 3, 5",
        "1, 4, 9, 16, 25",
        "2, 3, 5, 7, 11",
        "1, 2, 4, 8, 16"
    };
    private Integer scores = 0;
    private HashMap<String, Integer> answers =  new HashMap<String, Integer>();
    private Integer answerTimes = 0;

    public Quiz() {
        answers.put("3, 1, 4, 1, 5", 9);
        answers.put("1, 1, 2, 3, 5", 8);
        answers.put("1, 4, 9, 16, 25", 36);
        answers.put("2, 3, 5, 7, 11", 13);
        answers.put("1, 2, 4, 8, 16", 25);
    }

    public Integer getScores() {
        return scores;
    }
    public String[] getQuestions() {
        return questions;
    }
    public Integer getAnswerTimes() {
        return answerTimes;
    }

    public void increaseScores() {
        this.scores += 1;
    }
    public void increaseAnswerTimes() {
        this.answerTimes += 1;
    }
    public void decreaseQuestions(String question) {
        List<String> listQuestion = new ArrayList<String>(Arrays.asList(this.questions));
        listQuestion.remove(question);
        this.questions = listQuestion.toArray(new String[0]);
    }
    public void setAnswerResult(String question, Integer answer) {
        if (this.answers.get(question) == answer) {
            increaseScores();
        }
        decreaseQuestions(question);
        increaseAnswerTimes();
    }
}
