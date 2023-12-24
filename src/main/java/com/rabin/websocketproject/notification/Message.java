package com.rabin.websocketproject.notification;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String text;
    private String to;
}
