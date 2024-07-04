package com.sparta.heartvera.domain.follow.repository;

import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepositoryCustom {

    Page<PublicPost> getFollowerPostsByUserId(Long followerUserId, int page, int size, String sortBy);
}
