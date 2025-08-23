/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus TUF
 */
public class MentorRequest {
    private int id;
    private int requestid;
    private int mentorid;

    public MentorRequest() {
    }

    public MentorRequest(int id, int requestid, int mentorid) {
        this.id = id;
        this.requestid = requestid;
        this.mentorid = mentorid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getMentorid() {
        return mentorid;
    }

    public void setMentorid(int mentorid) {
        this.mentorid = mentorid;
    }
}
