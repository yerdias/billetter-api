package com.hackload.billetter_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;

    // только для тестов, в production убрать
    @Column(name = "password_plain", length = 255)
    private String passwordPlain;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column
    private LocalDate birthday;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "last_logged_in", nullable = false)
    private LocalDateTime lastLoggedIn;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
