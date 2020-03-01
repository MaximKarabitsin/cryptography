package ru.sstu.cryptography.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class EncryptModel {

    private String text;
    private List<Map<String, String>> methods;

}
