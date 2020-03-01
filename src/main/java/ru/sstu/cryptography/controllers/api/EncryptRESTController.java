package ru.sstu.cryptography.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sstu.cryptography.model.EncryptModel;
import ru.sstu.cryptography.sevices.EncryptService;

@RestController
@RequestMapping("/api/encrypt")
public class EncryptRESTController {

    private final EncryptService encryptService;

    public EncryptRESTController(EncryptService encryptService) {
        this.encryptService = encryptService;
    }


    @PostMapping
    public char[] encrypt(@RequestBody EncryptModel encryptModel) {
        return encryptService.encrypt(encryptModel);
    }
}
