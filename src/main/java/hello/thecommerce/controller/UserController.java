package hello.thecommerce.controller;

import hello.thecommerce.dto.UserDto;
import hello.thecommerce.entity.User;
import hello.thecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public @ResponseBody ResponseEntity joinUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity("회원가입 성공", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public @ResponseBody ResponseEntity getUserList(@RequestParam int page, @RequestParam int pageSize, @RequestParam String sort) {
        Page<User> userList = userService.getUserList(page, pageSize, sort);

        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping("/{login_id}")
    public @ResponseBody ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userService.updateUser(userDto);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
