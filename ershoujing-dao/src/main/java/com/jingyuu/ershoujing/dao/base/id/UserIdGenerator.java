package com.jingyuu.ershoujing.dao.base.id;


/**
 * @author owen
 * @date 2017-09-11
 */
public class UserIdGenerator extends AbstractIdGenerator {
    @Override
    protected String segmentValue() {
        return "PK_USER_ID";
    }

    @Override
    protected String prefix() {
        return "U";
    }

    @Override
    protected String dateStr() {
        return "";
    }
}
