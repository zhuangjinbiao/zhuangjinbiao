package day1129;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Json {
	public static ArrayList<Weather> list ;
	public String getJson(String url)  {
		if(url==null||url.length()==0) {
			return null;
		}
//		try {
//			url = URLEncoder.encode(url, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			System.out.println("URL编码出现问题");
//		}
		URL u =null;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream in=null;
		try {
			in = u.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader is =null;
		try {
			is = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br  = new BufferedReader(is);
		
		StringBuilder sb =  new StringBuilder();
		String line  = "";
		try {
			while(null!=(line=(br.readLine()))) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public ArrayList<Weather> GetWeather(String content){
		ArrayList<Weather> list  =  new ArrayList<>();
		if (content==null) {
			return null;
		}
		JSONObject jso = JSONObject.parseObject(content);
		System.out.println("json"+jso);
		
		System.out.println("----------");
		JSONArray array = jso.getJSONArray("result");
		System.out.println(array);
		for (int i = 0; i < array.size(); i++) {
			Weather w = JSONObject.toJavaObject(array.getJSONArray(i), Weather.class);
			list.add(w);
		}
		return list;
	}
	public static void main(String[] args) throws IOException {
		JSONObject jso =  new JSONObject();
		Json j1 =  new Json();
		String st = j1.getJson("http://api.k780.com/?app=weather.future&weaid=36&appkey=12897&sign=ad041abb874869a9764b5891234459b3&format=json");
		list = j1.GetWeather(st);
		for (Weather js : list) {
			System.out.println(js);
		}
	}
}
