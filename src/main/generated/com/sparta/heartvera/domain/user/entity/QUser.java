package com.sparta.heartvera.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1464100525L;

    public static final QUser user = new QUser("user");

    public final com.sparta.heartvera.common.QTimestamped _super = new com.sparta.heartvera.common.QTimestamped(this);

    public final EnumPath<UserRoleEnum> authority = createEnum("authority", UserRoleEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final ListPath<com.sparta.heartvera.domain.follow.entity.Follow, com.sparta.heartvera.domain.follow.entity.QFollow> followers = this.<com.sparta.heartvera.domain.follow.entity.Follow, com.sparta.heartvera.domain.follow.entity.QFollow>createList("followers", com.sparta.heartvera.domain.follow.entity.Follow.class, com.sparta.heartvera.domain.follow.entity.QFollow.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.heartvera.domain.follow.entity.Follow, com.sparta.heartvera.domain.follow.entity.QFollow> followings = this.<com.sparta.heartvera.domain.follow.entity.Follow, com.sparta.heartvera.domain.follow.entity.QFollow>createList("followings", com.sparta.heartvera.domain.follow.entity.Follow.class, com.sparta.heartvera.domain.follow.entity.QFollow.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<PasswordHistory, QPasswordHistory> passwordHistories = this.<PasswordHistory, QPasswordHistory>createList("passwordHistories", PasswordHistory.class, QPasswordHistory.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPassword = createString("userPassword");

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

