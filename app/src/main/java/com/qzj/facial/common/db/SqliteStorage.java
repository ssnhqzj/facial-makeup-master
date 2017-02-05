package com.qzj.facial.common.db;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.qzj.facial.bean.TableMap;
import com.qzj.facial.common.db.dao.TableMapDao;
import com.orhanobut.hawk.Storage;

public class SqliteStorage implements Storage {

    private TableMapDao tableMapDao;

    public SqliteStorage(Context context){
        tableMapDao = new TableMapDao(context);
    }

    @Override
    public <T> boolean put(String key, T value) {
        return tableMapDao != null && tableMapDao.add(new TableMap(key, JSON.toJSONString(value)));
    }

    @Override
    public <T> T get(String key) {
        if (tableMapDao!= null && tableMapDao.get(key) != null) {
            return (T) JSON.parse(tableMapDao.get(key));
        }

        return null;
    }

    @Override
    public boolean delete(String key) {
        return tableMapDao != null && tableMapDao.delete(key);
    }

    @Override
    public boolean deleteAll() {
        return tableMapDao != null && tableMapDao.clearTable();
    }

    @Override
    public long count() {
        if (tableMapDao != null) {
            return tableMapDao.count();
        }

        return 0;
    }

    @Override
    public boolean contains(String key) {
        return tableMapDao != null && tableMapDao.contain(key);
    }
}
