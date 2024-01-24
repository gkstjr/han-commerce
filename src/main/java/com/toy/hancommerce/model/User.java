package com.toy.hancommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(unique = true , nullable = false)
    private String username;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id" , referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id",referencedColumnName = "authority_id" )})
    private Set<Authority> authorities;

    @Embedded
    private Address address;



}
