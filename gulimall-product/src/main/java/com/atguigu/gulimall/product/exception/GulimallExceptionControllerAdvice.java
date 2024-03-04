package com.atguigu.gulimall.product.exception;

import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * ClassName: GulimallExceptionControllerAdvice
 * Package: com.atguigu.gulimall.product.exception
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/4 20:12
 * @Version 1.0
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
@RestControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题:{}，异常类型:{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> errorMap = new HashMap<>();
       bindingResult.getFieldErrors().forEach((fieldError -> {
           errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
       }));

        return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(),BizCodeEnume.VALID_EXCEPTION.getMsg()).put("data",errorMap);
    }
    //统一异常处理
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("error",throwable);
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }
}
