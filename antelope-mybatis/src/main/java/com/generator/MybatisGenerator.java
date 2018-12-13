package com.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 自动生成数据库映射文件
 *
 * @author yangmeiliang
 * @date 2018/5/10
 */
public class MybatisGenerator {

    public static void generator() {
        generator(null);
    }

    public static void generator(String configName) {
        try {
            if (Objects.isNull(configName)) {
                configName = "mybatis-generator-config.xml";
            }
            String resourcePath = System.getProperty("user.dir") + "/antelope-mybatis/gen/src/main/resources";
            String javaPath = System.getProperty("user.dir") + "/antelope-mybatis/gen/src/main/java";

            File directoryResource = new File(resourcePath);
            File directoryJava = new File(javaPath);

            boolean delete = deleteDir(javaPath);
            System.out.println("delete java dir: " + delete);
            delete = deleteDir(resourcePath);
            System.out.println("delete resources dir: " + delete);

            boolean mkdir = directoryResource.mkdirs();
            System.out.println("create resource dir:" + mkdir);
            mkdir = directoryJava.mkdirs();
            System.out.println("create java dir:" + mkdir);

            List<String> warnings = new ArrayList<>();
            URL url = MybatisGenerator.class.getClassLoader().getResource(configName);
            if (Objects.isNull(url)) {
                System.err.println(configName + " not exists");
                return;
            }
            String configFilePath = url.getPath();
            System.out.println("加载配置文件===" + configFilePath);
            File configFile = new File(configFilePath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);

            warnings.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        String[] content = file.list();
        Optional.ofNullable(content).map(Arrays::stream).ifPresent(list -> list.forEach(name -> {
            File temp = new File(path, name);
            if (temp.isDirectory()) {
                deleteDir(temp.getAbsolutePath());
            }
            System.out.println(String.format("file [%s] delete: %s", temp.getPath(), temp.delete()));
        }));
        return true;
    }
}
