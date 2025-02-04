package indi.midreamsheep.schatapp.backend.chat.individual.delete;

import indi.midreamsheep.schatapp.backend.api.aop.access.annotation.ChatAccessChecker;
import indi.midreamsheep.schatapp.backend.api.aop.access.annotation.ChatExceptionHandler;
import indi.midreamsheep.schatapp.backend.api.chat.handler.annotation.ChatHandler;
import indi.midreamsheep.schatapp.backend.api.scan.inter.ChatHandlerInter;
import indi.midreamsheep.schatapp.backend.chat.ChatMessage;
import indi.midreamsheep.schatapp.backend.chat.account.SChatUser;
import indi.midreamsheep.schatapp.backend.chat.message.ChatType;
import indi.midreamsheep.schatapp.backend.protocol.ChatTransmission;
import indi.midreamsheep.schatapp.backend.protocol.TransmissionEnum;
import indi.midreamsheep.schatapp.backend.protocol.data.result.Result;
import indi.midreamsheep.schatapp.backend.protocol.data.result.ResultEnum;
import indi.midreamsheep.schatapp.backend.protocol.transmission.DeleteMessage;
import indi.midreamsheep.schatapp.backend.service.chat.ChannelManager;
import indi.midreamsheep.schatapp.backend.service.chat.individual.api.IndividualChatDeleteService;
import indi.midreamsheep.schatapp.backend.util.json.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ChatHandler(type = ChatType.INDIVIDUAL, mapping = "DELETE")
public class individualChatDeleteHandler implements ChatHandlerInter {

    @Resource
    private ChannelManager channelManager;

    @Resource
    private IndividualChatDeleteService individualChatDeleteService;

    @Override
    @ChatAccessChecker(check = TransmissionEnum.DELETE_MESSAGE)
    @ChatExceptionHandler
    public ChatTransmission handle(ChannelHandlerContext ctx, ChatMessage data) {
        //获取用户信息
        SChatUser user = channelManager.getUser(ctx.channel());
        //获取消息信息
        DeleteMessage deleteMessage = JsonUtil.getJsonToBean(data.getData(), DeleteMessage.class);
        //权限校验
        individualChatDeleteService.check(user, deleteMessage);
        //删除消息
        individualChatDeleteService.delete(user,individualChatDeleteService.endurance(user, deleteMessage));
        return new ChatTransmission(data.getId(), TransmissionEnum.DELETE_MESSAGE, new Result(ResultEnum.SUCCESS));
    }
}
