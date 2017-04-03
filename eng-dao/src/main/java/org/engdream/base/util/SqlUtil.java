package org.engdream.base.util;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.engdream.base.util.entity.SearchCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlUtil {
	
	public static void fillWrapper(Page<?> page, Wrapper<?> wrapper) {
		if (null == page) {
			return;
		}
		if(null != wrapper)
        {
            wrapper.orderBy(page.getOrderByField(), page.isAsc());
            List<SearchCondition> conditions = extraParam(page.getCondition());
            for(SearchCondition condition : conditions){
            	switch(condition.getOtp()){
            	case eq:
            		wrapper.eq(condition.getKey(), condition.getValue());
            		break;
            	case ne:
            		wrapper.ne(condition.getKey(), condition.getValue());
            		break;
            	case gt:
            		wrapper.gt(condition.getKey(), condition.getValue());
            		break;
            	case ge:
            		wrapper.ge(condition.getKey(), condition.getValue());
            		break;
            	case lt:
            		wrapper.lt(condition.getKey(), condition.getValue());
            		break;
            	case le:
            		wrapper.le(condition.getKey(), condition.getValue());
            		break;
            	case like:
            		wrapper.like(condition.getKey(),condition.getValue().toString());
            		break;
            	case likeL:
            		wrapper.like(condition.getKey(), condition.getValue().toString(), SqlLike.LEFT);
            		break;
            	case likeR:
            		wrapper.like(condition.getKey(), condition.getValue().toString(), SqlLike.RIGHT);
            		break;
            	}
            	
            }
        }
	}
	/**
	 * 获取查询条件 格式如： username_like=value
	 * @param conditionMap
	 * @return
	 */
	private static List<SearchCondition> extraParam(Map<String, Object> conditionMap) {
		List<SearchCondition> conditions = new ArrayList<>();
		for(Map.Entry<String, Object> entry : conditionMap.entrySet()){
			String key = entry.getKey();
			SearchCondition condition;
			if(key.contains("_")){
				condition = new SearchCondition(key.split("_")[0], entry.getValue(), key.split("_")[1]);
			}else{
				condition = new SearchCondition(key, entry.getValue());
			}
			conditions.add(condition);
		}
		return conditions;
	}
}
