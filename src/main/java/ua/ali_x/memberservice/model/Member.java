package ua.ali_x.memberservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ua.ali_x.memberservice.service.MongoGridFsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@ApiModel(description = "All details about the member of the company.")
public class Member {

    private static final Logger logger = Logger.getLogger(MongoGridFsService.class.getName());

    @Id
    @ApiModelProperty(notes = "Keep in mind that member id has ObjectId type")
    private ObjectId _id;
    private String fname;
    private String lname;
    @ApiModelProperty(notes = "The birth date should be only in DD.MM.YYYY format")
    private Date birth;
    private Integer zip;
    @ApiModelProperty(notes = "This field is filled automatically")
    private String pictureId;

    public Member(ObjectId _id, String fname, String lname, Date birth, Integer zip, String pictureId) {
        this._id = _id;
        this.fname = fname;
        this.lname = lname;
        this.birth = birth;
        this.zip = zip;
        this.pictureId = pictureId;
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

    public void setBirth(String birth) {
        try {
            this.birth = new SimpleDateFormat("dd.MM.yyyy").parse(birth);
        } catch (ParseException e) {
            logger.warning(e.getMessage());
        }
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}
