package indi.midreamsheep.schatapp.backend.service.chat.individual.api;

import indi.midreamsheep.schatapp.backend.chat.account.SChatUser;
import indi.midreamsheep.schatapp.backend.protocol.transmission.SendMessage;
import indi.midreamsheep.schatapp.backend.service.dao.mysql.Message;

public interface IndividualChatSendService {
    Message endurance(SChatUser user, SendMessage data);
    void send(SChatUser user, Message data);
}
