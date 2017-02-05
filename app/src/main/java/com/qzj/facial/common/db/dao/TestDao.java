package com.qzj.facial.common.db.dao;

import android.content.Context;

import com.qzj.facial.common.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import junit.framework.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * test
 */
public class TestDao {
    public static final String TABLE_NAME = "hat_area";

    private Dao<Test,Integer> TestDao;
    private DatabaseHelper helper;

    public TestDao(Context context) {
        helper = DatabaseHelper.getHelper(context);
        TestDao = helper.getDao(Test.class);
    }

    /**
     * 清空表中数据
     * @return 返回删除数据条数，异常返回-1
     */
    public int clearTable(){
        int result;
        try {
            result = TestDao.executeRaw("delete from " + TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 添加一个城区
     */
    public void add(Test Test) {
        try {
            TestDao.create(Test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个城区列表
     */
    public void addAll(ArrayList<Test> list) {
        if (list != null){
            for (Test ha : list){
                //HatCity hatCity = new HatCity();
                //hatCity.setCityid(ha.getFather());
                //ha.setHatCity(hatCity);
                //TestDao.create(ha);
            }
        }

    }

    /**
     * 根据id查询城区及其对应城市
     */
    public Test getTestWithHatCity(int id) {
        Test Test = null;
        try {
            Test = TestDao.queryForId(id);
            //helper.getDao(HatCity.class).refresh(Test.getHatCity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Test;
    }

    /**
     * 根据id查询城区
     * @param id
     * @return
     */
    public Test get(int id) {
        Test Test = null;
        try {
            Test = TestDao.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Test;
    }

    /**
     * 根据城市id查询城区列表
     * @param cityId
     * @return
     */
    public List<Test> listByHatCityId(String cityId) {
        try {
            return TestDao.queryBuilder().where().eq("city_id", cityId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据城区id查询对应的省市区名称字符串
     * @param areaId
     * @return
     */
    public String queryJoinAddr(String areaId){
        if (areaId == null) return null;
        String result = "";
        try {
            GenericRawResults<String[]> rawResults = TestDao.queryRaw(
                    "SELECT p.province,c.city,a.area FROM hat_area a " +
                            "JOIN hat_city c on a.city_id=c.cityid " +
                            "JOIN hat_province p on c.province_id=p.provinceid " +
                            "WHERE a.areaid=" + areaId);
            if (rawResults != null){
                String[] arrs = rawResults.getFirstResult();
                if (arrs == null) return result;
                for (String arr : arrs){
                    result += arr;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据城区id查询对应的省市区名称
     * @param areaId
     * @return
     */
    public String[] queryPcr(String areaId){
        if (areaId == null) return null;
        String[] result = null;
        try {
            GenericRawResults<String[]> rawResults = TestDao.queryRaw(
                    "SELECT p.province,c.city,a.area FROM hat_area a " +
                            "JOIN hat_city c on a.city_id=c.cityid " +
                            "JOIN hat_province p on c.province_id=p.provinceid " +
                            "WHERE a.areaid=" + areaId);
            if (rawResults != null){
                String[] arrs = rawResults.getFirstResult();
                return arrs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
