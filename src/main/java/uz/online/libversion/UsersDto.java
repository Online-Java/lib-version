package uz.online.libversion;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
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
//    @UserExists(message = "User is already exists")
    private String username;
    @Size(min = 6, message = "Password must contain at least 6 character")
    private String password;
    private String imageURL;
    private String phoneNumber;
    @NotBlank
    @Email(message = "Email is incorrect")
    private String email;

//    private Set<Authorities> authority;

}
