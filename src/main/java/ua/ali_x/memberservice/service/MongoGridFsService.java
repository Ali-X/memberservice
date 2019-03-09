package ua.ali_x.memberservice.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Service
public class MongoGridFsService {
    private static final Logger logger = Logger.getLogger(MongoGridFsService.class.getName());
    @Autowired
    GridFsOperations gridOperations;

    String saveFile(MultipartFile file) {
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

    public GridFSDBFile retrieveImageFile(String fileId) {
        return gridOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
    }

    void deleteFile(String fileId) {
        gridOperations.delete(new Query(Criteria.where("_id").is(fileId)));
    }

}
