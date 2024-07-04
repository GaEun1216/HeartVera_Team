package com.sparta.heartvera.domain.like.repository;

import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepositoryCustom{

    List<Post> LikesPost(Long userId, int page, int size);
    List<PublicPost> LikesPubPost(Long userId, int page, int size);
    List<Comment> LikesComment(Long userId, int page, int size);

    int GetLikesCount(Long userId, LikeEnum type);
    int GetPostLikesCount(Long postId);
    int GetPubPostLikesCount(Long postId);
    int GetCommentLikesCount(Long commentId);

}
