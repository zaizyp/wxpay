package com.ping.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.vo.WXMessage;

/**
 * 淘淘商城自定义响应结构
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    /**
     * 定义微信模版消息需要的json字符串
     * @Title: getJson 
     * @Description: 
     * @param message 微信模版消息
     * @return
     */
    public static String getTemplateJson(WXMessage message){
		if(!(message.getMessage().length==message.getColor().length&&message.getColor().length>=3))
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"touser\":\"");
		sb.append(message.getToUser());
		sb.append("\",\"template_id\":\"");
		sb.append(message.getTemplateId());
		if(message.getUrl()!=null){
			sb.append("\",\"url\":\"");
			sb.append(message.getUrl());
		}
		if(message.getAppId()!=null){
			sb.append("\",\"miniprogram\":{\"appid\":\"");
			sb.append(message.getAppId());
			if(message.getPagePath()!=null){
				sb.append("\",\"pagepath\":\"");
				sb.append(message.getPagePath());
			}
			sb.append("\"}");
		}else{
			sb.append("\"");
		}
		sb.append(",\"data\":{\"first\": {\"value\":\"");
		sb.append(message.getMessage()[0]);
		sb.append("\",\"color\":\"");
		sb.append(message.getColor()[0]);
		sb.append("\"},");
		for(int i=1;i<(message.getMessage().length-1);i++){
			sb.append("\"keyword");
			sb.append(i);
			sb.append("\":{\"value\":\"");
			sb.append(message.getMessage()[i]);
			sb.append("\",\"color\":\"");
			sb.append(message.getColor()[i]);
			sb.append("\"},");
		}
		sb.append("\"remark\":{\"value\":\"");
		sb.append(message.getMessage()[message.getMessage().length-1]);
		sb.append("\",\"color\":\"");
		sb.append(message.getColor()[message.getMessage().length-1]);
		sb.append("\"}}}");
		return sb.toString();
	}
    
}
