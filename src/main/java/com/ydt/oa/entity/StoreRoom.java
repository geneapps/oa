package com.ydt.oa.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.giro.common.entity.StringUUIDEntity;

/**
 * 用户数据库持久化Bean
 * 
 * @author huchuqiao
 * 
 */
@Entity
@Table(name = "oa_storeroom")
// 库房表
public class StoreRoom extends StringUUIDEntity implements Serializable {

	private static final long serialVersionUID = -1468296967149240306L;
	public static final int STOREROOM_VALID = 1;
	public static final int STOREROOM_INVALID = 2;
	public static final int STOREROOM_DELETE = 99;
	private String name;        //库房名
	private String description; // 库房描述
	private User user; // 管理员
	private String createTime; // 创建时间
	private int status; // 状态
    private Project project;  //项目
    
    @OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {

		return createTime;
	}

	public void setCreateTime(String createTime) {

		this.createTime = createTime;
	}

	
	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}




	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {

		this.status = status;
	}

	
}
