package cn.swpu.entity;

import java.sql.*;

import cn.swpu.util.DbUtil;

/**
 * 7.29 修改pojo到数据库的映射不再是简单数据类型
 * @author zhangbo
 *
 */
public class Order {
	private String order_id;      
	private String catagory;		 //直接写死在里面吧  不用数据字典了
	private String describe;
	private float order_money;
	private User accept_person;
	private User send_person;
	private String order_status;    //直接写死在里面吧  不用数据字典了
	private String order_date;			
	private String finish_date;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	
	public float getOrder_money() {
		return order_money;
	}
	public void setOrder_money(float order_money) {
		this.order_money = order_money;
	}

	public User getAccept_person() {
		return accept_person;
	}
	public void setAccept_person(User accept_person) {
		this.accept_person = accept_person;
	}
	public User getSend_person() {
		return send_person;
	}	
	public void setSend_person(User send_person) {
		this.send_person = send_person;
	}
	


	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}
	
	

	
	
	

}
