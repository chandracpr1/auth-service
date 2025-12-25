package com.chatapp.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(updatable = false,nullable=false)
    private UUID id;

    @Column(nullable = false , length = 50)
    private String username;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private UserStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status=UserStatus.OFFLINE;
    }

    @PreUpdate
    protected  void onUpdate()
    {
        updateAt=LocalDateTime.now();
    }
}
