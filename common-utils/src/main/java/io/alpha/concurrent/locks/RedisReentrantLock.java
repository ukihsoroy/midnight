package io.alpha.concurrent.locks;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

/**
 * redis分布式锁，可重入锁
 * @author K.O
 */
public class RedisReentrantLock {

    //使用ThreadLocal记录锁
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    //jedis客户端
    private Jedis jedis;

    /**
     * 构造函数
     * @param jedis
     */
    public RedisReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 私有lock方法，这一步会阻塞线程
     * @param key redis key
     * @return 是否获取锁
     */
    private boolean _lock(String key) {
        //jedis新的api
        return jedis.set(key, "", SetParams.setParams().nx().ex(5)) != null;
    }

    /**
     * 释放锁
     * @param key redis key
     */
    private void _unlock(String key) {
        jedis.del(key);
    }

    /**
     * 获取当前的锁记录数据
     * @return
     */
    private Map<String, Integer> currentLockers() {
        //获取记录
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    /**
     * 开放的获取锁，封装了可重入锁的数记录
     * @param key
     * @return
     */
    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);

        //非第一次获取锁
        if (refCnt != null) {
            //层数+1, 直接返回
            refs.put(key, refCnt + 1);
            return true;
        }

        //如果成功获取锁
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }

        //记录锁的层数
        refs.put(key, 1);
        return true;
    }

    /**
     * 释放锁的封装，封装的状态流转
     * @param key
     * @return
     */
    public boolean unlock(String key) {
        //获取锁层数记录
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            return false;
        }

        //如果等于0, 释放锁
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

}
