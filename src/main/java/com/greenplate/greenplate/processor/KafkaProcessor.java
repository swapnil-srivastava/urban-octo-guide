package com.greenplate.greenplate.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KafkaProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProcessor.class);
    
    @Autowired
    public void processBackToTheFuture(StreamsBuilder builder) {
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
    
        KStream<String, String> quotes = builder.stream("back-to-the-future-topic", 
            Consumed.with(stringSerde, stringSerde));
    
        // Assuming the input is in the format "Character: Quote"
        KTable<String, Long> characterQuoteCounts = quotes
            .peek((key, value) -> logger.info("Received quote - Key: {}, Value: {}", key, value))
            .mapValues(value -> value.split(":", 2)[0].trim()) // Extract character name
            .groupBy((key, character) -> character)
            .count();
    
        // Output the count of quotes per character
        characterQuoteCounts.toStream()
            .to("character-quote-counts", Produced.with(stringSerde, longSerde));
    
        // Word frequency analysis
        KTable<String, Long> wordFrequency = quotes
            .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
            .groupBy((key, word) -> word)
            .count();
    
        // Output word frequency
        wordFrequency.toStream()
            .to("word-frequency", Produced.with(stringSerde, longSerde));
    }
}
