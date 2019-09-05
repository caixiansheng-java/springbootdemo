package com.hyx.demo.sdk.utils;  

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/** 
 * @ClassName:RedisUtils <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 上午11:29:52 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class RedisUtils {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private static final TimeUnit DEFAULT_EXPIRE_UNIT = TimeUnit.MINUTES;

    private static final String prefixKey = "hyx:key:lock:";

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    // =============================common============================
    /**
     * 指定缓存失效时间
     * 
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * 
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * 
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 删除缓存
     * 
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 根据表达式返回key集合（慎用，效率较低）
     * 
     * @param pattern 表达式
     * @return
     * @author ducongcong
     * @createDate 2018年2月1日
     * @updateDate
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 批量删除
     * 
     * @param keys
     * @author ducongcong
     * @createDate 2018年2月1日
     * @updateDate
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 模糊批量删除key
     * 
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    // ============================String=============================
    /**
     * 普通缓存获取
     * 
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * 
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }

    }

    /**
     * 用于分布式锁，新的value替代老value getset
     * 
     * @return
     */

    public <T> T getSet(String key, T value) {
        return key == null ? null : (T) redisTemplate.opsForValue().getAndSet(key, value);
    }

    private RedisSerializer<String> getSerializer() {
        return redisTemplate.getStringSerializer();
    }

    /**
     * 字符串类型:根据key设置value值,如果key中的value存在,那么返回false
     * 
     * @param key
     * @param value
     * @return
     */
    public Boolean setnx(final String key, final String value) {
        Boolean flag = false;
        RedisSerializer<String> serializer = getSerializer();
        byte keys[] = serializer.serialize(key);
        byte values[] = serializer.serialize(value);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        try {
            flag = connection.setNX(keys, values);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return flag;
    }

    /**
     * 字符串类型:设置key和value的超时时间(设置成String返回类型,不然要设置成Void)
     * 
     * @param key
     * @param timeout
     * @param value
     * @return
     */
    public void setex(final String key, final Long timeout, final String value) {
        RedisSerializer<String> serializer = getSerializer();
        byte keys[] = serializer.serialize(key);
        byte values[] = serializer.serialize(value);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        try {
            connection.setEx(keys, timeout, values);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 普通缓存放入并设置时间
     * 
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 递增 1
     * 
     * @param key 键
     * @return
     */
    public long incr(String key) {
        return incr(key, 1L);
    }

    /**
     * 递增
     * 
     * @param key 键
     * @param by 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * 
     * @param key 键
     * @return
     */
    public long decr(String key) {
        return decr(key, 1L);
    }

    /**
     * 递减
     * 
     * @param key 键
     * @param by 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 递增
     * 
     * @param key 键
     * @param by 要增加几(大于0)
     * @return
     */
    public double incr(String key, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * 
     * @param key 键
     * @param by 要减少几(小于0)
     * @return
     */
    public double decr(String key, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================
    /**
     * HashGet
     * 
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * 
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * 
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * 
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * 
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * 
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * 
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * 
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * 
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * 
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     * 
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            logger.error("is error:", e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * 
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * 
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * 
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * 
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     * 
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }
    // ===============================list=================================

    /**
     * 获取list缓存的内容
     * 
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            logger.error("is error:", e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * 
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * 
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            logger.error("is error:", e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     * 
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * 
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * 
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * 
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * 
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            logger.error("is error:", e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     * 
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            logger.error("is error:", e);
            return 0;
        }
    }

    public String client(String url, HttpMethod method, MultiValueMap<String, String> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 大部分的情况下，提交方式都是表单提交,需要别的提交方式可以自己扩展
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        // 执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }

    /**
     * 获取分布式锁（60秒超时）
     *
     * @param businessKey 锁
     * @param value 请求标识
     * @return 是否获取成功
     */
    public boolean getDistributedLock(String businessKey, String value) {
        String lockKey = prefixKey + businessKey;
        return setKeyIfAbsent(lockKey, value, 60, TimeUnit.SECONDS);
    }

    /**
     * set 如果key不存在就设置key值，否则返回false
     * 
     * @param key 键
     * @param value 值
     * @param expireTime 超时时间
     * @param unit 超时时间单位
     * @return
     */
    public boolean setKeyIfAbsent(String key, String value, long expireTime, TimeUnit unit) {
        if (unit == null) {
            unit = DEFAULT_EXPIRE_UNIT;
        }
        Boolean flag = false;
        RedisSerializer<String> serializer = getSerializer();
        byte keys[] = serializer.serialize(key);
        byte values[] = serializer.serialize(value);
        RedisConnection connection = null;
        try {
            connection = redisTemplate.getConnectionFactory().getConnection();
            flag = connection.set(keys, values, Expiration.from(expireTime, unit), SetOption.SET_IF_ABSENT);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return flag;
    }

    /**
     * 解除分布式锁
     * 
     * @param key 键
     * @param value 值
     */
    public void unlockDistributedLock(String key, String value) {
        try {
            String myKey = prefixKey + key;
            String currentValue = (String) get(myKey);
            if (StringUtils.isNotBlank(currentValue)) {
                if (!StringUtils.isNotBlank(value) && value.equals(currentValue)) {
                    redisTemplate.opsForValue().getOperations().delete(myKey);
                    logger.info("解锁完毕===" + key);
                }
            }
        } catch (Exception ex) {
            logger.error("解锁异常,{}", ex);
        }
    }

    /**
     * redis key按天累计增加1
     * 
     * @param key
     */
    public void incByDay(String key) {
        RedisSerializer<String> serializer = getSerializer();
        byte keys[] = serializer.serialize(key);
        RedisConnection connection = null;
        try {
            connection = redisTemplate.getConnectionFactory().getConnection();
            connection.incrBy(keys, 1);
            connection.expire(keys, DateTimeUtils.getRemainSecondsOneDay(new Date()));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
 