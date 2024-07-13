package Models;

import lombok.Data;

@Data
public class ChoicesItem {
    int index;
    String logprobs;
    String finish_reason;
    Message message;
}
