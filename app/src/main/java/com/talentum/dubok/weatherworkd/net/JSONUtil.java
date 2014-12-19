package com.talentum.dubok.weatherworkd.net;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONUtil {
	/**
	 * Returns a JSONObject from a string containing a GET request
	 * @param httpRequest
	 * @return
	 */
	public static JSONObject getJSONFromHttpRequest(String httpRequest) {
		BufferedReader in = null;
		JSONObject jsonObject = null;
		try {
		    
			HttpClient client = new DefaultHttpClient();
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 20000);
			HttpConnectionParams.setSoTimeout(params, 20000);
			HttpGet request = new HttpGet(httpRequest);
			HttpResponse response = client.execute(request);

			in = new BufferedReader(
					new InputStreamReader(
							response.getEntity().getContent(), "UTF-8"));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			String page = sb.toString();
			// System.out.println(page);

			jsonObject = new JSONObject(page);
		} catch (org.apache.http.conn.HttpHostConnectException e) {
			Log.e("ERROR", e.getMessage());
			Log.e("ERROR", "Error connecting to: " + httpRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}
}