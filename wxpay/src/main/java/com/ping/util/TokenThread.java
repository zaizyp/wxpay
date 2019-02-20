package com.ping.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ping.vo.AccessToken;

/**
 * <p>
 * Title: TokenThread
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 周运平
 * @date 2018年10月28日 下午10:22:00
 * @version 1.0
 */
public class TokenThread implements Runnable {
	private static Logger log = Logger.getLogger(TokenThread.class);
	// 第三方用户唯一凭证
	public static String appid = "";
	// 第三方用户唯一凭证密钥
	public static String appsecret = "";

	public void run() {
		AccessToken accessToken = AccessToken.getAccessToken();

		while (true) {
			try {
				// 1 请求微信接口获取access_token
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
						+ "&secret=" + appsecret;
				HttpClient client = new HttpClient(url);
				client.setHttps(true);
				client.get();
				String content = client.getContent();
				Map<String, String> map = JsonUtils.jsonToPojo(content, HashMap.class);
				if (map.get("access_token") != null) {
					accessToken.setToken(map.get("access_token"));
					accessToken.setExpiresIn(7200);
					log.info("获取access_token成功，有效时长{" + accessToken.getExpiresIn() + "}秒 token:{"
							+ accessToken.getToken() + "}");
					
					for(int i=0;i<3;i++){
						// 通过access_token获取jsapi_ticket
						String get_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
								+ AccessToken.getAccessToken().getToken() + "&type=jsapi";
						HttpClient client2 = new HttpClient(get_ticket_url);
						client2.setHttps(true);
						client2.get();
						String content2 = client2.getContent();
						Map<String, String> map2 = new HashMap<String, String>();
						map2 = JsonUtils.jsonToPojo(content2, Map.class);
						String jsapi_ticket = map2.get("ticket");
						if(jsapi_ticket!=null){
							accessToken.setJsapiTicket(jsapi_ticket);
							log.info("获取jsapi_ticket成功，有效时长{"+jsapi_ticket+"}秒 jsapi_ticket:{"
									+ accessToken.getJsapiTicket() + "}");
							break;
						}else{
							log.warn(content2);
							Thread.sleep(60 * 1000);
						}
					}
					// 休眠7000秒
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					log.warn(content);
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (ParseException | InterruptedException | IOException e) {

				log.error("{}", e);
			}
		}
	}
}
