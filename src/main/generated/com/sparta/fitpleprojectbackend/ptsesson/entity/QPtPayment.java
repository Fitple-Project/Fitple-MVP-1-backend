package com.sparta.fitpleprojectbackend.ptsesson.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPtPayment is a Querydsl query type for PtPayment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPtPayment extends EntityPathBase<PtPayment> {

    private static final long serialVersionUID = -1100294149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPtPayment ptPayment = new QPtPayment("ptPayment");

    public final com.sparta.fitpleprojectbackend.common.QTimeStamped _super = new com.sparta.fitpleprojectbackend.common.QTimeStamped(this);

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMembership = createBoolean("isMembership");

    public final DateTimePath<java.time.LocalDateTime> paymentDate = createDateTime("paymentDate", java.time.LocalDateTime.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus> paymentStatus = createEnum("paymentStatus", com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentType> paymentType = createEnum("paymentType", com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentType.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes> ptTimes = createEnum("ptTimes", com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes.class);

    public final com.sparta.fitpleprojectbackend.trainer.entity.QTrainer trainer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.fitpleprojectbackend.user.entity.QUser user;

    public QPtPayment(String variable) {
        this(PtPayment.class, forVariable(variable), INITS);
    }

    public QPtPayment(Path<? extends PtPayment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPtPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPtPayment(PathMetadata metadata, PathInits inits) {
        this(PtPayment.class, metadata, inits);
    }

    public QPtPayment(Class<? extends PtPayment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.sparta.fitpleprojectbackend.trainer.entity.QTrainer(forProperty("trainer")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.fitpleprojectbackend.user.entity.QUser(forProperty("user")) : null;
    }

}

