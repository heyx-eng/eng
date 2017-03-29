/**
 * 代码生成
 *
 */
package org.eng.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eng.codegen.generator.AutoGenerator;
import org.eng.codegen.generator.InjectionConfig;
import org.eng.codegen.generator.config.DataSourceConfig;
import org.eng.codegen.generator.config.FileOutConfig;
import org.eng.codegen.generator.config.GlobalConfig;
import org.eng.codegen.generator.config.PackageConfig;
import org.eng.codegen.generator.config.StrategyConfig;
import org.eng.codegen.generator.config.po.TableInfo;
import org.eng.codegen.generator.config.rules.DbType;
import org.eng.codegen.generator.config.rules.NamingStrategy;

public class App {

    public static void main(String[] args) {
    	String[] includeTables = new String[]{"sys_user"};
        String[] tablePrefix = new String[]{"sys_"};
        String moduleName = "sys";
        
        AutoGenerator mpg = new AutoGenerator();
        String baseOutputPath = App.class.getClassLoader().getResource("").getPath();
        String baseWebPath = baseOutputPath.replace("eng-codegen", "eng-web").
				replace("/target/classes/", "/src/main/webapp/WEB-INF/jsp/");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        
        gc.setEntityOutputDir(baseOutputPath.replace("eng-codegen", "eng-dao").replace("/target/classes/", "/src/main/java"));
        gc.setMapperJavaOutputDir(baseOutputPath.replace("eng-codegen", "eng-dao").replace("/target/classes/", "/src/main/java"));
        gc.setMapperXmlOutputDir(baseOutputPath.replace("eng-codegen", "eng-dao").replace("/target/classes/", "/src/main/resources/mapper"));
        gc.setServiceOutputDir(baseOutputPath.replace("eng-codegen", "eng-service").replace("/target/classes/", "/src/main/java"));
        gc.setControllerOutputDir(baseOutputPath.replace("eng-codegen", "eng-web").replace("/target/classes/", "/src/main/java"));

        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("Heyx");
        gc.setFileOverride(true);
        gc.setOpen(false);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        //自定义controller文件输出目录，不用默认的controller
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/eng?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //之前插件是默认全匹配，改为左前缀匹配
		strategy.setInclude(includeTables);
		strategy.setTablePrefix(tablePrefix);
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 自定义实体父类
        strategy.setSuperEntityClass("org.engdream.common.base.BaseEntity");
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(new String[] { "id" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass("org.engdream.common.base.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("org.engdream.common.base.impl.BaseServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("org.engdream.common.base.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        //strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.engdream");
		pc.setModuleName(moduleName);
        mpg.setPackageInfo(pc);
        

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                this.setMap(map);
            }
        };
        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        
        /*focList.add(new FileOutConfig("/templates/edit.jsp.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				//生成到eng-web模块下 /src/main/webapp/WEB-INF/jsp/模块名/实体名/edit.jsp
				return baseWebPath;
			}
        });*/
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        /*System.err.println(mpg.getCfg().getMap().get("abc"));*/
    }

}

