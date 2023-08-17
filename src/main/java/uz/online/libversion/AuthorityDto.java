package uz.online.libversion;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDto {
    private Long id;
    @NotBlank(message = "Value must be not null")
    private String username;
    @NotBlank(message = "Value must be not null")
    private String authority;
}
