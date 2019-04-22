package com.lmy.antelope.config.mybatis;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yangmeiliang
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createdTime = new Date();
    private Date modifiedTime = new Date();
    private Boolean isDeleted = Boolean.FALSE;

}
