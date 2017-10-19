package com.jingyuu.ershoujing.dao.mybatis.mapper;

import com.jingyuu.ershoujing.dao.mybatis.bo.TagKeyQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.TagValueQueryBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagKeyVo;
import com.jingyuu.ershoujing.dao.mybatis.vo.TagValueVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    /**
     * 查询标签健列表
     *
     * @param tagKeyQueryBo 标签健查询Bo
     */
    List<TagKeyVo> listTagKey(TagKeyQueryBo tagKeyQueryBo);


    /**
     * 查询标签值列表
     *
     * @param tagValueQueryBo 标签值查询Bo
     * @return
     */
    List<TagValueVo> listTagValue(TagValueQueryBo tagValueQueryBo);

}