package me.otmane.assignments.springboot.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import me.otmane.assignments.springboot.entities.Category;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

public class ProductCategorySerializer extends JsonObjectSerializer<Category> {

    @Override
    protected void serializeObject(Category category, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeNumberField("pk", category.getPk());
        jsonGenerator.writeStringField("name", category.getName());
    }
}
