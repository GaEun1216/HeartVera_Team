package com.sparta.heartvera.domain.like.repository;

import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepositoryCustom{

    Page<Post> LikesPost(Long userId, Pageable pageable);
    Page<PublicPost> LikesPubPost(Long userId, Pageable pageable);
    Page<Comment> LikesComment(Long userId, Pageable pageable);

    int GetLikesCount(Long userId, LikeEnum type);
    int GetPostLikesCount(Long postId);
    int GetPubPostLikesCount(Long postId);
    int GetCommentLikesCount(Long commentId);

}
