package hello.thecommerce.service;

import hello.thecommerce.dto.UserDto;
import hello.thecommerce.entity.User;
import hello.thecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        validateDuplicateMember(userDto.getLoginId());
        userRepository.save(User.createUser(userDto));
    }

    private void validateDuplicateMember(String loginId) {
        User findUser = userRepository.findByLoginId(loginId);

        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Page<User> getUserList(int page, int pageSize, String sort) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sort));
        return userRepository.findAll(pageable);
    }

    public User updateUser(UserDto userDto) {
        User user = userRepository.findByLoginId(userDto.getLoginId());
        user.updateUser(userDto);

        return user;
    }
}
