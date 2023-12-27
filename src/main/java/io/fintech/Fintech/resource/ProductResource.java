package io.fintech.Fintech.resource;

import io.fintech.Fintech.domain.HttpResponse;
import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.dtomapper.ProductMapper;
import io.fintech.Fintech.service.ProductService;
import io.fintech.Fintech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static io.fintech.Fintech.utils.UserUtils.getAuthenticatedUser;
import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path="/product")
@RequiredArgsConstructor
public class ProductResource {
    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> saveProduct(@AuthenticationPrincipal @Valid UserDTO user, @RequestBody @Valid Product product) {
        //Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "product", productService.createProduct(product, user)))
                        .message("Product created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getProducts(@AuthenticationPrincipal UserDTO user, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "page", productService.getProductsWithCategory(page.orElse(0), size.orElse(10), user)))
                        .message("Products retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteProduct(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "page", productService.deleteProduct(id)))
                        .message("Product deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }


}
