package com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.MinioController;

import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.MinioService.MinioService;
import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils.FileResponse;
import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.Utils.FileTypeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.activation.FileTypeMap;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file-management/minio")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @Operation(summary = "آپلود فایل")
    @RequestMapping(value="/upload",consumes = {"multipart/form-data"},method = RequestMethod.POST)
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FileResponse.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> uploadFile(@RequestParam("file") MultipartFile file) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        String fileType = FileTypeUtils.getFileType(file);
        if (fileType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "خطا در فرمت فایل ارسالی");
        }
        FileResponse fileResponse = minioService.putObject(file, fileType);
        result.setResult(ResponseEntity.status(HttpStatus.CREATED).body(fileResponse));
        return result;
    }


    @DeleteMapping(value = "/removeObject")
    @Operation(summary ="حذف یک آبجکت")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> removeObject(@RequestParam String objectName) throws Exception {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        Boolean state = minioService.removeObject(objectName);
        result.setResult(state ? ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
        return result;
    }

    @GetMapping(value = "/download")
    @Operation(summary ="دانلود فایل")
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> download(HttpServletResponse response,
                                               @RequestParam(required = false) String objectName,
                                               @RequestParam(required = false,defaultValue = "false") boolean preview) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        InputStream in = null;
        try {
            in = minioService.downloadObject(objectName);
            if(preview){
                response.setContentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(objectName)).toString());
            }
            else{
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName, StandardCharsets.UTF_8));
                response.setCharacterEncoding("UTF-8");
            }
            IOUtils.copy(in, response.getOutputStream());

        } catch (UnsupportedEncodingException e) {
            LOGGER.info("MinioController | download | UnsupportedEncodingException : " + e.getMessage());
        } catch (Exception e) {
            LOGGER.info("MinioController | download | IOException : " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.info("MinioController | download | IOException : " + e.getMessage());
                }
            }
        }
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(in));
        return result;
    }


//
//    @DeleteMapping(value = "/removeListObject")
//    @Operation(summary ="حذف گروهی آبجکت ها")
//    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) })
//    public @ResponseBody
//    DeferredResult<ResponseEntity<?>> removeObjects(@RequestParam String bucketName, @RequestParam List<String> objectNameList) throws Exception {
//        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
//        Boolean state = minioService.removeListObject(bucketName, objectNameList);
//        result.setResult(state ? ResponseEntity.status(HttpStatus.OK).body(true) :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
//        return result;
//    }
//

//    @GetMapping(value = "/showListObjectNameAndDownloadUrl")
//    @Operation(summary ="لیست آبجکت ها و url دانلود آن ها")
//    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
//    public @ResponseBody
//    DeferredResult<ResponseEntity<?>> getListObjectNameAndDownloadUrl(@RequestParam String bucketName) throws Exception {
//        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
//        Map<String, String> map = new HashMap<>();
//        List<String> listObjectNames = minioService.listObjectNames(bucketName);
//        String hostName;
//        try (Socket socket = new Socket()) {
//            hostName = socket.getLocalAddress().getHostName();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        int portNumber = 8282;
//        String url = hostName + ":" + portNumber + "/minio/download/" + bucketName + "/";
//
//        for (String listObjectName : listObjectNames) {
//            map.put(listObjectName, url + listObjectName);
//        }
//        result.setResult(ResponseEntity.status(HttpStatus.OK).body(map));
//
//        return result;
//    }


    //
//    @PostMapping(value = "/addBucket")
//    @Operation(summary = "اضافه کردن یک باکت جدید")
//    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) })
//    public @ResponseBody
//    DeferredResult<ResponseEntity<?>> addBucket(@RequestParam String bucketName) throws Exception {
//        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
//        Boolean flag = minioService.makeBucket(bucketName);
//        result.setResult(flag ? ResponseEntity.status(HttpStatus.CREATED).body(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
//        return result;
//    }

//    @GetMapping(value = "/showObject")
//    @Operation(summary = "لیست تمام آبجکت های داخل یک باکت")
//    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
//    public @ResponseBody
//    DeferredResult<ResponseEntity<?>> getObjects(@RequestParam String bucketName) throws Exception {
//        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
//        List<String> listObject = minioService.listObjectNames(bucketName);
//        result.setResult(ResponseEntity.status(HttpStatus.OK).body(listObject));
//        return result;
//    }


//    @GetMapping(value = "/showBucketName")
//    @Operation(summary = "لیست تمامی باکت های موجود")
//    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
//    public @ResponseBody
//    DeferredResult<ResponseEntity<?>> getBucketNames() throws Exception {
//        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
//        List<String> listObject = minioService.listBucketName();
//        result.setResult(ResponseEntity.status(HttpStatus.OK).body(listObject));
//        return result;
//    }

}
