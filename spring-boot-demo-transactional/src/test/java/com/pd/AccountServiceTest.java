package com.pd;

import com.pd.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/22 09:46
 */
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    public void testTransferAccount(){
        service.transferAccount(2,1,100.00);
    }
}
