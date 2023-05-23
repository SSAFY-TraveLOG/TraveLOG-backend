package com.ssafy.travelog.like.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LikeService {
    List likeArticle(Map<String, String> map);

    List likeAttraction(Map<String, String> map);

    Long getAttractionLikeNum(String attractionNo);

    Set getLikeAttractionList(String userNo);
}