package com.rminaya.sendaturistica.domain.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
public class AuditableEntity {

    @Column(name = "created_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime modifiedDate;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean activo;

    @PrePersist
    public void prePersist() {
        activo = true;
        modifiedDate = LocalDateTime.now();
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
