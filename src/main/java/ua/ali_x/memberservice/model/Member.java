package ua.ali_x.memberservice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Member {

    @Id
    private ObjectId _id;
    private String fname;
    private String lname;
    private Date birth;
    private Integer zip;
    private String picture;

    public Member(ObjectId _id, String fname, String lname, Date birth, Integer zip, String picture) {
        this._id = _id;
        this.fname = fname;
        this.lname = lname;
        this.birth = birth;
        this.zip = zip;
        this.picture = picture;
    }

    public Member() {

    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
