package com.lte.admin.entity;

import java.util.List;

public class DictBean {

	private Integer id;
	
	private String code;
	
	private String value;
	
	private String name;
	
	private String description;
	
	private String extend;
	
	private String zxbj;

	private List<DictBean> dictList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getZxbj() {
		return zxbj;
	}

	public void setZxbj(String zxbj) {
		this.zxbj = zxbj;
	}

	public List<DictBean> getDictList() {
		return dictList;
	}

	public void setDictList(List<DictBean> dictList) {
		this.dictList = dictList;
	}
	
	public DictBean clone(){
		DictBean retBean = new DictBean();
		retBean.setId(this.id);
		retBean.setCode(this.code);
		retBean.setName(this.name);
		retBean.setValue(this.value);
		retBean.setDescription(this.description);
		retBean.setZxbj(this.zxbj);
		retBean.setExtend(this.extend);
		return retBean;
	}
}
