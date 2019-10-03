package br.com.fernando.blog.exeptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErroMessage {

    private LocalDateTime timestamp;
    private String message;

}
