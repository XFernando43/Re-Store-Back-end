package com.example.prueba2.api;


import com.example.prueba2.application.dtos.ProductView;
import com.example.prueba2.application.dtos.Request.RegisterProductRequest;
import com.example.prueba2.application.dtos.Response.RegisterProductResponse;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import com.example.prueba2.application.services.ProductApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerProduct(@RequestBody RegisterProductRequest registerProductRequest) {
        try {
            Result<RegisterProductResponse, Notification> result = productApplicationService.registerProduct(registerProductRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Get client person by id", response = PersonView.class)
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            ProductView productView = productApplicationService.getById(id);
            return ApiController.ok(productView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

}
