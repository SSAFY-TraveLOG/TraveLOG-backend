package com.ssafy.travelog.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class LikeServiceImpl implements LikeService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public LikeServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List likeArticle(Map<String, String> map) {
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                String key = "like:article:" + map.get("articleNo");
                operations.opsForSet().add((K) key, (V) map.get("userNo"));
                key = "like:user_article:" + map.get("userNo");
                operations.opsForSet().add((K) key, (V) map.get("articleNo"));
                return operations.exec();
            }
        });
        return execute;
    }

    @Override
    public List likeAttraction(Map<String, String> map) {
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                String key = "like:attraction:" + map.get("attractionNo");
                operations.opsForSet().add((K) key, (V) map.get("userNo"));
                key = "like:user_attraction:" + map.get("userNo");
                operations.opsForSet().add((K) key, (V) map.get("attractionNo"));
                return operations.exec();
            }
        });
        return execute;
    }

    @Override
    public Long getAttractionLikeNum(String attractionNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.size("like:attraction:" + attractionNo);
    }

    @Override
    public Set getLikeAttractionList(String userNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.members("like:user_attraction:" + userNo);
    }

    @Override
    public Set getLikeBoardList(String userNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.members("like:user_article:" + userNo);
    }

    @Override
    public List hateArticle(Map<String, String> map) {
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                String key = "like:article:" + map.get("articleNo");
                operations.opsForSet().remove((K) key, (V) map.get("userNo"));
                key = "like:user_article:" + map.get("userNo");
                operations.opsForSet().remove((K) key, (V) map.get("articleNo"));
                return operations.exec();
            }
        });
        return execute;
    }

    @Override
    public Long getArticleLikeNum(String articleNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.size("like:article:" + articleNo);
    }
}
