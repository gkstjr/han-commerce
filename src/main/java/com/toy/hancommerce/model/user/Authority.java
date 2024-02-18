package com.toy.hancommerce.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @Column(name = "authority_name" , length = 50)
    private String authorityName;
}
