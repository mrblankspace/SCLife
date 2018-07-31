package cn.swpu.entity;

public class Message {
	private Integer id; 
	private String content;
	private User to_person;
	private String status;
	private String date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getTo_person() {
		return to_person;
	}
	public void setTo_person(User to_person) {
		this.to_person = to_person;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
