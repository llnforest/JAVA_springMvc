package com.model.system;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * AbstractSysTable entity provides the base persistence definition of the
 * SysTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_table")
@Repository
public class SysTable extends com.model.BaseModel implements
		java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String tableId;
	private String tableName;
	private String columnName;
	private String remarkName;
	private String dictCode;
	private Long logOrder;
	private Byte isFixedLog;
	private Byte isLog;
	private Byte isSearch;
	private String searchType;
	private Long searchOrder;
	private String searchTime;
	private String searchSql;
	private String searchName;
	private String listWidth;
	private String listTpl;
	private String listJsfunc;
	private String listStyle;
	private String listName;
	private Byte isList;
	private Byte listHide;
	private Byte listInput;
	private Byte listSwitch;
	private Long listOrder;

	// Constructors

	/** default constructor */
	public SysTable() {
	}

	/** full constructor */
	public SysTable(
			String tableName, String columnName, String remarkName,
			String dictCode, Long logOrder, Byte isFixedLog, Byte isLog,
			Byte isSearch, String searchType, Long searchOrder,
			String searchTime, String searchSql, String searchName) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.remarkName = remarkName;
		this.dictCode = dictCode;
		this.logOrder = logOrder;
		this.isFixedLog = isFixedLog;
		this.isLog = isLog;
		this.isSearch = isSearch;
		this.searchType = searchType;
		this.searchOrder = searchOrder;
		this.searchTime = searchTime;
		this.searchSql = searchSql;
		this.searchName = searchName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TABLE_ID", unique = true, nullable = false, length = 32)
	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Column(name = "TABLE_NAME", length = 40)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "COLUMN_NAME", length = 40)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "REMARK_NAME", length = 40)
	public String getRemarkName() {
		return this.remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	@Column(name = "DICT_CODE", length = 40)
	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	@Column(name = "LOG_ORDER", precision = 10, scale = 0)
	public Long getLogOrder() {
		return this.logOrder;
	}

	public void setLogOrder(Long logOrder) {
		this.logOrder = logOrder;
	}

	@Column(name = "IS_FIXED_LOG", precision = 2, scale = 0)
	public Byte getIsFixedLog() {
		return this.isFixedLog;
	}

	public void setIsFixedLog(Byte isFixedLog) {
		this.isFixedLog = isFixedLog;
	}

	@Column(name = "IS_LOG", precision = 2, scale = 0)
	public Byte getIsLog() {
		return this.isLog;
	}

	public void setIsLog(Byte isLog) {
		this.isLog = isLog;
	}

	@Column(name = "IS_SEARCH", precision = 2, scale = 0)
	public Byte getIsSearch() {
		return this.isSearch;
	}

	public void setIsSearch(Byte isSearch) {
		this.isSearch = isSearch;
	}

	@Column(name = "SEARCH_TYPE", length = 40)
	public String getSearchType() {
		return this.searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@Column(name = "SEARCH_ORDER", precision = 10, scale = 0)
	public Long getSearchOrder() {
		return this.searchOrder;
	}

	public void setSearchOrder(Long searchOrder) {
		this.searchOrder = searchOrder;
	}

	@Column(name = "SEARCH_TIME", length = 20)
	public String getSearchTime() {
		return this.searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	@Column(name = "SEARCH_SQL", length = 120)
	public String getSearchSql() {
		return this.searchSql;
	}

	public void setSearchSql(String searchSql) {
		this.searchSql = searchSql;
	}
	
	@Column(name = "SEARCH_NAME", length = 40)
	public String getSearchName() {
		return this.searchName;
	}
	
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	@Column(name = "IS_LIST", precision = 2, scale = 0)
	public Byte getIsList() {
		return this.isList;
	}
	
	public void setIsList(Byte isList) {
		this.isList = isList;
	}
	
	@Column(name = "LIST_ORDER", precision = 10, scale = 0)
	public Long getListOrder() {
		return this.listOrder;
	}
	
	public void setListOrder(Long listOrder) {
		this.listOrder = listOrder;
	}
	
	@Column(name = "LIST_WIDTH", length = 10)
	public String getListWidth() {
		return this.listWidth;
	}
	
	public void setListWidth(String listWidth) {
		this.listWidth = listWidth;
	}
	
	@Column(name = "LIST_TPL", length = 40)
	public String getListTpl() {
		return this.listTpl;
	}
	
	public void setListTpl(String listTpl) {
		this.listTpl = listTpl;
	}
	
	@Column(name = "LIST_STYLE", length = 100)
	public String getListStyle() {
		return this.listStyle;
	}
	
	public void setListStyle(String listStyle) {
		this.listStyle = listStyle;
	}
	
	@Column(name = "LIST_JSFUNC", length = 40)
	public String getListJsfunc() {
		return this.listJsfunc;
	}
	
	public void setListJsfunc(String listJsfunc) {
		this.listJsfunc = listJsfunc;
	}
	
	@Column(name = "LIST_NAME", length = 40)
	public String getListName() {
		return this.listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}

	@Column(name = "LIST_HIDE", precision = 2, scale = 0)
	public Byte getListHide() {
		return this.listHide;
	}

	public void setListHide(Byte listHide) {
		this.listHide = listHide;
	}
	
	@Column(name = "LIST_INPUT", precision = 2, scale = 0)
	public Byte getListInput() {
		return this.listInput;
	}
	
	public void setListInput(Byte listInput) {
		this.listInput = listInput;
	}
	
	@Column(name = "LIST_SWITCH", precision = 2, scale = 0)
	public Byte getListSwitch() {
		return this.listSwitch;
	}
	
	public void setListSwitch(Byte listSwitch) {
		this.listSwitch = listSwitch;
	}
}