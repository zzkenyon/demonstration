package com.pd.mapper;

import com.pd.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/22 09:07
 */
@Mapper
public interface AccountMapper {
    @Select("select * from account where id = (select account_id from user where id = #{uid})")
    Account queryByUid(@Param("uid") Integer uid);

    @Update("update account set amount = #{money} where id = #{accountId}")
    Integer updateAmount(@Param("accountId") Integer var1,@Param("money") Double var2);

}
