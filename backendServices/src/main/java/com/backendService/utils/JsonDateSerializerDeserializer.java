package com.backendService.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class JsonDateSerializerDeserializer {

    public static class Serializer extends JsonSerializer<Date> {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

    public static class Deserializer extends JsonDeserializer<Date> {
        private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String dateString = jsonParser.getText();
            try {
                return dateFormat1.parse(dateString);
            } catch (ParseException e1) {
                try {
                    return dateFormat2.parse(dateString);
                } catch (ParseException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }
}