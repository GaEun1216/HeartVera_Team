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
        /*
        SELECT *
        FROM post AS p
                 LEFT JOIN likes AS l ON p.id = l.post_id
        WHERE l.user_id = 2 AND
              l.content_type = "POST";
         */
        QPost post = QPost.post;
        QLike like = QLike.like;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page-1, size, sort);

        QueryResults<Post> queryResults = jpaQueryFactory
                .selectFrom(post)
                .leftJoin(like).on(post.id.eq(like.post.id))
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.POST)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Post> postList = queryResults.getResults();
        long total = queryResults.getTotal();

        return new PageImpl<>(postList, pageable, total);
    }

    @Override
    public Page<PublicPost> LikesPubPost(Long userId, int page, int size) {
        QPublicPost post = QPublicPost.publicPost;
        QLike like = QLike.like;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page-1, size, sort);

        QueryResults<PublicPost> queryResults = jpaQueryFactory
                .selectFrom(post)
                .leftJoin(like).on(post.id.eq(like.publicPost.id))
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.PUBPOST)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<PublicPost> postList = queryResults.getResults();
        long total = queryResults.getTotal();

        return new PageImpl<>(postList, pageable, total);
    }

    @Override
    public Page<Comment> LikesComment(Long userId, int page, int size) {
        QComment comment = QComment.comment;
        QLike like = QLike.like;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page-1, size, sort);

        QueryResults<Comment> queryResults = jpaQueryFactory
                .selectFrom(comment)
                .leftJoin(like).on(comment.id.eq(like.comment.id))
                .where(like.user.userSeq.eq(userId)
                        .and(like.contentType.eq(LikeEnum.COMMENT)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Comment> commentList = queryResults.getResults();
        long total = queryResults.getTotal();

        return new PageImpl<>(commentList, pageable, total);
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
