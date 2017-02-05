package com.qzj.facial.common.db.dao;

import android.content.Context;

import com.qzj.facial.bean.TableMap;
import com.qzj.facial.common.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

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
    public boolean clearTable() {
        int result;
        try {
            result = tableMapDao.executeRaw("delete from table_map");
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result>=0;
    }

    /**
     * 数据条数
     */
    public long count(){
        long result;
        try {
            result = tableMapDao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
            result = 0;
        }

        return result;
    }

    /**
     * 是否有包含key的数据
     */
    public boolean contain(String key){
        QueryBuilder queryBuilder = tableMapDao.queryBuilder();
        queryBuilder.setCountOf(true);
        try {
            queryBuilder.setWhere(queryBuilder.where().eq("key", key));
            return tableMapDao.countOf(queryBuilder.prepare()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 添加
     */
    public boolean add(TableMap tableMap) {
        try {
            Dao.CreateOrUpdateStatus status = tableMapDao.createOrUpdate(tableMap);
            return status.isCreated() || status.isUpdated();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
    public boolean delete(String key) {
        int result;
        try {
            result = tableMapDao.delete(new TableMap(key,null));
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }

        return result>0;
    }
}
