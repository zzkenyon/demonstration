package com.pd.service;

import com.pd.mapper.AccountMapper;
import com.pd.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/22 09:06
 */
@Service
@Slf4j
public class AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Transactional(timeout = -1,propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,readOnly = false, rollbackFor = RuntimeException.class)
    public void transferAccount(Integer fromUserId,Integer toUserId,Double money){
        Account fromUserAccount = accountMapper.queryByUid(fromUserId);
        Account toUserAccount = accountMapper.queryByUid(toUserId);
        accountMapper.updateAmount(toUserAccount.getId(),toUserAccount.getAmount()+money);
        if(fromUserAccount.getAmount()-money < 0)
        {
            throw new RuntimeException("转出方余额不足！");
        }
        accountMapper.updateAmount(fromUserAccount.getId(),fromUserAccount.getAmount()-money);
        log.info("转账成功");
    }

}
