package com.app.reservation.configuration;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

@Component
public class DateSerializer extends StdSerializer<OffsetDateTime> {

    public DateSerializer() {
        this(null);
    }

    public DateSerializer(Class<OffsetDateTime> t) {
        super(t);
    }

    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.format(DateTimeFormatter.ofPattern(CommonConfig.TIME_FORMAT)));
    }

}
