package com.zrytech.framework.app.dto;

import com.zrytech.framework.app.constants.RegExConstants;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Setter
@Getter
@ApiModel(value = "修改密码dto")
public class PasswordDto implements Serializable {

    /** 原密码 */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegExConstants.PASSWORD, message = RegExConstants.PASSWORDERR_MSG)
    private String oldPassword;

    /** 新密码 */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegExConstants.PASSWORD, message = RegExConstants.PASSWORDERR_MSG)
    private String newPassword;
    
    /** 确认密码 */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegExConstants.PASSWORD, message = RegExConstants.PASSWORDERR_MSG)
    private String confirmPassword;
}
