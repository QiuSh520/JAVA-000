package io.kimmking.springboot01.jdbc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 字典
 * dict
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DictDO implements Serializable {
    private static final long serialVersionUID = -6132355805501907841L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 字典类别
     */
    private String category;
    /**
     * 字典类别描述
     */
    private String categoryName;
    /**
     * 字段编码
     */
    private String code;
    /**
     * 字典值
     */
    private String valueDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }
}
