package cn.swpu.entity;
/**
 * ¶©µ¥ΚµΜεΐΰ
 * @author zhangbo
 *
 */
public class Order {
	private int order_id;      
	private int catagory_id;
	private String order_describe;
	private float order_money;
	private int accept_id;
	private int send_id;
	private int order_status;
	private String order_date;
	private String finish_date;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getCatagory_id() {
		return catagory_id;
	}
	public void setCatagory_id(int catagory_id) {
		this.catagory_id = catagory_id;
	}
	public String getOrder_describe() {
		return order_describe;
	}
	public void setOrder_describe(String order_describe) {
		this.order_describe = order_describe;
	}
	public float getOrder_money() {
		return order_money;
	}
	public void setOrder_money(float order_money) {
		this.order_money = order_money;
	}
	public int getAccept_id() {
		return accept_id;
	}
	public void setAccept_id(int accept_id) {
		this.accept_id = accept_id;
	}
	public int getSend_id() {
		return send_id;
	}
	public void setSend_id(int send_id) {
		this.send_id = send_id;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
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
