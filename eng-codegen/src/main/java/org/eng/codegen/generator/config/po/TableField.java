/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eng.codegen.generator.config.po;

import org.eng.codegen.generator.config.StrategyConfig;
import org.eng.codegen.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.toolkit.StringUtils;

/**
 * <p>
 * 表字段信息
 * </p>
 *
 * @author YangHu
 * @since 2016-12-03
 */
public class TableField {
	private boolean convert;
	private boolean keyFlag;
	/** 主键是否为自增类型 */
	private boolean keyIdentityFlag;
	private String name;
	private String type;
	private String propertyName;
	private DbColumnType columnType;
	private String comment;
	
	/**
	 * 用于生成jsp
	 * 例如: label:用户名;type:input;valid:regexp|[a-zA-Z]{6,}
	 * 属性之间暂时以 ; 分隔，和正则表达式可能冲突,正则中禁用 ;,如需要，则手动添加验证
	 */
	private String label;
	/**
	 * 输入框类型 <input type=inputType>
	 * hidden
	 * input|password|select|checkbox|radio
	 * textarea|editor(百度编辑器)
	 * image(单图)|images(多图)
	 */
	private String inputType;
	/**
	 * 输入验证
	 * email|number|required|date|url
	 * regexp(格式： regexp|正则表达式)
	 * 
	 */
	private String valid;
	public boolean isConvert() {
		return convert;
	}

	public void setConvert(boolean convert) {
		this.convert = convert;
	}

	protected void setConvert(StrategyConfig strategyConfig) {
		if (strategyConfig.isCapitalModeNaming(name)) {
			this.convert = false;
		} else {
			// 转换字段
			if (StrategyConfig.DB_COLUMN_UNDERLINE) {
				// 包含大写处理
				if (StringUtils.containsUpperCase(name)) {
					this.convert = true;
				}
			} else if (!name.equals(propertyName)) {
				this.convert = true;
			}
		}
	}

	public boolean isKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(boolean keyFlag) {
		this.keyFlag = keyFlag;
	}

	public boolean isKeyIdentityFlag() {
		return keyIdentityFlag;
	}

	public void setKeyIdentityFlag(boolean keyIdentityFlag) {
		this.keyIdentityFlag = keyIdentityFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(StrategyConfig strategyConfig, String propertyName) {
		this.propertyName = propertyName;
		this.setConvert(strategyConfig);
	}

	public DbColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(DbColumnType columnType) {
		this.columnType = columnType;
	}

	public String getPropertyType() {
		if (null != columnType) {
			return columnType.getType();
		}
		return null;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCapitalName() {
		return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}
