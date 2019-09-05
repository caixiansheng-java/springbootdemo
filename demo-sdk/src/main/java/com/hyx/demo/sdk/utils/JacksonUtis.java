package com.hyx.demo.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JacksonUtis
 *
 * @author huangyaxiong 2019年5月31日 下午5:06:22
 */
public class JacksonUtis {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtis.class);

    /**
     * Jackson的ObjectMapper对象
     */
    private static ObjectMapper objectMapper;

    /**
     * 禁止实例化
     */
    private JacksonUtis() {

    }

    /**
     * @param createNew 是否创建新的实例
     * @return
     * @author liaochangxun 2019年5月31日 下午5:10:33
     */
    public static ObjectMapper getObjectMapper(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        }
        return getObjectMapper();
    }

    /**
     * 获取ObjectMapper实例
     * 
     * @return ObjectMapper实例
     */
    private static ObjectMapper getObjectMapper() {
        if (objectMapper != null) {
            return objectMapper;
        }

        synchronized (JacksonUtis.class) {
            if (objectMapper != null) {
                return objectMapper;
            }
            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return objectMapper;
        }
    }

    /**
     * 将对象转换为Json字符串
     * 
     * @param object 要转换的对象
     * @return 转换后的Json字符串
     * @throws RuntimeException
     */
    public static String object2Json(Object object) {
        try {
            if (object == null) {
                return null;
            }
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("将对象[{}]转为Json字符串时发生异常", object, e);
            throw new RuntimeException("将对象转为Json字符串时发生异常", e);
        }
    }

    /**
     * 将Json字符串转换为指定类型的对象
     * 
     * @param json 要转换的Json字符串
     * @param clazz 指定的类型
     * @return 指定类型的对象
     * @throws RuntimeException
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("将Json字符串[{}]转换为[{}]类型对象时发生异常", json, clazz.getName(), e);
            throw new RuntimeException("将Json字符串转换为对象时发生异常", e);
        }
    }

    /**
     * 将Json字符串转换为自定义类型或复杂类型的对象
     * 
     * @param json 要转换的Json字符串
     * @param valueTypeRef 自定义类型或复杂类型
     * @return 给定类型的对象
     * @throws RuntimeException
     */
    public static <T> T json2Object(String json, TypeReference<T> valueTypeRef) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return getObjectMapper().readValue(json, valueTypeRef);
        } catch (Exception e) {
            LOGGER.error("将Json字符串[{}]转换为[{}]类型对象时发生异常", json, valueTypeRef, e);
            throw new RuntimeException("将Json字符串转换为对象时发生异常", e);
        }
    }

    /**
     * 将Json字符串转换为Map类型的对象
     * 
     * @param json 要转换的Json字符串
     * @return Map对象，key为String类型，value为Object类型
     * @throws RuntimeException
     */
    public static Map<String, Object> json2Map(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return json2Object(json, new TypeReference<Map<String, Object>>() {
        });
    }
}
