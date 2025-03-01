package co.com.ancas.cloudinary.adapters;

import co.com.ancas.models.domain.MediaMessage;
import co.com.ancas.models.ports.ICloudinaryPort;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudunaryAdapter  implements ICloudinaryPort {
    private final Cloudinary cloudinary;


    @Override
    public Map uploadFile(MediaMessage file) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(file.getFile());
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "public_id", "whatsapp/" +file.getSenderId()+"/"+file.getNameFile()
        );
        return cloudinary.uploader().upload(decodedBytes, uploadParams);
    }
}
