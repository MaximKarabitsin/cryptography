package ru.sstu.cryptography.methods.polyalphabetic_cipher;

import lombok.*;
import ru.sstu.cryptography.constraints.Alphabet;
import ru.sstu.cryptography.exceptions.BadRequestException;
import ru.sstu.cryptography.methods.Data;

import java.util.Map;

import static ru.sstu.cryptography.utils.MapUtil.get;

@Builder
@Getter
@AllArgsConstructor
public class DataForPolyalphabeticCipher extends Data {

    private char[] text;
    private char[] cipher;
    private char[] alphabet;
    private char[] key;
    private boolean caseSensitive;

    public DataForPolyalphabeticCipher() {
    }

    @Override
    public void setData(Map<String, String> map) {
        key = get(map, "key")
                .orElseThrow(BadRequestException::new)
                .toCharArray();
        alphabet = Alphabet.parse(get(map, "alphabet")
                .orElseThrow(BadRequestException::new).toUpperCase())
                .getAlphabet()
                .toCharArray();
        caseSensitive = Boolean.parseBoolean(map.get("caseSensitive"));
        text = get(map, "text").orElse("").toCharArray();
        cipher = get(map, "cipher").orElse("").toCharArray();
    }

    @Override
    public void setText(char[] text) {
        this.text = text;
    }

    @Override
    public void setCipher(char[] cipher) {
        this.cipher = cipher;
    }
}
