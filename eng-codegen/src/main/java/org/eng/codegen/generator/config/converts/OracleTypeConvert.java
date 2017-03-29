/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eng.codegen.generator.config.converts;

import org.eng.codegen.generator.config.ITypeConvert;
import org.eng.codegen.generator.config.rules.DbColumnType;

/**
 * <p>
 * ORACLE 字段类型转换
 * </p>
 *
 * @author hubin
 * @date 2017-01-20
 */
public class OracleTypeConvert implements ITypeConvert {

	public DbColumnType processTypeConvert(String fieldType) {
		String t = fieldType.toUpperCase();
		if (t.contains("CHAR")) {
			return DbColumnType.STRING;
		} else if (t.contains("DATE") || t.contains("TIMESTAMP")) {
			return DbColumnType.DATE;
		} else if (t.contains("NUMBER")) {
			if (t.matches("NUMBER\\(+\\d{1}+\\)")) {
				return DbColumnType.INTEGER;
			} else if (t.matches("NUMBER\\(+\\d{2}+\\)")) {
				return DbColumnType.LONG;
			}
			return DbColumnType.DOUBLE;
		} else if (t.contains("FLOAT")) {
			return DbColumnType.FLOAT;
		} else if (t.contains("BLOB")) {
			return DbColumnType.OBJECT;
		} else if (t.contains("RAW")) {
			return DbColumnType.BYTE_ARRAY;
		}
		return DbColumnType.STRING;
	}

}
