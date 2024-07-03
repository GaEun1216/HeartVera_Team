package com.sparta.heartvera.domain.like.repository;

import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepositoryCustom{

    Page<Post> LikesPost(Long userId, int page, int size);
    Page<PublicPost> LikesPubPost(Long userId, int page, int size);
    Page<Comment> LikesComment(Long userId, int page, int size);

    int GetLikesCount(Long userId, LikeEnum type);

}
