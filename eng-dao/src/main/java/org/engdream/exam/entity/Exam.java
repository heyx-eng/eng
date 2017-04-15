package org.engdream.exam.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.engdream.base.entity.BaseEntity;
/**
 * <p>
 * 试卷
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@TableName("tb_exam")
public class Exam extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:试卷类型;type:select;valid:required
     */
	private String type;
    /**
     * label:试卷名称;type:input;valid:required;
     */
	private String name;

	public String getType() {
        return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
        return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
