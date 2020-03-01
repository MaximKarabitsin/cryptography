package ru.sstu.cryptography.constraints;

import ru.sstu.cryptography.exceptions.BadRequestException;

public enum Alphabet {
    EN("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    RU("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЮЬЭЮЯ");

    final String alphabet;

    Alphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public static Alphabet parse(String string){
        try {
            return valueOf(string);
        } catch (Exception ex){
            throw new BadRequestException("Incorrect alphabet");
        }
    }
}
