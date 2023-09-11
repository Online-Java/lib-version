package uz.online.libversion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = "password", allowSetters = true)
public class UsersDto implements UserDetails {
    private Integer id;
    @NotBlank(message = "firstName is null or not contains any character")
    private String firstName;
    @NotBlank(message = "lastName is null or not contains any character")
    private String lastName;
    //01.07.2000
    @Pattern(regexp = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}", message = "date format is incorrect") //dd.MM.yyyy
    private String birthDate;
    private String passport;
    private Long pinfl;
    //@UserExists(message = "User is already exists")
    private String username;
    @Size(min = 6, message = "Password must contain at least 6 character")
    private String password;
    private String imageURL;
    private String phoneNumber;
    @NotBlank
    @Email(message = "Email is incorrect")
    private String email;

    @JsonIgnore
    private List<AuthorityDto> authorities;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.of(authorities)
                .map(auth -> auth.stream()
                        .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
