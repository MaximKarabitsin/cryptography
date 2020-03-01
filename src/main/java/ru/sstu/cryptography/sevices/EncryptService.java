package ru.sstu.cryptography.sevices;

import org.springframework.stereotype.Service;
import ru.sstu.cryptography.exceptions.BadRequestException;
import ru.sstu.cryptography.methods.Coder;
import ru.sstu.cryptography.methods.Data;
import ru.sstu.cryptography.model.EncryptModel;

import java.util.Map;
import java.util.function.Supplier;

import static ru.sstu.cryptography.constraints.Postfix.*;
import static ru.sstu.cryptography.utils.MapUtil.get;

@Service
public class EncryptService {

    private final Map<String, Supplier<Coder>> coderSuppliers;
    private final Map<String, Supplier<Data>> dataSuppliers;

    public EncryptService(Map<String, Supplier<Coder>> coderSuppliers, Map<String, Supplier<Data>> dataSuppliers) {
        this.coderSuppliers = coderSuppliers;
        this.dataSuppliers = dataSuppliers;
    }


    public char[] encrypt(EncryptModel encryptModel) {
        if (encryptModel.getText() == null || encryptModel.getText().isEmpty()) throw new BadRequestException("Text is empty");
        char[] text = encryptModel.getText().toCharArray();
        char[] cipher = text;
        for (Map<String, String> map : encryptModel.getMethods()) {
            String method = get(map, "method")
                    .orElseThrow(BadRequestException::new);
            String methodSupplier = method + CODER + SUPPLIER;
            if (!coderSuppliers.containsKey(methodSupplier))
                throw new BadRequestException("Unknown method \"" + method + "\"");
            Coder coder = coderSuppliers.get(methodSupplier).get();

            String dataSupplier = method + DATA + SUPPLIER;
            if (!dataSuppliers.containsKey(dataSupplier))
                throw new BadRequestException("Unknown method \"" + method + "\"");
            Data data = dataSuppliers.get(dataSupplier).get();
            data.setData(map);
            data.setText(text);
            coder.setData(data);
            cipher = text = coder.encrypt();
        }
        return cipher;
    }
}
