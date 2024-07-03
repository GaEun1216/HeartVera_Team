package com.sparta.heartvera.domain.like.repository;

import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.like.entity.Like;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeRepositoryCustom {

    Optional<Like> findByUserAndComment(User user, Comment comment);
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndPublicPost(User user, PublicPost publicPost);

}