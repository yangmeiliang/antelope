package com.lmy.antelope.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Data
@NoArgsConstructor
public class PageInfo<T> {

    private int code;
    private int page;
    private int limit;
    private int count;
    private List<T> data;
}
