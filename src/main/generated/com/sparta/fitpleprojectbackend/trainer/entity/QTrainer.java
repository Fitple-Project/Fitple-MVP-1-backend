package com.sparta.fitpleprojectbackend.trainer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrainer is a Querydsl query type for Trainer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainer extends EntityPathBase<Trainer> {

    private static final long serialVersionUID = 1184069368L;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final com.sparta.fitpleprojectbackend.common.QTimeStamped _super = new com.sparta.fitpleprojectbackend.common.QTimeStamped(this);

    public final StringPath accountId = createString("accountId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMembership = createBoolean("isMembership");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Double> ptPrice = createNumber("ptPrice", Double.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.enums.Role> role = createEnum("role", com.sparta.fitpleprojectbackend.enums.Role.class);

    public final StringPath trainerInfo = createString("trainerInfo");

    public final StringPath trainerName = createString("trainerName");

    public final StringPath trainerPhoneNumber = createString("trainerPhoneNumber");

    public final StringPath trainerPicture = createString("trainerPicture");

    public final StringPath trainerStatus = createString("trainerStatus");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTrainer(String variable) {
        super(Trainer.class, forVariable(variable));
    }

    public QTrainer(Path<? extends Trainer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrainer(PathMetadata metadata) {
        super(Trainer.class, metadata);
    }

}

