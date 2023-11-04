package io.fintech.Fintech.resource;

import io.fintech.Fintech.domain.HttpResponse;
import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.domain.UserPrincipal;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.form.LoginForm;
import io.fintech.Fintech.provider.TokenProvider;
import io.fintech.Fintech.service.RoleService;
import io.fintech.Fintech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static io.fintech.Fintech.dtomapper.UserDTOMapper.toUser;
import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping(path="/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(),loginForm.getPassword()));
        UserDTO user = userService.getUserByEmail(loginForm.getEmail());
        return user.isUsingMfa() ? sendVerificationCode(user) : sendResponse(user);
    }
    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDTO))
                        .message("User created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("/profile")
    public ResponseEntity<HttpResponse> profile(Authentication authentication)  {
        UserDTO user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user ))
                        .message("Profile Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/verify/code/{email}/{code}")
    public ResponseEntity<HttpResponse> verifyCode(@PathVariable("email") String email, @PathVariable("code") String code)  {
        UserDTO user = userService.verifyCode(email,code);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user, "access_token", tokenProvider.createAccessToken(getUserPrincipal(user))
                                , "refresh_token", tokenProvider.createRefreshToken(getUserPrincipal(user))))
                        .message("Login Success")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    private URI getUri() {
        return URI.create(fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }
    private ResponseEntity<HttpResponse> sendResponse(UserDTO user) {
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user, "access_token", tokenProvider.createAccessToken(getUserPrincipal(user))
                        , "refresh_token", tokenProvider.createRefreshToken(getUserPrincipal(user))))
                        .message("Login Success")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
        //TODO Access token and Refresh TOKEN
    }

    private UserPrincipal getUserPrincipal(UserDTO user) {
        return new UserPrincipal(toUser(userService.getUserByEmail(user.getEmail())), roleService.getRoleByUserId(user.getId()).getPermission());
    }

    private ResponseEntity<HttpResponse> sendVerificationCode(UserDTO user) {
        userService.sendVerificationCode(user);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user))
                        .message("Verification Code Sent")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());

    }
}
