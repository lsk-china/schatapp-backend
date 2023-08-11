package indi.midreamsheep.schatapp.backend.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.midreamsheep.schatapp.backend.service.dao.mysql.GroupUserMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Table "group_user_mapping" Accessor
 * @author lsk
 */
@Mapper
public interface GroupUserMapMapper extends BaseMapper<GroupUserMapping> {
    /**
     * query group members' uids by group id
     * @param groupId group id
     * @return list of group members' uid
     */
    @Select("select user_id from group_user_mapper where group_id=#{group_id}")
    List<Long> queryGroupMembers(@Param("group_id") Long groupId);
}
