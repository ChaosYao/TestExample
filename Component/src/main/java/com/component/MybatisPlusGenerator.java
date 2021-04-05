package com.component;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MybatisPlusGenerator {
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ": ");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt == null) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "! ");
    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        String[] tables = {"xxx"};

        GlobalConfig globalConfig = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir") ;
        globalConfig.setOutputDir(projectPath );
        globalConfig.setAuthor("yao");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);

        mpg.setGlobalConfig(globalConfig);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("xxxx?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&autoReconnect=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("xxx");
        dsc.setPassword("xxx");
        mpg.setDataSource(dsc);

        final PackageConfig pc = new PackageConfig();
        pc.setParent("src.main.java.dao");
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                ;
            }
        };

        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return "projectPath" + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
//         templateConfig.setEntity();
//         templateConfig.setService();
//         templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tables);//scanner("表名")
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
