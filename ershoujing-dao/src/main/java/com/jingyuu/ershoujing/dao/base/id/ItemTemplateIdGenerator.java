package com.jingyuu.ershoujing.dao.base.id;


/**
 * @author owen
 * @date 2017-09-11
 */
public class ItemTemplateIdGenerator extends AbstractIdGenerator {
    @Override
    protected String segmentValue() {
        return "PK_ITEM_ID";
    }

    @Override
    protected String prefix() {
        return "I";
    }

    @Override
    protected String dateStr() {
        return "";
    }
}
