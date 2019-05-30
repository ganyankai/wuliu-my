package com.zrytech.framework.app.dto.focus;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MyFocusPersonAddDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864120243361640036L;

	/** 被关注人的id */
	@NotNull(message = "被关注人的Id不能为空")
	private Integer beFocuserId;

}
