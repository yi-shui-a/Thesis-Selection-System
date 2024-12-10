package duan.thesis_select_system.entity;

import java.io.Serializable;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-14
 */
@Data
public class Root implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private String name;

    private String password;

    private String tel;

    private String email;

    private String role;


}
