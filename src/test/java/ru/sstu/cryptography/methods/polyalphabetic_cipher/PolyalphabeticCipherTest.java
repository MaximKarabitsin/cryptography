package ru.sstu.cryptography.methods.polyalphabetic_cipher;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sstu.cryptography.constraints.Alphabet;
import ru.sstu.cryptography.methods.Coder;
import ru.sstu.cryptography.methods.Data;
import ru.sstu.cryptography.methods.Decoder;

import static org.junit.jupiter.api.Assertions.*;

class PolyalphabeticCipherTest {
    private final static String ALPHABET = Alphabet.EN.getAlphabet();
    private final static String TEXT = "ATtACKAtDAWN";
    private final static String KEY = "LEMON";
    private final static String CIPHER = "LXfOPVEfRNHR";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void encryptTest() {
        Data data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key(KEY.toCharArray())
                .text(TEXT.toCharArray())
                .caseSensitive(false)
                .build();
        Coder coder = new PolyalphabeticCipher(data);
        assertEquals(CIPHER, new String(coder.encrypt()));

        data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key(KEY.toCharArray())
                .text(TEXT.toCharArray())
                .build();
        coder = new PolyalphabeticCipher(data);
        assertEquals(CIPHER.toUpperCase(), new String(coder.encrypt()));
    }

    @Test
    void decryptTest() {
        Data data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key(KEY.toCharArray())
                .cipher(CIPHER.toCharArray())
                .caseSensitive(false)
                .build();
        Decoder decoder = new PolyalphabeticCipher(data);
        assertEquals(TEXT, new String(decoder.decrypt()));


        data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key(KEY.toCharArray())
                .cipher(CIPHER.toCharArray())
                .build();
        decoder = new PolyalphabeticCipher(data);
        assertEquals(TEXT.toUpperCase(), new String(decoder.decrypt()));
    }

    @Test
    void incorrectAlphabetTest() {
        Data data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key(KEY.toCharArray())
                .text("AD12".toCharArray())
                .build();
        Coder coder = new PolyalphabeticCipher(data);
        Exception ex = assertThrows(IllegalArgumentException.class, coder::encrypt);
        assertEquals("Incorrect alphabet", ex.getMessage());

        data = DataForPolyalphabeticCipher.builder()
                .alphabet(ALPHABET.toCharArray())
                .key("Qad1W".toCharArray())
                .cipher(CIPHER.toCharArray())
                .build();
        Decoder decoder = new PolyalphabeticCipher(data);
        ex = assertThrows(IllegalArgumentException.class, decoder::decrypt);
        assertEquals("Incorrect alphabet", ex.getMessage());
    }

}