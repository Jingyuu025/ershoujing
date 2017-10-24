package com.jingyuu.ershoujing.dao.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author owen
 * @date 2017-10-09
 */
@NoRepositoryBean // spring不会去实例化该repository
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * 返回所有的列表数据
     */
    @Override
    List<T> findAll();

    /**
     * 返回所有的列表数据
     *
     * @param sort 排序
     */
    @Override
    List<T> findAll(Sort sort);

    /**
     * 返回符合条件的所有数据
     *
     * @param specification 查询条件
     * @return
     */
    List<T> findAll(Specification<T> specification);

    /**
     * 返回符合条件并带排序的所有数据
     *
     * @param specification 查询条件
     * @param sort          排序
     * @return
     */
    List<T> findAll(Specification<T> specification, Sort sort);
}
