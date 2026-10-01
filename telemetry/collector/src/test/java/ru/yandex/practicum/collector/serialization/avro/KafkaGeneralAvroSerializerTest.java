package ru.yandex.practicum.collector.serialization.avro;

import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaGeneralAvroSerializerTest {
    private final KafkaGeneralAvroSerializer serializer = new KafkaGeneralAvroSerializer();

    @Test
    void shouldSerializeAvroRecord() throws Exception {
        SwitchSensorAvro source = SwitchSensorAvro.newBuilder().setState(true).build();

        byte[] bytes = serializer.serialize("test-topic", source);

        SpecificDatumReader<SwitchSensorAvro> reader = new SpecificDatumReader<>(SwitchSensorAvro.class);
        SwitchSensorAvro restored = reader.read(null, DecoderFactory.get().binaryDecoder(bytes, null));
        assertThat(restored.getState()).isTrue();
    }

    @Test
    void shouldReturnEmptyArrayForNullValue() {
        assertThat(serializer.serialize("test-topic", null)).isEmpty();
    }
}
