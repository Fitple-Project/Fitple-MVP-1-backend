package com.sparta.fitpleprojectbackend.user.entity;

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

    private static final long serialVersionUID = -915328952L;

    public static final QUser user = new QUser("user");

    public final com.sparta.fitpleprojectbackend.common.QTimeStamped _super = new com.sparta.fitpleprojectbackend.common.QTimeStamped(this);

    public final StringPath accountId = createString("accountId");

    public final BooleanPath addInfo = createBoolean("addInfo");

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final NumberPath<Long> birthday = createNumber("birthday", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath detailedAddress = createString("detailedAddress");

    public final StringPath email = createString("email");

    public final ListPath<com.sparta.fitpleprojectbackend.follow.entity.Follow, com.sparta.fitpleprojectbackend.follow.entity.QFollow> followers = this.<com.sparta.fitpleprojectbackend.follow.entity.Follow, com.sparta.fitpleprojectbackend.follow.entity.QFollow>createList("followers", com.sparta.fitpleprojectbackend.follow.entity.Follow.class, com.sparta.fitpleprojectbackend.follow.entity.QFollow.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.fitpleprojectbackend.follow.entity.Follow, com.sparta.fitpleprojectbackend.follow.entity.QFollow> followings = this.<com.sparta.fitpleprojectbackend.follow.entity.Follow, com.sparta.fitpleprojectbackend.follow.entity.QFollow>createList("followings", com.sparta.fitpleprojectbackend.follow.entity.Follow.class, com.sparta.fitpleprojectbackend.follow.entity.QFollow.class, PathInits.DIRECT2);

    public final StringPath foreignerRegistrationNumber = createString("foreignerRegistrationNumber");

    public final StringPath googleId = createString("googleId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isForeigner = createBoolean("isForeigner");

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final StringPath mainAddress = createString("mainAddress");

    public final StringPath naverId = createString("naverId");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<com.sparta.fitpleprojectbackend.post.entity.Post, com.sparta.fitpleprojectbackend.post.entity.QPost> postList = this.<com.sparta.fitpleprojectbackend.post.entity.Post, com.sparta.fitpleprojectbackend.post.entity.QPost>createList("postList", com.sparta.fitpleprojectbackend.post.entity.Post.class, com.sparta.fitpleprojectbackend.post.entity.QPost.class, PathInits.DIRECT2);

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final EnumPath<com.sparta.fitpleprojectbackend.enums.Role> role = createEnum("role", com.sparta.fitpleprojectbackend.enums.Role.class);

    public final DateTimePath<java.time.LocalDateTime> scheduledDeletionDate = createDateTime("scheduledDeletionDate", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userName = createString("userName");

    public final StringPath userPicture = createString("userPicture");

    public final StringPath zipcode = createString("zipcode");

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

