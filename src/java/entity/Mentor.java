package entity;

import java.sql.Date;

public class Mentor {
    private int id;
    private int accountid;
    private String accountname;
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private Date birthday;
    private String sex;
    private String introduce;
    private String achievement;
    private String avatar;
    private float cost;
    private float averageStar;

    public Mentor() {}

    public Mentor(int id, int accountid, String firstname, String lastname, String address, String phone, Date birthday, String sex, String introduce, String achievement, String avatar, float cost) {
        this.id = id;
        this.accountid = accountid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.achievement = achievement;
        this.avatar = avatar;
        this.cost = cost;
    }

    public Mentor(int id, int accountid, String firstname, String lastname, String address, String phone, Date birthday, String sex, String introduce, String achievement, String avatar, float cost, float averageStar) {
        this.id = id;
        this.accountid = accountid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.achievement = achievement;
        this.avatar = avatar;
        this.cost = cost;
        this.averageStar = averageStar;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }
    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getAccountname() {
        return accountname;
    }
    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return (firstname != null ? firstname : "") + " " + (lastname != null ? lastname : "");
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAchievement() {
        return achievement;
    }
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getAverageStar() {
        return averageStar;
    }
    public void setAverageStar(float averageStar) {
        this.averageStar = averageStar;
    }
}
