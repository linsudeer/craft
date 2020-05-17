package com.czht.smartpark.provider.uac.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class User modify pwd dto.
 *
 * @author paascloud.net@gmail.com
 */
@Data
public class UserModifyPwdDto implements Serializable {

	private static final long serialVersionUID = -3933378415083541145L;


	@ApiModelProperty(value = "原始密码")
	private String oldPassword;

	/**
	 * 新密码
	 */
	@ApiModelProperty(value = "新密码")
	private String newPassword;

	/**
	 * 确认密码
	 */
	@ApiModelProperty(value = "确认密码")
	private String confirmPwd;

}
