package com.toy.hancommerce.model.dto;

import com.toy.hancommerce.model.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NotNull
    @Size(min =3 , max =50)
    private String username;

    @NotNull
    @Size(min =3 , max =100)
    private String password;
}
