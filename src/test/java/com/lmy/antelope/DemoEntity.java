package com.lmy.antelope;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yangmeiliang
 * @date 2018/1/22
 */
@Data
@NoArgsConstructor
public class DemoEntity {
    private Integer count;
    private String brand;
    private String month;
    private Double percent;

    public DemoEntity(Integer count, String brand, String month) {
        this.count = count;
        this.brand = brand;
        this.month = month;
    }
}
