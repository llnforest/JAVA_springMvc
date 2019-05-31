package com.common.tree;

import java.util.ArrayList;
import java.util.List;

/**  
 * 树形结构节点对象
 * @author:wangzhen
 * @version:V1.0
 * 2018年12月25日  
 */
public class Node {
	
	/** 节点ID*/
	private String id;
	/** 上级节点ID*/
	private String parentId;
	/** 节点名称*/
    private String title;
    /** 子节点集合*/
    private List<Node> children = new ArrayList<Node>();
    /** 复选框集合*/
    private CheckArr checkArr = new CheckArr();
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public CheckArr getCheckArr() {
		return checkArr;
	}
	public void setCheckArr(CheckArr checkArr) {
		this.checkArr = checkArr;
	}

}
