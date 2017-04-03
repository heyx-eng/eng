package org.engdream.base.web.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.plugins.Page;

public class DataTable<T> {
    @JSONField(serialize=false)
    private Page<T> pageInfo;

    public DataTable(Page<T> pageInfo){
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return pageInfo.getRecords();
    }

    public long getITotalRecords() {
        return pageInfo.getTotal();
    }

    public long getITotalDisplayRecords() {
        return pageInfo.getTotal();
    }

}
