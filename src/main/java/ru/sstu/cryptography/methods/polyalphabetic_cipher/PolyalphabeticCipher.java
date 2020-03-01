package ru.sstu.cryptography.methods.polyalphabetic_cipher;

import ru.sstu.cryptography.exceptions.BadRequestException;
import ru.sstu.cryptography.methods.Coder;
import ru.sstu.cryptography.methods.Data;
import ru.sstu.cryptography.methods.Decoder;

import static java.lang.Character.*;

public class PolyalphabeticCipher implements Coder, Decoder {

    private DataForPolyalphabeticCipher data;

    public PolyalphabeticCipher() {
    }

    public PolyalphabeticCipher(Data data) {
        this.data = (DataForPolyalphabeticCipher) data;
    }

    @Override
    public char[] encrypt() {
        char[] cipher = new char[data.getText().length];
        for (int i = 0; i < data.getText().length; i++) {
            if (!data.isCaseSensitive() || isUpperCase(data.getText()[i])) {
                cipher[i] = getLetterCipher(i);
            } else {
                cipher[i] = toLowerCase(getLetterCipher(i));
            }
        }
        return cipher;
    }

    @Override
    public char[] decrypt() {
        char[] text = new char[data.getCipher().length];
        for (int i = 0; i < data.getCipher().length; i++) {
            if (!data.isCaseSensitive() || isUpperCase(data.getCipher()[i])) {
                text[i] = getLetterText(i);
            } else {
                text[i] = toLowerCase(getLetterText(i));
            }
        }
        return text;
    }

    private char getLetterCipher(int i) {
        int keyLetterNumber = getLetterNumber(data.getKey()[i % data.getKey().length]);
        int textLetterNumber = getLetterNumber(data.getText()[i]);
        return data.getAlphabet()[(textLetterNumber + keyLetterNumber) % data.getAlphabet().length];
    }

    private char getLetterText(int i) {
        int keyLetterNumber = getLetterNumber(data.getKey()[i % data.getKey().length]);
        int textCipherNumber = getLetterNumber(data.getCipher()[i]);
        return data.getAlphabet()[(data.getAlphabet().length + textCipherNumber - keyLetterNumber) % data.getAlphabet().length];
    }

    private int getLetterNumber(char letter) {
        for (int i = 0; i < data.getAlphabet().length; i++) {
            if (data.getAlphabet()[i] == toUpperCase(letter)) return i;
        }
        throw new BadRequestException("Incorrect alphabet");
    }

    @Override
    public void setData(Data data) {
        this.data= (DataForPolyalphabeticCipher) data;
    }
}
