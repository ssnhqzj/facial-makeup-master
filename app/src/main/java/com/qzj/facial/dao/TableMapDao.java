package com.qzj.facial.dao;

import android.content.Context;

import com.qzj.facial.bean.TableMap;
import com.qzj.facial.common.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 *
 * Created by qzj on 2016/6/15.
 */
@SuppressWarnings("unchecked")
public class TableMapDao {

    private Context context;
    private Dao<TableMap,Integer> tableMapDao;

    public TableMapDao(Context context) {
        this.context = context;
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        tableMapDao = helper.getDao(TableMap.class);
    }

    /**
     * 清空表中数据
     * @return 返回删除数据条数，异常返回-1
     */
    public int clearTable() {
        int result;
        try {
            result = tableMapDao.executeRaw("delete from table_map");
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 添加
     */
    public void add(TableMap tableMap) {
        try {
            tableMapDao.createOrUpdate(tableMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用key获取value值
     */
    public String get(String key) {
        try {
            TableMap tableMap = new TableMap();
            tableMap.key = key;
            tableMap = tableMapDao.queryForSameId(tableMap);
            return tableMap==null?null:tableMap.value;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据key删除一条记录
     */
    public int delete(String key) {
        int result;
        try {
            result = tableMapDao.delete(new TableMap(key,null));
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }

        return result;
    }
}
