package ru.sstu.cryptography.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sstu.cryptography.methods.Coder;
import ru.sstu.cryptography.methods.Decoder;
import ru.sstu.cryptography.methods.polyalphabetic_cipher.PolyalphabeticCipher;

import java.util.function.Supplier;

@Configuration
public class MethodsConfig {


    @Bean
    public Supplier<Coder> polyalphabeticCipherCoderSupplier() {
        return this::polyalphabeticCipher;
    }

    @Bean
    public Supplier<Decoder> polyalphabeticCipherDecoderSupplier() {
        return this::polyalphabeticCipher;
    }

    @Bean
    @Scope(value = "prototype")
    public PolyalphabeticCipher polyalphabeticCipher() {
        return new PolyalphabeticCipher();
    }

}
