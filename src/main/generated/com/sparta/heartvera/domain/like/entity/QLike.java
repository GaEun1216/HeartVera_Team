package com.sparta.heartvera.domain.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Like> {

    private static final long serialVersionUID = 353934405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLike like = new QLike("like1");

    public final com.sparta.heartvera.common.QTimestamped _super = new com.sparta.heartvera.common.QTimestamped(this);

    public final com.sparta.heartvera.domain.comment.entity.QComment comment;

    public final EnumPath<LikeEnum> contentType = createEnum("contentType", LikeEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.sparta.heartvera.domain.post.entity.QPost post;

    public final com.sparta.heartvera.domain.post.entity.QPublicPost publicPost;

    public final com.sparta.heartvera.domain.user.entity.QUser user;

    public QLike(String variable) {
        this(Like.class, forVariable(variable), INITS);
    }

    public QLike(Path<? extends Like> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLike(PathMetadata metadata, PathInits inits) {
        this(Like.class, metadata, inits);
    }

    public QLike(Class<? extends Like> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new com.sparta.heartvera.domain.comment.entity.QComment(forProperty("comment"), inits.get("comment")) : null;
        this.post = inits.isInitialized("post") ? new com.sparta.heartvera.domain.post.entity.QPost(forProperty("post"), inits.get("post")) : null;
        this.publicPost = inits.isInitialized("publicPost") ? new com.sparta.heartvera.domain.post.entity.QPublicPost(forProperty("publicPost"), inits.get("publicPost")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.heartvera.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

