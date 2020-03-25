package com.local;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;

/**
 * @author lou ke
 * @version 1.0
 * @date 2020/3/25 22:38
 */
//代码自动生成器
public class InitCode {
    public static void main(String[] args) {
        // 1.需要构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        // 配置策略
        // 1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("louKe");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false); //是否覆盖
        globalConfig.setServiceName("%sService");   //去service前缀
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);

        autoGenerator.setGlobalConfig(globalConfig);

        // 2、设置数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("19960923");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);

        autoGenerator.setDataSource(dataSourceConfig);

        // 3、包的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("blog");
        packageConfig.setParent("com.local");
        packageConfig.setEntity("model");   // 实体类
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");

        autoGenerator.setPackageInfo(packageConfig);


        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("person");   //设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);   //自动生成 lombok
        strategy.setLogicDeleteFieldName("deleted");  // 逻辑删除的字段名

        // 自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);

        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);

        // 乐观锁配置
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);

        autoGenerator.setStrategy(strategy);


        // 执行
        autoGenerator.execute();
    }
}
