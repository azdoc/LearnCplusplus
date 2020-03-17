package cplusplus.learn.trinity.learnc.model;

/**
 * Created by DELL-PC on 6/24/2017.
 */

public class interviewFaqModel {
    private String id;
    private String question ;
    private String answer;
    private String favourite;

    public interviewFaqModel(){}

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public interviewFaqModel(String id, String question, String answer, String favourite){
        this.id=id;
        this.question=question ;
        this.answer=answer;
        this.favourite=favourite;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

