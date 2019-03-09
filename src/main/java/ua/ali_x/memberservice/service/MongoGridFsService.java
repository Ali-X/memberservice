package ua.ali_x.memberservice.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Service
public class MongoGridFsService {
    private static final Logger logger = Logger.getLogger(MongoGridFsService.class.getName());
    @Autowired
    GridFsOperations gridOperations;

    @GetMapping("/save")
    public String saveFile(MultipartFile file) {
        try {
            DBObject metaData = new BasicDBObject();
            metaData.put("type", "image");

            InputStream inputStream = file.getInputStream();

            return gridOperations.store(inputStream, file.getOriginalFilename(), "image/png", metaData).getId().toString();
        } catch (IOException | NullPointerException e) {
            logger.warning(e.getMessage());
            return StringUtils.EMPTY;
        }
    }

/*    @GetMapping("/retrieve/imagefile")
    public String retrieveImageFile() throws IOException {
        // read file from MongoDB
        GridFSDBFile imageFile = gridOperations.findOne(new Query(Criteria.where("_id").is(imageFileId)));

        // Save file back to local disk
        imageFile.writeTo("D:\\JSA\\retrieve\\jsa-logo.png");

        System.out.println("Image File Name:" + imageFile.getFilename());

        return "Done";
    }

    @GetMapping("/retrieve/textfiles")
    public String retrieveTextFiles(){
        *//**
     * get all data files then save to local disk
     *//*

        // Retrieve all data files
        List<GridFSDBFile> textFiles = gridOperations.find(new Query(Criteria.where("metadata.type").is("data")));

        // Save all back to local disk
        textFiles.forEach(file->{

            try {
                String fileName = file.getFilename();

                file.writeTo("D:\\JSA\\retrieve\\"+ fileName);

                System.out.println("Text File Name: " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "Done";
    }

    @GetMapping("/delete/image")
    public String deleteFile(){
        // delete image via id
        gridOperations.delete(new Query(Criteria.where("_id").is(imageFileId)));

        return "Done";
    }*/

}
