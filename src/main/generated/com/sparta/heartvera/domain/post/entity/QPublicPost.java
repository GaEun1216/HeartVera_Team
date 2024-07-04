package com.sparta.heartvera.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicPost is a Querydsl query type for PublicPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublicPost extends EntityPathBase<PublicPost> {

    private static final long serialVersionUID = 1769647520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicPost publicPost = new QPublicPost("publicPost");

    public final com.sparta.heartvera.common.QTimestamped _super = new com.sparta.heartvera.common.QTimestamped(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final ListPath<com.sparta.heartvera.domain.like.entity.Like, com.sparta.heartvera.domain.like.entity.QLike> likes = this.<com.sparta.heartvera.domain.like.entity.Like, com.sparta.heartvera.domain.like.entity.QLike>createList("likes", com.sparta.heartvera.domain.like.entity.Like.class, com.sparta.heartvera.domain.like.entity.QLike.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath title = createString("title");

    public final com.sparta.heartvera.domain.user.entity.QUser user;

    public QPublicPost(String variable) {
        this(PublicPost.class, forVariable(variable), INITS);
    }

    public QPublicPost(Path<? extends PublicPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicPost(PathMetadata metadata, PathInits inits) {
        this(PublicPost.class, metadata, inits);
    }

    public QPublicPost(Class<? extends PublicPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.heartvera.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

