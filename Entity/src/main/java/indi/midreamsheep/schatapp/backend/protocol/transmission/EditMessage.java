package indi.midreamsheep.schatapp.backend.protocol.transmission;

import indi.midreamsheep.schatapp.backend.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMessage {
    private long messageTo;
    private String message;
    private long editId;

    @Override
    public String toString() {
        return JsonUtil.getBeanToJson(this);
    }
}
