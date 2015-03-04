package com.ydt.oa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.MD5;
import com.giro.common.util.StringUtils;
import com.giro.common.util.ValidateUtils;


/**
 * 用户数据库持久化Bean
 * @author caochun
 *
 */
@Entity
@Table(name = "oa_user")
public class User extends StringUUIDEntity implements Serializable{

	private static final long serialVersionUID = 6907877109269792483L;
	
	public static final int USER_VALID = 1;
	public static final int USER_INVALID = 2;
	public static final int USER_DELETE = 99;
	
	private String password;
	private String userNo;  // 员工编号
	private String realName; // 姓名
	private String mobile; //  手机号
	private String birthday;
	private int sex ;
	private String address;
	private String onBoardDate;
	private String email;
	private String lastLogin;
	private String remark;
	private int status;
	private int age;
	private String regTime;
	private Role role;
	private Department department;
	
	public int getStatus() {
	
		return status;
	}

	
	public void setStatus(int status) {
	
		this.status = status;
	}

	public String getUserNo() {
	
		return userNo;
	}

	public void setUserNo(String userNo) {
	
		this.userNo = userNo;
	}
	
	public String getRealName() {
	
		return realName;
	}
	
	public void setRealName(String realName) {
	
		this.realName = realName;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirthday() {
	
		return birthday;
	}
	
	public void setBirthday(String birthday) {
	
		this.birthday = birthday;
	}
	
	public int getSex() {
	
		return sex;
	}
	
	public void setSex(int sex) {

		this.sex = sex;
	}
	
	public String getAddress() {
	
		return address;
	}
	
	public void setAddress(String address) {
	
		this.address = address;
	}
	
	public String getOnBoardDate() {
	
		return onBoardDate;
	}
	
	public void setOnBoardDate(String onBoardDate) {
	
		this.onBoardDate = onBoardDate;
	}
	
	public String getEmail() {
	
		return email;
	}
	
	public void setEmail(String email) {
	
		this.email = email;
	}
	
	public String getLastLogin() {
	
		return lastLogin;
	}
	
	public void setLastLogin(String lastLogin) {
	
		this.lastLogin = lastLogin;
	}

	public String getRemark() {
	
		return remark;
	}

	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	
	public int getAge() {
	
		return age;
	}
	
	public void setAge(int age) {
	
		this.age = age;
	}

	public String getPassword() {
		if(password!=null && password.length()!=32){
			return MD5.getMD5(password);
		}else{
			return password;
		}		
	}

	public void setPassword(String password) {
	
		this.password = password;
	}
	
	public String getRegTime() {
		
		if(regTime==null || regTime.equals("")){
			return DateUtils.getNowDateStr();
		}
		else return regTime;
	}
	
	public void setRegTime(String regTime) {
	
		this.regTime = regTime;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToOne(
			cascade = { CascadeType.REFRESH },
			targetEntity = Department.class, fetch = FetchType.EAGER)
	@JoinColumn
	public Department getDepartment() {
	
		return department;
	}
	
	public void setDepartment(Department department) {
	
		this.department = department;
	}

	public void validate() throws GiroException{
		if(!ValidateUtils.checkLength(userNo,5,20)){
			throw new GiroException(-1,"用户名必须是5~20位");
		}
		if(!ValidateUtils.checkId(userNo)){
			throw new GiroException(-1,"用户名必须是字符、数字、下划线，且只能以字母开始");
		}
		
		
		if(!StringUtils.isNull(mobile)){
		
			if(!ValidateUtils.checkMobile(mobile)){
				throw new GiroException(-1,"手机号格式不正确");
			}
		}
		
		if(!StringUtils.isNull(email)){
			if(!ValidateUtils.checkEmail(email, 100)){
				throw new GiroException(-1,"Email格式不正确");
			}
		}
	}
	
	/**
	 * 能否访问
	 * @param actionCode
	 * @return
	 */
	public boolean canAccess(int actionCode){
		System.out.println(actionCode);
		try{
			Set<OaAction> actions = getRole().getActions();
			
			for(OaAction action:actions){
				
				System.out.println(action.getActionValue());
				
				if(action.getActionValue()==actionCode){
					return true;
				}
			}
		}catch(Exception e){
			
		}

		return false;
	}
	
	public boolean canAccess(String url){
		
		HashSet<String> urls =  canAccessUrl();
		
		if(urls.contains(url)){
			return true;
		}else{
			return false;
		}

	}
	
	public HashSet<String> canAccessUrl(){
		HashSet<String> urlSet = new HashSet<String>();
		
		try{
			Set<OaAction> actions = getRole().getActions();
			
			for(OaAction action:actions){
				List<ActionUrl> urls = action.getUrls();
				for(ActionUrl url:urls){
					urlSet.add(url.getUrl());
				}
				
			}
		}catch(Exception e){
			
		}

		return urlSet;
	}
	
	/**
	 * 获取当前用户所在项目
	 * @return
	 */
	@Transient
	public Department getUserProject(){	
		return findDepartment(getDepartment(),Department.DEP_TYPE_PROJECT);		
	}
	
	/**
	 * 获取项目仓库
	 * @return
	 */
	@Transient
	public Department getProjectHouse(){
		
		Department depart = getUserProject();
		
		List<Department> departs =  depart.getChilds();
		
		for(Department d:departs){
			if(d.getDepType()==Department.DEP_TYPE_HOUSE){
				return d;
			}
		}
		return null;
	}
	
	@Transient
	public List<Department> getAllChilds(){
		
		List<Department> childs = new ArrayList<Department>();
		if(this.department.getDepType() == 1 ||  this.department.getDepType() == 2){
			
			Department depart = getUserProject();
			childs.add(depart);
		}
		
		childs.add(department);
		findChilds(childs,this.department);
		return childs;
	}
	
	private void findChilds(List<Department> childs,Department depart){

		childs.addAll(depart.getChilds());
		for(Department d:depart.getChilds()){
			findChilds(childs,d);
		}

	}
	
	private Department findDepartment(Department depart,int departType){
		
		if(depart==null) return null;
		
		if(depart.getDepType()==departType){
			return depart;
		}else{
			return findDepartment(depart.getParent(),departType);
		}
	}



	
	
}
