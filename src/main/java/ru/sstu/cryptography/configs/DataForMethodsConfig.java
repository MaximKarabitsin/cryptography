package ru.sstu.cryptography.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sstu.cryptography.methods.Data;
import ru.sstu.cryptography.methods.polyalphabetic_cipher.DataForPolyalphabeticCipher;

import java.util.function.Supplier;

@Configuration
public class DataForMethodsConfig {


    @Bean
    public Supplier<Data> polyalphabeticCipherDataSupplier(){
        return this::dataForPolyalphabeticCipher;
    }

    @Bean
    @Scope("prototype")
    public DataForPolyalphabeticCipher dataForPolyalphabeticCipher(){
        return new DataForPolyalphabeticCipher();
    }

}
