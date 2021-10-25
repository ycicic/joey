package com.zhongshi.joey.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类查找工具类
 *
 * @author ycc
 */
public class ClassFinder {

    private static final ClassLoader CLASSLOADER = Thread.currentThread().getContextClassLoader();

    /**
     * 获取同一路径下所有子类或接口实现类
     *
     * @param clazz 父类/接口的class
     * @return 同一路径下所有子类或接口实现类
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> clazz) {
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : getClasses(clazz)) {
            if (clazz.isAssignableFrom(c) && !clazz.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * 取得当前类路径下的所有类
     *
     * @param clazz 父类/接口的class
     * @return 当前类路径下的所有类
     */
    public static List<Class<?>> getClasses(Class<?> clazz) {
        String pk = clazz.getPackage().getName();
        String path = pk.replace('.', '/');
        try {
            String dirPath = URLDecoder.decode(Objects.requireNonNull(CLASSLOADER.getResource(path)).getPath(), "utf-8");
            return getClasses(new File(dirPath), pk);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ArrayList<Class<?>>();
    }

    /**
     * 迭代查找类
     *
     * @param dir 路径
     * @param pk  包名
     * @return 查找到的类
     */
    private static List<Class<?>> getClasses(File dir, String pk) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!dir.exists()) {
            return classes;
        }
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.isDirectory()) {
                classes.addAll(getClasses(f, pk + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                try {
                    classes.add(Class.forName(pk + "." + name.substring(0, name.length() - 6)));
                } catch (Exception ignored) {
                }
            }
        }
        return classes;
    }
}
