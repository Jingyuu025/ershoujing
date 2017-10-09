package com.jingyuu.ershoujing.dao.jpa.entity.user;

import com.jingyuu.ershoujing.dao.base.BaseCustomIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员
 *
 * @author owen
 * @date 2017-09-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jyu_people")
public class PeopleEntity extends BaseCustomIdEntity {
    @Id
    private String userId;

    @Column(name = "user_name", columnDefinition = "VARCHAR(32) COMMENT '用户名称'")
    private String userName;

    @Column(name = "id_type", columnDefinition = "SMALLINT(6) COMMENT '证件类型 1：身份证 2：营业执照'")
    private Integer idType;

    @Column(name = "id_code", columnDefinition = "VARCHAR(32) COMMENT '证件编码'")
    private String idCode;

    @Column(name = "id_picture", columnDefinition = "VARCHAR(64) COMMENT '证件图片'")
    private String idPicture;

    @Column(name = "state", columnDefinition = "SMALLINT(6) COMMENT '认证状态 1：未认证 2：已认证'")
    private Integer state;
}
