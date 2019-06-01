package com.zrytech.framework.app.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * <pre>
 * 根据腾讯视频网页播放地址获取原视频地址
 * 返回的原视频播放地址带有访问密钥，密钥有有效期
 * </pre>
 */
@Slf4j
public class WlQQVideoUrlUtils {
	
	
	/**
	 * 获取视频原地址
	 * @param url
	 * @return
	 */
	public static String getRealUrl(String url) {
		String vid = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		String urlJson = getUrlJson(vid);
		String realUrl = parseRealUrl(urlJson);
		log.info("原视频地址：{}", realUrl);
		return realUrl;
	}
	
	
	/**
	 * 获取视频地址JSON
	 * @param vid
	 * @return
	 */
	private static String getUrlJson(String vid) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL("http://vv.video.qq.com/getinfo?vids=" + vid + "&platform=101001&charge=0&otype=json");
			URLConnection connection = realUrl.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.error("获取视频原地址时发生异常");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				log.error("获取视频原地址时发生异常");
			}
		}
		return result;
	}

	/**
	 * 从返回的JSON中截取视频有效的播放地址
	 * @param urlJson
	 * @return
	 */
	private static String parseRealUrl(String urlJson) {
		int fn = urlJson.indexOf("\"fn\"");
		int fs = urlJson.indexOf("\"fs\"", fn);
		String videoName = urlJson.substring(fn + 6, fs - 2);
		int fvkey = urlJson.indexOf("\"fvkey\"");
		int head = urlJson.indexOf("\"head\"", fvkey);
		String vkey = urlJson.substring(fvkey + 9, head - 2);
		int url = urlJson.indexOf("\"url\"");
		int vt = urlJson.indexOf("\"vt\"", url);
		String urlPre = urlJson.substring(url + 7, vt - 2);
		String videoUrl = urlPre + videoName + "?vkey=" + vkey;
		return videoUrl;
	}
	
}
