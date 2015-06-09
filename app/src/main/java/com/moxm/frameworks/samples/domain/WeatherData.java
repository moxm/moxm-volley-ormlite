package com.moxm.frameworks.samples.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * 系统联系人
 * 
 * @author richard.ma
 * 
 */
@DatabaseTable(tableName = "weather_data")
public class WeatherData implements Serializable {

	/*
	{
		"error":0,
		"status":"success",
		"date":"2015-06-09",
		"results":
		[
			{
				"currentCity":"绥德",
				"pm25":"",
				"index":[
					{"title":"穿衣","zs":"热","tipt":"穿衣指数","des":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},
					{"title":"洗车","zs":"不宜","tipt":"洗车指数","des":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},
					{"title":"旅游","zs":"适宜","tipt":"旅游指数","des":"天气较好，感觉有些热，总体来说还是好天气哦。适宜旅游，可不要错过机会呦"},
					{"title":"感冒","zs":"少发","tipt":"感冒指数","des":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},
					{"title":"运动","zs":"较适宜","tipt":"运动指数","des":"阴天，较适宜进行各种户内外运动。"},
					{"title":"紫外线强度","zs":"弱","tipt":"紫外线强度指数","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
				],
				"weather_data":[
					{"date":"周二 06月09日 (实时：33℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"阴转小雨","wind":"微风","temperature":"31 ~ 16℃"},
					{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/xiaoyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"小雨转晴","wind":"微风","temperature":"26 ~ 11℃"},
					{"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"微风","temperature":"27 ~ 13℃"},
					{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/qing.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/qing.png","weather":"晴","wind":"微风","temperature":"29 ~ 14℃"}
				]
			}
		]
	}
	*/

	/**
	 * 
	 */
	private static final long serialVersionUID = -6350996935665630539L;


	@DatabaseField(generatedId = true, columnName = "_id")
	private Long _id;
	
	/** ID */
	@DatabaseField(columnName = "id")
	private String id;

	@DatabaseField(columnName = "date")
	private String date;

	@DatabaseField(columnName = "dayPictureUrl")
	private String dayPictureUrl;

	@DatabaseField(columnName = "nightPictureUrl")
	private String nightPictureUrl;

	@DatabaseField(columnName = "weather")
	private String weather;

	@DatabaseField(columnName = "wind")
	private String wind;

	@DatabaseField(columnName = "temperature")
	private String temperature;


	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayPictureUrl() {
		return dayPictureUrl;
	}

	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}

	public String getNightPictureUrl() {
		return nightPictureUrl;
	}

	public void setNightPictureUrl(String nightPictureUrl) {
		this.nightPictureUrl = nightPictureUrl;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}
