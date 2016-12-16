package org.sun.spring.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FileUtils {
	public static final String FILEPATH = "Permanent_Data";

//	public static void main(String[] args) {
//		JSONObject jsontemp = new JSONObject();
//		jsontemp.put("access_token", "token2");
//		jsontemp.put("begin_time", System.currentTimeMillis());
////		JSONObject jsonAccess = new JSONObject();
////		jsonAccess.put("1234", jsontemp);
//
//		FileUtils.write2File(jsontemp, "accesstoken");
//
//		long l = 1479915220217L;
//		DateTime dateTime = new DateTime(l);
//		System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
//	}

	// json写入文件
	public synchronized static void write2File(Object json, String fileName) {
		BufferedWriter writer = null;
		File filePath = new File(FILEPATH);
		JSONObject eJSON = null;
		
		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdirs();
		}

		File file = new File(FILEPATH + File.separator + fileName + ".xml");
		System.out.println("path:" + file.getPath() + " abs path:" + file.getAbsolutePath());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.out.println("createNewFile，出现异常:");
				e.printStackTrace();
			}
		} else {
			eJSON = (JSONObject) read2JSON(fileName);
		}

		try {
			writer = new BufferedWriter(new FileWriter(file));

			if (eJSON==null) {
				writer.write(json.toString());
			} else {
				Object[] array = ((JSONObject) json).keySet().toArray();
				for(int i=0;i<array.length;i++){
					eJSON.put(array[i].toString(), ((JSONObject) json).get(array[i].toString()));
				}

				writer.write(eJSON.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 读文件到json
	public static JSONObject read2JSON(String fileName) {
		File file = new File(FILEPATH + File.separator + fileName + ".xml");
		if (!file.exists()) {
			return null;
		}

		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (JSONObject) JSON.parse(laststr);
	}

	// 通过key值获取文件中的value
	public static Object getValue(String fileName, String key) {
		JSONObject eJSON = null;
		eJSON = (JSONObject) read2JSON(fileName);
		if (null != eJSON && eJSON.containsKey(key)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> values = JSON.parseObject(eJSON.toString(), Map.class);
			return values.get(key);
		} else {
			return null;
		}
	}


}
