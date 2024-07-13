package Models;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTChatResponse {
    String id;
    String object;
    Long created;
    String model;
    String system_fingerprint;
    List<ChoicesItem> choices;
    Usage usage;
}
