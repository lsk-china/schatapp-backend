package indi.midreamsheep.schatapp.backend.service.chat.group.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.midreamsheep.schatapp.backend.api.chat.exception.ChatException;
import indi.midreamsheep.schatapp.backend.chat.account.SChatUser;
import indi.midreamsheep.schatapp.backend.chat.message.ChatType;
import indi.midreamsheep.schatapp.backend.dao.mysql.handle.message.MessageMapperHandler;
import indi.midreamsheep.schatapp.backend.dao.mysql.mapper.GroupMapper;
import indi.midreamsheep.schatapp.backend.dao.mysql.mapper.GroupUserMapMapper;
import indi.midreamsheep.schatapp.backend.protocol.data.result.ResultEnum;
import indi.midreamsheep.schatapp.backend.service.chat.group.GroupChatService;
import indi.midreamsheep.schatapp.backend.service.chat.individual.manager.IndividualChatManager;
import indi.midreamsheep.schatapp.backend.service.dao.mysql.GroupUserMapping;
import indi.midreamsheep.schatapp.backend.service.dao.mysql.Message;
import indi.midreamsheep.schatapp.backend.service.service.chat.individual.IndividualChatEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static indi.midreamsheep.schatapp.backend.util.entity.TimeUtil.now;

/**
 * Implements group chat logics
 * @author lsk
 */
@Service
public class GroupChatServiceImpl  implements GroupChatService {
    // Table "message" accessor
    @Resource
    private MessageMapperHandler messageMapperHandler;

    // Table "group" accessor
    @Resource
    private GroupMapper groupMapper;

    // Table "group_user_mapping" accessor
    @Resource
    private GroupUserMapMapper groupUserMapMapper;

    // used to send a message to group message
    @Resource
    private IndividualChatManager individualChatManager;

    @Override
    public void enduranceMessage(SChatUser user, Message msg) {
        // Generate the message id with snowflake id generator
        msg.setId(IdUtil.getSnowflake().nextId());
        msg.setMessageTime(now());
        msg.setMessageFrom(user.getId());
        messageMapperHandler.insertMessage(msg);
    }

    @Override
    public void send(Message msg) {
        // ensure group message
        if (msg.getType() != ChatType.GROUP.getId()) {
            throw new ChatException("not a group message", ResultEnum.ERROR);
        }
        // 1. query group members
        Long groupId = msg.getMessageTo();
        List<Long> uids = groupUserMapMapper.queryGroupMembers(groupId);
        // remove the message sender so we won't send the message back
        uids.remove(msg.getMessageFrom());
        // ensure the group exists and have member
        if (uids == null || uids.size() == 0) {
            throw new ChatException("No such group or empty group", ResultEnum.ERROR);
        }

        // 2. send message to online members
        for (Long userId : uids) {
            IndividualChatEntity individualChat = individualChatManager.getIndividualChat(userId);
            if (individualChat == null) {
                continue;
            }

        }
    }
}
