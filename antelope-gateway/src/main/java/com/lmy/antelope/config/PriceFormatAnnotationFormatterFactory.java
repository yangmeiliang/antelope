package com.lmy.antelope.config;

import lombok.AllArgsConstructor;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangmeiliang
 */
@Component
@AllArgsConstructor
public class PriceFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<PriceStyle> {

    private PriceFormatter priceFormatter;

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<>();
        fieldTypes.add(Integer.class);
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(PriceStyle annotation, Class<?> fieldType) {
        return priceFormatter;
    }

    @Override
    public Parser<?> getParser(PriceStyle annotation, Class<?> fieldType) {
        return priceFormatter;
    }
}
