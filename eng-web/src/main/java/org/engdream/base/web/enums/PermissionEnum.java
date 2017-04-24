package org.engdream.base.web.enums;

/**
 * Created by heyx on 2017/4/23.
 */
public enum PermissionEnum {
    create("创建"),
    delete("删除"),
    update("更新"),
    view("查看"),
    audit("审核");
    private String info;
    PermissionEnum(String info){
        this.info = info;
    }

}
