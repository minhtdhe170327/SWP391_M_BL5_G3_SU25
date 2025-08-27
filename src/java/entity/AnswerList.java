package entity;

public class AnswerList {
    private int id;
    private String content;
    private String mentorName;
    private String menteeName;
    private String requestTitle;

    public AnswerList() {}

    public AnswerList(int id, String content, String mentorName, String menteeName, String requestTitle) {
        this.id = id;
        this.content = content;
        this.mentorName = mentorName;
        this.menteeName = menteeName;
        this.requestTitle = requestTitle;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMentorName() { return mentorName; }
    public void setMentorName(String mentorName) { this.mentorName = mentorName; }

    public String getMenteeName() { return menteeName; }
    public void setMenteeName(String menteeName) { this.menteeName = menteeName; }

    public String getRequestTitle() { return requestTitle; }
    public void setRequestTitle(String requestTitle) { this.requestTitle = requestTitle; }
}
