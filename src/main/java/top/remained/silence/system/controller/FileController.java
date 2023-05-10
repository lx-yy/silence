package top.remained.silence.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Project：silence
 * Date：2022/9/9
 * Time：23:10
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/up")
    public String getImg(MultipartFile file) throws IOException {
//        获取上传时的文件名字

        String fileName = file.getOriginalFilename();

//        解决重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        String finaleName = UUID.randomUUID().toString() + hzName;
        //E:\WebstormProjects\vue-lj\src\assets
//        File.separator+"www"+File.separator+"wwwroot"+File.separator+"silence"+File.separator+"dist"+File.separator+"img"+File.separator
        file.transferTo(new File("E:\\WebstormProjects\\vue-lj\\src\\assets\\"+finaleName));
        return finaleName;
    }
}
