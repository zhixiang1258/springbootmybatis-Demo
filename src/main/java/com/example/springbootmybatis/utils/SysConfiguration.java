package com.example.springbootmybatis.utils;

import javafx.beans.property.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class SysConfiguration {
    //读取配置文件目录
    private static final String[] PROPERTIES_FILE = new String[]{"config/application.properties","config/application-develop.properties","config/application-settings.properties"};
    //加载日志文件
    private static final Logger LOGGER = LoggerFactory.getLogger(SysConfiguration.class);
    //定义property对象
    private static Properties properties = null;
    //构造函数

    public SysConfiguration() {
    }

    //静态方法，在对象加载的时候加载，且加载一次
    static {
        init();
    }

    private static void init(){
        for(int i = 0;i<PROPERTIES_FILE.length;i++){
            //读取系统配置文件流
            try {
                InputStream inputStream = new FileInputStream(PROPERTIES_FILE[i]);
                if (inputStream!=null){
                    if (properties==null){
                        properties = new Properties();
                    }
                    properties.load(inputStream);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LOGGER.info(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭流
     */
    private static void closeStream(InputStream inputStream,String log){
        try {
            if (inputStream!=null){
                inputStream.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("close"+log+"error");
        }
    }

    /**
     * 根据传入的key获取相应的值
     */
    public static String getString(String key){
        if(properties!=null){
            return properties.getProperty(key);
        }
        return "";
    }

    /**
     * 获得配置属性
     */
    public static Integer getInteger(String key){
        if (properties!=null){
            return Integer.valueOf(properties.getProperty(key));
        }
        return null;
    }

    /**
     * 根据路径读取配置文件
     */
    public static Properties getProperties(String path){
        Properties properties = new Properties();
        try {
            InputStream  is = new FileInputStream(path);
            properties.load(is);
            if(is!=null){
                is.close();
            }
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.info("getProperties...FileNotFoundException...error");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("getProperties...IOException...error");
            return null;
        }
    }
}
