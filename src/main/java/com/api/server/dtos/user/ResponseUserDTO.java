package com.api.server.dtos.user;

import com.api.server.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseUserDTO {
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private Date birthDate;

    public ResponseUserDTO(UserModel user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
    }
}
