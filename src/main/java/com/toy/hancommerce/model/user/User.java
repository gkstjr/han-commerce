package com.toy.hancommerce.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toy.hancommerce.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(unique = true , nullable = false , length = 50)
    private String username;

    @JsonIgnore
    @Column(length = 100)
    private String password;

    @JsonIgnore//추후에 응답 요청 Dto로 변경 하면 좋을 듯
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id" , referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "authority_name" )})
    private Set<Authority> authorities;

    @Embedded
    private Address address;

}
