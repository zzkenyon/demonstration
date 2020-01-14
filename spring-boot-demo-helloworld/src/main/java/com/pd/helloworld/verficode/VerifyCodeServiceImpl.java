package com.pd.helloworld.verficode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码图片生成接口的默认实现类
 * @author zhaozhengkang
 * @date 2019/12/16 15:15
 */
@Service
@Slf4j
public class VerifyCodeServiceImpl implements VerifyCodeService{

    @Override
    public String createPicVerificationCode(String param,HttpServletRequest request) {
        VerifyCode code = VerifyCode.getInstance();
        try {
            Assert.notNull(code.getContext(),"验证码文本不能为空");
            request.getSession().setAttribute(param,code.getContext());
            return code.getBase64VerifyCode();
        }catch (Exception e){
            log.error("生成图片验证码异常"+e);
            e.printStackTrace();
        }
        return null;
    }
}
