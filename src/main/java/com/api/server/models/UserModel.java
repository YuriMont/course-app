package com.api.server.models;

import com.api.server.dtos.user.RegisterDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private Date birthDate;
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<OrderModel> orders;
    public UserModel(RegisterDTO registerDTO) {
        this.firstName = registerDTO.getFirstName();
        this.lastName = registerDTO.getLastName();
        this.cpf = registerDTO.getCpf();
        this.email = registerDTO.getEmail();
        this.birthDate = registerDTO.getBirthDate();
        this.password = registerDTO.getPassword();
        this.orders = new ArrayList<>();
    }

    public void addOrder(OrderModel orderModel){
        orders.add(orderModel);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
