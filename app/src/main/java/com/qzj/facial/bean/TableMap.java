package com.qzj.facial.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * Created by Administrator on 2016/6/15.
 */
@DatabaseTable(tableName = "table_map")
public class TableMap {

    public TableMap(){}

    public TableMap(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @DatabaseField(id = true)
    public String key;

    @DatabaseField
    public String value;

}
