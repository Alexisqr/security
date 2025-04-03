package edu.oleks.security.Animal;


import com.mongodb.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/*
@author   oleksandra
@project   security
@class  AuditMetaData
@version  1.0.0
@since 03.04.2025 - 11.12
*/
@Data
@NoArgsConstructor
public class AuditMetaData {

    @CreatedDate
    @NonNull
    private LocalDateTime createdDate;

    @CreatedBy
    @NonNull
    @Field("createdBy")
    private String createdBy;

    @LastModifiedDate
    @Nullable
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Nullable
    private String lastModifiedBy;
}