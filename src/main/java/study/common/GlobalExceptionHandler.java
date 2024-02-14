package study.common;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import study.entity.result.R;
import study.enums.HttpCodeEnum;

import java.io.FileNotFoundException;

/**
 * @author Persolute
 * @version 1.0
 * @description 全局异常处理器
 * @email 1538520381@qq.com
 * @date 2024/2/14 15:20
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /*
     * @author Persolute
     * @version 1.0
     * @description FileSizeLimitExceededException异常处理器
     * @email 1538520381@qq.com
     * @date 2024/2/14 15:35
     */
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public R<String> exceptionHandler(FileSizeLimitExceededException ex) {
        String pattern = "The field file exceeds its maximum permitted size of \\d+ bytes\\.";
        if (ex.getMessage().matches(pattern)) {
            return R.error(HttpCodeEnum.FILE_TO_BIG);
        }
        return R.error();
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description FileNotFoundException异常处理器
     * @email 1538520381@qq.com
     * @date 2024/2/14 15:59
     */
    @ExceptionHandler(FileNotFoundException.class)
    public R<String> exceptionHandler(FileNotFoundException ex) {
        String pattern = "^.+\\(系统找不到指定的文件。\\)$";
        if (ex.getMessage().matches(pattern)) {
            return R.error(HttpCodeEnum.FILE_UNEXIST);
        }
        return R.error();
    }
}
