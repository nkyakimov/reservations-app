package com.app.reservation.configuration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DateDeserializer extends StdDeserializer<OffsetDateTime> {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    public static OffsetDateTime deserialize(String text) {
        if (!StringUtils.hasText(text)) {
            return null;
        }

        try {
            return OffsetDateTime.ofInstant(
                    new SimpleDateFormat(CommonConfig.TIME_FORMAT).parse(text).toInstant(),
                    ZoneId.systemDefault());
        } catch (ParseException e) {
            return null;
        }
    }

    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return deserialize(p.getText());
    }

}
