package com.sparta.heartvera.domain.follow.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.heartvera.domain.follow.entity.QFollow;
import com.sparta.heartvera.domain.post.entity.PublicPost;
import com.sparta.heartvera.domain.post.entity.QPublicPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<PublicPost> getFollowerPostsByUserId(Long followerUserId, Pageable pageable, String sortBy) {
        /*
        SELECT pp.* FROM public_post AS pp
         LEFT JOIN FOLLOW AS f ON (pp.user_id = f.to_user)
        WHERE f.from_user = 2;
         */
        QFollow follow = QFollow.follow;
        QPublicPost post = QPublicPost.publicPost;
        OrderSpecifier<?> orderSpecifier;
        if ("userName".equals(sortBy)) {
            orderSpecifier = post.user.userName.asc();
        } else {
            orderSpecifier = post.createdAt.desc();
        }

        List<PublicPost> postList = jpaQueryFactory
                .selectFrom(post)
                .leftJoin(follow).on(post.user.userId.eq(follow.toUser.userId))
                .where(follow.fromUser.userSeq.eq(followerUserId))
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(post.count())
                .from(post)
                .leftJoin(follow).on(post.user.userId.eq(follow.toUser.userId))
                .where(follow.fromUser.userSeq.eq(followerUserId))
                .fetchOne();

        if(total == null){
            total = 0L;
        }

        return new PageImpl<>(postList, pageable, total);
    }
}
