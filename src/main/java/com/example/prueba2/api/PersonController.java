package com.example.prueba2.api;

import com.example.prueba2.application.dtos.PersonView;
import com.example.prueba2.application.dtos.Request.RegisterPersonRequest;
import com.example.prueba2.application.dtos.Response.RegisterPersonResponse;
import com.example.prueba2.application.notification.Notification;
import com.example.prueba2.application.notification.Result;
import com.example.prueba2.application.services.PersonApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/person")
//@Api(tags = "Clients")
public class PersonController {
    private final PersonApplicationService personApplicationService;

    public PersonController(PersonApplicationService personApplicationService) {
        this.personApplicationService = personApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerPerson(@RequestBody RegisterPersonRequest registerPersonRequest) {
        try {
            Result<RegisterPersonResponse, Notification> result = personApplicationService.registerPerson(registerPersonRequest);
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
            PersonView personView = personApplicationService.getById(id);
            return ApiController.ok(personView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
}
