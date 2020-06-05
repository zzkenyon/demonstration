package com.pd.redis.sort;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class SortTestServiceTest {
    private SortTestService sortTestService;
    @Autowired
    public void setSortTestService(SortTestService sortTestService) {
        this.sortTestService = sortTestService;
    }

    @Test
    public void init() {
        sortTestService.init();
    }

    @Test
    public void getTop() {
        Set<Object> res = sortTestService.getTop(3);
        for(Object o : res){
            System.out.println(o);
        }
    }

    @Test
    public void range() {
        sortTestService.range();
    }

    @Test
    public void rangeWithScore() {
        sortTestService.rangeWithScore();
    }
}