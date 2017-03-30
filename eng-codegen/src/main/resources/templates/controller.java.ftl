package ${package.Controller};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ${superControllerClassPackage};
import ${package.Entity}.${entity};
/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Controller
@RequestMapping("${package.ModuleName}/${table.entityPath}")
public class ${table.controllerName} extends ${superControllerClass}<${entity}, Long> {
	@Override
	protected String getResourcePrefix() {
		return "${package.ModuleName}:${table.entityPath}";
	}
}
