package cn.zzcai.tools;

import java.util.List;


/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月6日 下午12:02:52
 * @remark TODO
 */
public class PageBean<T> {
	private int pageNo = 1;
	private int pageSize = 8;
	private int totalCount;
	private int totalPages;
	private List<T> pageList;

	public PageBean(int pageNo, int pageSize, int totalCount, int totalPages, List<T> pageList) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
		this.pageList = pageList;
	}

	public PageBean() {
		super();
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else if (pageNo > totalPages && totalPages != 0) {
			this.pageNo = totalPages;
		} else {
			this.pageNo = pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if (totalCount % pageSize == 0) {
			this.totalPages = totalCount / pageSize;
		} else {
			this.totalPages = totalCount / pageSize + 1;
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

}
