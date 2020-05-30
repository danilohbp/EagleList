package com.example.estudando.model;

import java.util.Arrays;
import java.util.List;

public class Emails {

    public static List<Email> fakeEmails(){
        return Arrays.asList(
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .build(),
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .setStared(true)
                        .setUnread(true)
                        .build(),
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .setStared(false)
                        .build(),
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .build(),
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .setStared(true)
                        .build(),
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas principais para aumentar as suas habilidades em programação Java.")
                        .setPreview("Olá Danilo, você precisa ver esse site.")
                        .build()
        );
    }
}
