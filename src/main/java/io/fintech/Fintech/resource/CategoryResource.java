package io.fintech.Fintech.resource;

import io.fintech.Fintech.domain.HttpResponse;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.service.CategoryService;
import io.fintech.Fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.fintech.Fintech.utils.UserUtils.getAuthenticatedUser;
import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path="/category")
@RequiredArgsConstructor
public class CategoryResource {
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> profile(Authentication authentication)  {
        UserDTO user = userService.getUserByEmail(getAuthenticatedUser(authentication).getEmail());
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", user,"category", categoryService.listCategory()))
                        .message("Category Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}
