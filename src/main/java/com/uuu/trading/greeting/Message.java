package com.uuu.trading.greeting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class Message {
    @Value("-1")
    private int id;
    @Value("default user")
    private String name;
}