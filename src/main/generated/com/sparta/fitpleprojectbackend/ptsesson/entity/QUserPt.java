package com.sparta.fitpleprojectbackend.ptsesson.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPt is a Querydsl query type for UserPt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPt extends EntityPathBase<UserPt> {

    private static final long serialVersionUID = -1193052874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPt userPt = new QUserPt("userPt");

    public final com.sparta.fitpleprojectbackend.common.QTimeStamped _super = new com.sparta.fitpleprojectbackend.common.QTimeStamped(this);

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isMembership = createBoolean("isMembership");

    public final DateTimePath<java.time.LocalDateTime> paymentDate = createDateTime("paymentDate", java.time.LocalDateTime.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus> paymentStatus = createEnum("paymentStatus", com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentType> paymentType = createEnum("paymentType", com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentType.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes> ptTimes = createEnum("ptTimes", com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes.class);

    public final com.sparta.fitpleprojectbackend.trainer.entity.QTrainer trainer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.fitpleprojectbackend.user.entity.QUser user;

    public QUserPt(String variable) {
        this(UserPt.class, forVariable(variable), INITS);
    }

    public QUserPt(Path<? extends UserPt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPt(PathMetadata metadata, PathInits inits) {
        this(UserPt.class, metadata, inits);
    }

    public QUserPt(Class<? extends UserPt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.sparta.fitpleprojectbackend.trainer.entity.QTrainer(forProperty("trainer")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.fitpleprojectbackend.user.entity.QUser(forProperty("user")) : null;
    }

}

