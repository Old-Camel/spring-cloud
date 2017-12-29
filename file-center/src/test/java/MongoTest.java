
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.yunzainfo.oa.filecenter.config.GridFsConfiguration;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;
/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/27
 * Email:old_camel@126.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GridFsConfiguration.class)
public class MongoTest {
    @Autowired
    GridFsTemplate gridFsTemplate;
    private String key;
    // 存文件
    @Test
    public void storeFileInGridFs() {
        //Resource file = new ClassPathResource("application.yml.b");
         Resource file = new FileSystemResource("E:\\个人开发\\portal-3.0.4.zip");
        try {
            GridFSFile yml = gridFsTemplate.store(file.getInputStream(), file.getFilename(), "zip");
key=yml.getId().toString();
            System.out.println(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 下载文件
    @Test
    public void findFilesInGridFs() throws IOException {
        List<GridFSDBFile> result = gridFsTemplate.find(query(whereFilename().is("application.yml.b")));
        Query query = new Query();
        //query.
        Criteria criteria = new Criteria();

       /* List<GridFSDBFile> result2 = gridFsTemplate.find(query(where("_id").is(new ObjectId("5a436424dd51741a3c665113"))));

        GridFSDBFile gridFSDBFile = result2.get(0);

        File file = new File("G:\\1227\\" + gridFSDBFile.getFilename());
        //file.mkdirs();
        gridFSDBFile.writeTo(file);*/

    }
    @Test
    public void delete(){
        gridFsTemplate.delete(query(where("_id").is(new ObjectId("5a436424dd51741a3c665113"))));
    }
    // 所有文件
    @Test
    public void readFilesFromGridFs() {
        GridFsResource[] txtFiles = gridFsTemplate.getResources("*");
        for (GridFsResource txtFile : txtFiles) {
            System.out.println(txtFile.getFilename());
        }
    }

}