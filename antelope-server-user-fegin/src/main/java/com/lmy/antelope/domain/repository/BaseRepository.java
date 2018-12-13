package com.lmy.antelope.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author yangmeiliang
 * @date 2018/10/22
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
