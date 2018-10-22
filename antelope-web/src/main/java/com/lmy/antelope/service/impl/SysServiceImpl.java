package com.lmy.antelope.service.impl;

import com.lmy.antelope.domain.entities.SysMenu;
import com.lmy.antelope.repository.SysMenuRepository;
import com.lmy.antelope.service.SysService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangmeiliang
 * @date 2017/12/20
 */
@Service
@AllArgsConstructor
public class SysServiceImpl implements SysService {

    private SysMenuRepository sysMenuRepository;

    @Override
    public Page<SysMenu> pageMenuList() {
        Pageable pageable = new PageRequest(0, 10);
        Specification<SysMenu> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("createTime").as(String.class), "2017-12-11"));
            predicates.forEach(criteriaQuery::where);
            return criteriaQuery.getRestriction();
        };

        return sysMenuRepository.findAll(specification, pageable);
    }
}
