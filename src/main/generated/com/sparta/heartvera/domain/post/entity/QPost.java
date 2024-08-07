package com.sparta.heartvera.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1088280361L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.sparta.heartvera.common.QTimestamped _super = new com.sparta.heartvera.common.QTimestamped(this);

    public final ListPath<com.sparta.heartvera.domain.comment.entity.Comment, com.sparta.heartvera.domain.comment.entity.QComment> comments = this.<com.sparta.heartvera.domain.comment.entity.Comment, com.sparta.heartvera.domain.comment.entity.QComment>createList("comments", com.sparta.heartvera.domain.comment.entity.Comment.class, com.sparta.heartvera.domain.comment.entity.QComment.class, PathInits.DIRECT2);

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

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.heartvera.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

