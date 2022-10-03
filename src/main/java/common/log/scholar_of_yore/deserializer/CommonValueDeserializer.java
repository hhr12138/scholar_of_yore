package common.log.scholar_of_yore.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class CommonValueDeserializer implements Deserializer<Object> {

    ObjectMapper objectMapper = new ObjectMapper();
    StringDeserializer stringDeserializer = new StringDeserializer();

    @SneakyThrows
    @Override
    public Object deserialize(String s, byte[] bytes) {
        String result = stringDeserializer.deserialize(s, bytes);
        Object ans = objectMapper.readValue(result,Object.class);
        return ans;
    }
}
