package co.com.ancas.models.ports;

import co.com.ancas.models.domain.MediaMessage;

import java.io.IOException;
import java.util.Map;

public interface ICloudinaryPort {
    Map uploadFile(MediaMessage file) throws IOException;
}
