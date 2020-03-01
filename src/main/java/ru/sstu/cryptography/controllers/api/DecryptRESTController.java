package ru.sstu.cryptography.controllers.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.cryptography.model.DecryptModel;
import ru.sstu.cryptography.sevices.DecryptService;

@RestController
@RequestMapping("/api/decrypt")
public class DecryptRESTController {

    private final DecryptService decryptService;

    public DecryptRESTController(DecryptService decryptService) {
        this.decryptService = decryptService;
    }


    @PostMapping
    public char[] decrypt(@RequestBody DecryptModel decryptModel) {
        return decryptService.decrypt(decryptModel);
    }
}
