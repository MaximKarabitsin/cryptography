package ru.sstu.cryptography.sevices;

import org.springframework.stereotype.Service;
import ru.sstu.cryptography.exceptions.BadRequestException;
import ru.sstu.cryptography.methods.Coder;
import ru.sstu.cryptography.methods.Data;
import ru.sstu.cryptography.methods.Decoder;
import ru.sstu.cryptography.model.DecryptModel;
import ru.sstu.cryptography.model.EncryptModel;

import java.util.Map;
import java.util.function.Supplier;

import static ru.sstu.cryptography.constraints.Postfix.*;
import static ru.sstu.cryptography.utils.MapUtil.get;

@Service
public class DecryptService {

    private final Map<String, Supplier<Decoder>> decoderSuppliers;
    private final Map<String, Supplier<Data>> dataSuppliers;

    public DecryptService(Map<String, Supplier<Decoder>> decoderSuppliers, Map<String, Supplier<Data>> dataSuppliers) {
        this.decoderSuppliers = decoderSuppliers;
        this.dataSuppliers = dataSuppliers;
    }


    public char[] decrypt(DecryptModel decryptModel) {
        if (decryptModel.getCipher() == null || decryptModel.getCipher().isEmpty()) throw new BadRequestException("Cipher is empty");
        char[] cipher = decryptModel.getCipher().toCharArray();
        char[] text = cipher;
        for (Map<String, String> map : decryptModel.getMethods()) {
            String method = get(map, "method")
                    .orElseThrow(BadRequestException::new);
            String methodSupplier = method + DECODER + SUPPLIER;
            if (!decoderSuppliers.containsKey(methodSupplier))
                throw new BadRequestException("Unknown method \"" + method + "\"");
            Decoder decoder = decoderSuppliers.get(methodSupplier).get();

            String dataSupplier = method + DATA + SUPPLIER;
            if (!dataSuppliers.containsKey(dataSupplier))
                throw new BadRequestException("Unknown method \"" + method + "\"");
            Data data = dataSuppliers.get(dataSupplier).get();
            data.setData(map);
            data.setCipher(cipher);
            decoder.setData(data);
            text = cipher = decoder.decrypt();
        }
        return text;
    }
}
