package ru.sstu.cryptography.methods;

import java.util.Map;

public abstract class Data {

    public abstract  void setData(Map<String,String> map);
    public abstract  void setText(char[] text);
    public abstract  void setCipher(char[] cipher);

}
