package com.ssafy.travelog.like.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface LikeRepository extends CrudRepository<HashSet<Integer>, String> {
}
