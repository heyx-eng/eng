package org.engdream.sys.enums;

/**
 * Created by heyx on 2017/4/16.
 */
public enum ResourceType {
    menu("菜单"),button("按钮");
    private String info;
    ResourceType(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
