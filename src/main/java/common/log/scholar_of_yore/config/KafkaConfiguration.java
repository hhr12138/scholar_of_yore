package common.log.scholar_of_yore.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    public static final String DEFAULT_TOPIC = "common.log.scholar-of-yore.common-log";

    /**
     * 解决批量消费的问题
     * @param properties 配置信息，springboot 从配置文件获取, 自动注入
     * @return 批量工厂类
     */
    @Bean
    public ConsumerFactory<String,Object> consumerFactory(KafkaProperties properties){
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        ConsumerFactory<String,Object> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProperties);
        return consumerFactory;
    }
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true); // 开启批量监听
        return factory;
    }
}