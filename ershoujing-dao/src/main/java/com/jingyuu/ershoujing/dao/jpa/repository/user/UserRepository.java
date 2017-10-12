package com.jingyuu.ershoujing.dao.jpa.repository.user;

import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author owen
 * @date 2017-09-07
 */
@Repository
public interface UserRepository extends BaseRepository<UserEntity, String> {
    /**
     * 查询用户列表
     *
     * @param telephone 手机号
     * @return
     */
    List<UserEntity> findByTelephone(String telephone);
}
