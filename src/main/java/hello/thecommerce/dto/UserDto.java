package hello.thecommerce.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String loginId;
    private String password;
    private String nickname;
    private String username;
    private String phoneNumber;
    private String email;
}