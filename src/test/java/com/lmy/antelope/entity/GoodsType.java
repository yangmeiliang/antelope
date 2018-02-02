package com.lmy.antelope.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangmeiliang
 * @date 2018/1/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsType {
    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer typeParent;
    private List<GoodsType> subGoods;

    public GoodsType(Integer typeId, String typeName, Integer typeParent) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeParent = typeParent;
    }
}
