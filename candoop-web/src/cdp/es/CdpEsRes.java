package cdp.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CdpEsRes {

	private static CdpEsRes instance;

	private CdpEsRes() {}

	public static CdpEsRes getInstance() {
		if (instance == null) {
			instance = new CdpEsRes();
		}
		return instance;
	}
	
	public String getEsGet(String urls, String method) {
		StringBuffer res = new StringBuffer();
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urls);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				res.append(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			// 오류의 경우 getErrorStream 으로 오류 스트림 전달.
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String errOutput;
				res = new StringBuffer();
				while ((errOutput = br.readLine()) != null) {
					res.append(errOutput);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return res.toString();
	}
	
	public String getEsPost(String urls, String data) {
		return getEsPost(urls, data, "POST");
	}
	public String getEsPost(String urls, String data, String method) {
		StringBuffer res = new StringBuffer();
		URL url = null;
		HttpURLConnection conn = null;
		try {

			url = new URL(urls);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String output;
			while ((output = br.readLine()) != null) {
				res.append(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			// 오류의 경우 getErrorStream 으로 오류 스트림 전달.
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				String errOutput;
				res = new StringBuffer();
				while ((errOutput = br.readLine()) != null) {
					res.append(errOutput);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return res.toString();
	}
	
}
