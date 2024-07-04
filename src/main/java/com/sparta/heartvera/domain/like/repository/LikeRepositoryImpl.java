package com.sparta.heartvera.domain.like.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.heartvera.domain.comment.entity.Comment;
import com.sparta.heartvera.domain.comment.entity.QComment;
import com.sparta.heartvera.domain.like.entity.LikeEnum;
import com.sparta.heartvera.domain.like.entity.QLike;
import com.sparta.heartvera.domain.post.entity.Post;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.post.entity.QPost;
import com.sparta.heartvera.domain.post.entity.QPublicPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> LikesPost(Long userId, int page, int size) {

        QPost post = QPost.post;
        QLike like = QLike.like;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page-1, size);

        List<Post> postList = jpaQueryFactory
                .selectFrom(post)
                .leftJoin(like).on(post.id.eq(like.post.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.POST)))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(post.count())
                .from(post)
                .leftJoin(like).on(post.id.eq(like.publicPost.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.POST)))
                .fetchOne();

        if(total == null){
            total = 0L;
        }

        return new PageImpl<>(postList, pageable, total);
    }

    @Override
    public Page<PublicPost> LikesPubPost(Long userId, int page, int size) {

        QPublicPost post = QPublicPost.publicPost;
        QLike like = QLike.like;
        Pageable pageable = PageRequest.of(page-1, size);

        List<PublicPost> postList = jpaQueryFactory
                .selectFrom(post)
                .leftJoin(like).on(post.id.eq(like.publicPost.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.PUBPOST)))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(post.count())
                .from(post)
                .leftJoin(like).on(post.id.eq(like.publicPost.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.PUBPOST)))
                .fetchOne();

        if(total == null){
            total = 0L;
        }

        return new PageImpl<>(postList, pageable, total);
    }

    @Override
    public Page<Comment> LikesComment(Long userId, int page, int size) {
        QComment comment = QComment.comment;
        QLike like = QLike.like;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page-1, size, sort);

        List<Comment> commentList = jpaQueryFactory
                .selectFrom(comment)
                .leftJoin(like).on(comment.id.eq(like.comment.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.COMMENT)))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .leftJoin(like).on(comment.id.eq(like.comment.id)).fetchJoin()
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.COMMENT)))
                .fetchOne();

        if(total == null){
            total = 0L;
        }
        return new PageImpl<>(commentList, pageable, total);
    }

    @Override
    public int GetPostLikesCount(Long postId) {
        QLike like = QLike.like;
        Long count = jpaQueryFactory.select(like.count())
                .from(like)
                .where(like.post.id.eq(postId)
                        .and(like.contentType.eq(LikeEnum.POST)))
                .fetchOne();
        return count.intValue();
    }

    @Override
    public int GetPubPostLikesCount(Long postId) {
        QLike like = QLike.like;
        Long count = jpaQueryFactory.select(like.count())
                .from(like)
                .where(like.publicPost.id.eq(postId)
                        .and(like.contentType.eq(LikeEnum.PUBPOST)))
                .fetchOne();
        return count.intValue();
    }

    @Override
    public int GetCommentLikesCount(Long commentId) {
        QLike like = QLike.like;
        Long count = jpaQueryFactory.select(like.count())
                .from(like)
                .where(like.comment.id.eq(commentId)
                        .and(like.contentType.eq(LikeEnum.COMMENT)))
                .fetchOne();
        return count.intValue();
    }

    @Override
    public int GetLikesCount(Long userId, LikeEnum type) {
        /*
        SELECT COUNT(*) FROM LIKE
        WHERE LIKE.TYPE = TYPE
        AND LIKE.USERID = USERID;
         */
        QLike like = QLike.like;
        Long count = jpaQueryFactory.select(like.count())
                .from(like)
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(type)))
                .fetchOne();
        return count.intValue();
    }



}
