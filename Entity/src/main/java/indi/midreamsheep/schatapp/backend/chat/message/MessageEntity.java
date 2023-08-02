package indi.midreamsheep.schatapp.backend.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    private String toId;
    private String dataJson;

}
