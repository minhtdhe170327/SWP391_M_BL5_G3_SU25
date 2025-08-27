package entity;

public class Feedback {
    private int id;
    private int menteeid;
    private int star;
    private String comment;
    private String menteeName; // thêm field

    public Feedback() {}

    public Feedback(int id, int menteeid, int star, String comment) {
        this.id = id;
        this.menteeid = menteeid;
        this.star = star;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getMenteeid() {
        return menteeid;
    }
    public void setMenteeid(int menteeid) {
        this.menteeid = menteeid;
    }

    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMenteeName() {
        return menteeName;
    }
    public void setMenteeName(String menteeName) {
        this.menteeName = menteeName;
    }
}
