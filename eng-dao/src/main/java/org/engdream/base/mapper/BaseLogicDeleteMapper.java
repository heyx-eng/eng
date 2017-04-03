package org.engdream.base.mapper;

import java.io.Serializable;

/**
 * Created by heyx on 2017/4/1.
 */
public interface BaseLogicDeleteMapper<ID extends Serializable> {
    void markDelete(ID id);
}
