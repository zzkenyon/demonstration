package com.pd.helloworld.verficode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/13 11:24
 */
@RestController
public class VerifyController {
    private static final String PARAM_NAME = "ImgVerificationCodeMark";
    private VerifyCodeService service;
    @Autowired
    public void setService(VerifyCodeService service) {
        this.service = service;
    }

    @GetMapping(path = "/getcode")
    public String getPictureCode(HttpServletRequest request){
        return service.createPicVerificationCode(PARAM_NAME, request);
    }
}
