package hello.thecommerce.entity;

import hello.thecommerce.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    @Column(unique = true)
    private String nickname;
    private String username;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Builder
    public User(String loginId, String password, String nickname, String username, String phoneNumber, String email) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static User createUser(UserDto userDto) {
        return User.builder()
                .loginId(userDto.getLoginId())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .username(userDto.getUsername())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .build();
    }

    public void updateUser(UserDto userDto) {
        this.password = userDto.getPassword();
        this.nickname = userDto.getNickname();
        this.username = userDto.getUsername();
        this.phoneNumber = userDto.getPhoneNumber();
        this.email = userDto.getEmail();
    }
}
