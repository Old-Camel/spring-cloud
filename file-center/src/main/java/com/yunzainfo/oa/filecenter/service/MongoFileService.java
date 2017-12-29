package com.yunzainfo.oa.filecenter.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/27
 * Email:old_camel@126.com
 */
@Service
public class MongoFileService {
    @Autowired
    GridFsTemplate gridFsTemplate;
    //上传文件
    public String storeFileInGridFs(String fileName, InputStream inputStream) throws IOException {

            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            GridFSFile gridFSFile = gridFsTemplate.store(inputStream, fileName, suffix);
            return  gridFSFile.getId().toString();
    }
    // 下载文件
    public GridFSDBFile findFilesInGridFs(String id) throws IOException {
        List<GridFSDBFile> result = gridFsTemplate.find(query(where("_id").is(new ObjectId(id))));
        if (result.size()!=0) {
            return result.get(0);
        }else{
            return null;
        }
    }
    // 删除文件
    public Boolean deleteFilesInGridFs(String id) throws IOException {
         gridFsTemplate.delete(query(where("_id").is(new ObjectId(id))));
        List<GridFSDBFile> result = gridFsTemplate.find(query(where("_id").is(new ObjectId(id))));
       return  Optional.ofNullable(result.size()).map((size)->{return size==0;}).orElse(false);
    }
    // 所有文件
    public void readFilesFromGridFs() {
        GridFsResource[] txtFiles = gridFsTemplate.getResources("*");
        for (GridFsResource txtFile : txtFiles) {
            System.out.println(txtFile.getFilename());
        }
    }

}
