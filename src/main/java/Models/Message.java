package Models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Message {
    @Builder.Default
    String role = "user";
    @NonNull
    String content;
}
