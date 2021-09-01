package com.nguyenmauhuy.authentication.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;

    @ManyToMany
    @JoinTable(name = "permission",
    joinColumns = @JoinColumn(name = "user_id",nullable = false),
    inverseJoinColumns = @JoinColumn(name = "role_id",nullable = false))
    private Set<Role> roles = new HashSet<>();
}
