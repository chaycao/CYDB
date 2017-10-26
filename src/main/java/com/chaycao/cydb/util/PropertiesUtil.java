package com.chaycao.cydb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chaycao on 2017/10/26.
 * chaycao@163.com
 *
 * 配置文件工具
 */
public class PropertiesUtil {
    private Properties properties;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
    public PropertiesUtil() {
        properties = new Properties();
    }
    public PropertiesUtil(String filePath) {
        properties = new Properties();
        try{
            inputFile = new FileInputStream(filePath);
            properties.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex){
            System.out.println("读取" + filePath + "文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载" + filePath + "--->失败!");
            ex.printStackTrace();
        }
    }
    public String getValue(String key)
    {
        if(properties.containsKey(key)){
            String value = properties.getProperty(key);//得到某一属性的值
            return value;
        }
        else
            return "";
    }
    public String getValue(String fileName, String key)
    {
        try{
            String value = "";
            inputFile = new FileInputStream(fileName);
            properties.load(inputFile);
            inputFile.close();
            if(properties.containsKey(key)){
                value = properties.getProperty(key);
                return value;
            }else
                return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

    public void clear()
    {
        properties.clear();
    }

    public void setValue(String key, String value)
    {
        properties.setProperty(key, value);
    }

    public void saveFile(String fileName, String description)
    {
        try{
            outputFile = new FileOutputStream(fileName);
            properties.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
