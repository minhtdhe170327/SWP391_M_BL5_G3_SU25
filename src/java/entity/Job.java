package entity;

public class Job {
    private int id;
    private String jobname;
    private String description; // thêm mới
    private String status;      // thêm mới

    // Constructor
    public Job() {}

    public Job(int id, String jobname, String description, String status) {
        this.id = id;
        this.jobname = jobname;
        this.description = description;
        this.status = status;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }
    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
