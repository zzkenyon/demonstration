package com.pd.redis.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 09:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String name;
    private Integer score;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
