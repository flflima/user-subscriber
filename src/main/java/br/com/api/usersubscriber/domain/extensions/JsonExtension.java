package br.com.api.usersubscriber.domain.extensions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JsonExtension {
  private ObjectMapper objectMapper;

  @Autowired
  public JsonExtension(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public <T> String toJsonString(T object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  public <T> T toObject(String json, Class<T> clazz) throws IOException {
    return objectMapper.readValue(json, clazz);
  }

  public <T> List<T> toObjectList(String json, Class<T> clazz) throws IOException {
    return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
  }
}
