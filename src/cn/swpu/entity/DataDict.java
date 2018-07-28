package cn.swpu.entity;
/**
 * 数据字典表
 * @author zhangbo
 *
 */
public class DataDict {
	private int dictId;         //id
	private String catagory_id;    //类别id
	private int dict_type_code; //类别名
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}
	public String getCatagory_id() {
		return catagory_id;
	}
	public void setCatagory_id(String catagory_id) {
		this.catagory_id = catagory_id;
	}
	public int getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(int dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
}
