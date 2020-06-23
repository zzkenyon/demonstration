package com.pd;

import com.github.pagehelper.PageHelper;
import com.pd.model.Blog;
import com.pd.service.MbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/21 11:35
 */
@RestController
public class MbController {

    @Resource
    private MbService mbService;

    @GetMapping("/page-helper-test")
    public List<Blog> queryBlogs(@RequestParam Integer pageNum){
        PageHelper.startPage(pageNum,2);
        List<Blog> res = mbService.queryBlogs();
        return res;
    }

}
