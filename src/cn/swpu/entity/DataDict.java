package cn.swpu.entity;
/**
 * �����ֵ��
 * @author zhangbo
 *
 */
public class DataDict {
	private int dictId;         //id
	private String catagory_id;    //���id
	private int dict_type_code; //�����
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
