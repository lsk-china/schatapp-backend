package indi.midreamsheep.schatapp.backend.service.dao.mysql;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table group_user_mapping structure
 * @author lsk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("group_user_mapping")
public class GroupUserMapping {
    /**
     * Field id
     * this field have noting to do with the function of this table
     * just keeps records unique
     */
    @TableId("id")
    private Long id;

    /**
     * Field group_id
     * Group ID
     * field "id" in table "group"
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * Field user_id
     * User ID
     * field "id" in table "user"
     */
    @TableField("user_id")
    private Long userId;
}
