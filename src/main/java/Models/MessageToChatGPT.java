package Models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageToChatGPT {
    String model;
    List<Message> messages;
    @Builder.Default
    int max_tokens = 60;
}
