package org.sun.spring.gson;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by tianfei on 16/10/21.
 */
public class Category {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private List<Category> children;
    // 不需要序列化,所以不加 @Expose
    // 等于 @Expose(serialize = false,deserialize = false)
    private Category parent;

    public Category(int id, String name, List<Category> children, Category parent) {
        this.id = id;
        this.name = name;
        this.children = children;
        this.parent = parent;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
