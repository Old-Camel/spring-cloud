package com.yunzainfo.oa.filecenter.web;

import com.mongodb.gridfs.GridFSDBFile;
import com.yunzainfo.oa.filecenter.service.MongoFileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/27
 * Email:old_camel@126.com
 */
@RestController
@RefreshScope
@Slf4j
public class FileController {
    @Autowired
    private MongoFileService mongoFileService;

    @ApiOperation(value = "下载文件", notes = "下载文件,参数为id")
    @ApiImplicitParam(name = "id", value = "文件标识", required = true,defaultValue = "5a435fd1dd5174442034c0fa",dataType = "String",paramType = "path")
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> test(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            GridFSDBFile filesInGridFs = mongoFileService.findFilesInGridFs(id);
            if(filesInGridFs==null){
                headers.setContentType(MediaType.valueOf("text/plain;charset=UTF-8"));
                return new ResponseEntity<byte[]>("文件未找到".getBytes(), headers, HttpStatus.NOT_FOUND);
            }
            InputStream inputStream = filesInGridFs.getInputStream();
            String gridFsFilename = filesInGridFs.getFilename();
            String filename = new String(gridFsFilename.getBytes("UTF-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("filename", filename);
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>("程序出现异常".getBytes(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "上传文件", notes = "上传文件,参数为上传的文件,若上传成功,从header中获取id的值为文件id")
    @PostMapping("/file")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if(multipartFile==null){
            return new ResponseEntity<String>("文件不能为空", null, HttpStatus.BAD_REQUEST);
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String id= mongoFileService.storeFileInGridFs(multipartFile.getOriginalFilename(),multipartFile.getInputStream());
            headers.setContentType(MediaType.valueOf("text/plain;charset=UTF-8"));
            headers.set("id", id);
            return new ResponseEntity<String>("上传成功", headers, HttpStatus.OK);
        } catch (Exception e) {
            //throw new Exception();
            return new ResponseEntity<String>("程序异常", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "删除文件", notes = "删除文件,参数为id,成功或失败信息在响应体中,文件id存入header中返回")
    @ApiImplicitParam(name = "id", value = "文件标识", required = true,defaultValue = "5a435fd1dd5174442034c0fa",dataType = "String",paramType = "path")
    @DeleteMapping("/file/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType(MediaType.valueOf("text/plain;charset=UTF-8"));
            headers.set("id",id);
            GridFSDBFile filesInGridFs = mongoFileService.findFilesInGridFs(id);
            if( mongoFileService.findFilesInGridFs(id)!=null) {
                Boolean aBoolean = mongoFileService.deleteFilesInGridFs(id);
                if (aBoolean) {
                   return new ResponseEntity<String>("删除成功", headers, HttpStatus.OK);
               }
           }else{
            return new ResponseEntity<String>("要删除的文件不存在", headers, HttpStatus.BAD_REQUEST);
           }
            return new ResponseEntity<String>("删除失败", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //return "失败";
            log.error(e.getMessage());
            return new ResponseEntity<String>("删除失败", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
