
package com.moxm.frameworks.samples.manager;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.moxm.frameworks.samples.domain.WeatherData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends BaseManager {
	
	private final static String TAG = "DatabaseManager";
	
	protected Dao<WeatherData, Long> weatherDataDao;

    public DatabaseManager(Context context) {
    	super(context);
    }
    public Dao<WeatherData, Long> getWeatherDataDao() throws SQLException {
		if (weatherDataDao == null) {
			weatherDataDao = getDatabaseHelper().getDao(WeatherData.class);
		}
		return weatherDataDao;
	}

	public void saveWeatherDatas(List<WeatherData> items) {
		AndroidDatabaseConnection db = new AndroidDatabaseConnection(getDatabaseHelper().getWritableDatabase(), true);
		db.setAutoCommit(false);
		try {
			for(WeatherData item : items){
				//执行添加或修改记录操作
				getWeatherDataDao().create(item);
				Log.d(TAG, "++++++++saveWeatherDatas:" + item.getWeather());
			}
			db.commit(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<WeatherData> findWeatherDatas() {
		try {
			QueryBuilder<WeatherData, Long> queryBuilder = getWeatherDataDao().queryBuilder();
			queryBuilder.orderBy("date", true);
			return getWeatherDataDao().query(queryBuilder.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<WeatherData>();
	}

	public void deleteWeatherDatas() {
		try {
			getWeatherDataDao().delete(getWeatherDataDao().queryForAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
  
}
