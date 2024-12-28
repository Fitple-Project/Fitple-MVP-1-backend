package com.sparta.fitpleprojectbackend.ptsesson.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPtInfomation is a Querydsl query type for PtInfomation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPtInfomation extends EntityPathBase<PtInfomation> {

    private static final long serialVersionUID = -1375552831L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPtInfomation ptInfomation = new QPtInfomation("ptInfomation");

    public final com.sparta.fitpleprojectbackend.common.QTimeStamped _super = new com.sparta.fitpleprojectbackend.common.QTimeStamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMembership = createBoolean("isMembership");

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus> paymentStatus = createEnum("paymentStatus", com.sparta.fitpleprojectbackend.ptsesson.enums.PaymentStatus.class);

    public final NumberPath<Double> ptPrice = createNumber("ptPrice", Double.class);

    public final EnumPath<com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes> ptTimes = createEnum("ptTimes", com.sparta.fitpleprojectbackend.ptsesson.enums.PtTimes.class);

    public final com.sparta.fitpleprojectbackend.trainer.entity.QTrainer trainer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.fitpleprojectbackend.user.entity.QUser user;

    public QPtInfomation(String variable) {
        this(PtInfomation.class, forVariable(variable), INITS);
    }

    public QPtInfomation(Path<? extends PtInfomation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPtInfomation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPtInfomation(PathMetadata metadata, PathInits inits) {
        this(PtInfomation.class, metadata, inits);
    }

    public QPtInfomation(Class<? extends PtInfomation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new com.sparta.fitpleprojectbackend.trainer.entity.QTrainer(forProperty("trainer")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.fitpleprojectbackend.user.entity.QUser(forProperty("user")) : null;
    }

}

