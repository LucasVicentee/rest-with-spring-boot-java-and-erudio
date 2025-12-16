package com.LucasVicentee.serialization.converter;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class Yamljackson2HttpMenssageConverter extends AbstractJackson2HttpMessageConverter {

    protected Yamljackson2HttpMenssageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/yaml"));
    }
}
