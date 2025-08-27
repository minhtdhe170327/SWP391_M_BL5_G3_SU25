package entity;

import java.sql.Date;

public class Mentee {
    private int id;
    private int accountid;
    private String firstname;
    private String lastname;
    private String name;
    private String address;
    private String phone;
    private Date birthday;
    private String sex;
    private String introduce;
    private String avatar;

    public Mentee() {
    }

    public Mentee(int id, int accountid, String firstname, String lastname, String address,
                  String phone, Date birthday, String sex, String introduce, String avatar) {
        this.id = id;
        this.accountid = accountid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.avatar = avatar;
    }

    public Mentee(int id, int accountid, String name, String address,
                  String phone, Date birthday, String sex, String introduce, String avatar) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.avatar = avatar;
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
        return (firstname != null ? firstname : "") 
             + " " 
             + (lastname != null ? lastname : "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
