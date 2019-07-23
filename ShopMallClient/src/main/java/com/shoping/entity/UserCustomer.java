package com.shoping.entity;

/**
 * 客户实体类
 * @author zhuxu
 *
 */
public class UserCustomer {

	String customerId;              //用户编号
	String customerName;            //用户姓名
	int age;                        //年龄
	String phonenumber;             //手机号
	String address;                 //住址
	String account;                 //账号
	String password;                //密码
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "customer [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age
				+ ", phonenumber=" + phonenumber + ", address=" + address + ", account=" + account + ", password="
				+ password + "]";
	}
	
	
	
	
}
