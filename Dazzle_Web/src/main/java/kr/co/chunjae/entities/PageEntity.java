package kr.co.chunjae.entities;

import kr.co.digigroove.commons.entities.PageNavigationEntity;

/**
 * Created by sangyong on 11/13/14.
 */
public class PageEntity extends PageNavigationEntity {

private static final String DEFAULT_LABEL_FIRST_PAGE = "<img src='/resources/img/icon-first.gif' alt='첫페이지'>";
  private static final String DEFAULT_LABEL_LAST_PAGE  = "<img src='/resources/img/icon-last.gif' alt='이전페이지'>";
  private static final String DEFAULT_LABEL_GROUP_PREV = "<img src='/resources/img/icon-prev.gif' alt='다음페이지'>";
  private static final String DEFAULT_LABEL_GROUP_NEXT = "<img src='/resources/img/icon-next.gif' alt='마지막페이지'>";

  private String searchKey;
  private String searchText;
  private String searchValue;
  private String startDate;
  private String endDate;
  private String sortBy;
  private String sortOrder;

  public PageEntity ( final long currentPageParam, final int pageGroupSizeParam, final int pageListSizeParam ) {
    super( currentPageParam, pageGroupSizeParam, pageListSizeParam );

    setLabelsInit();
  }
  
  public PageEntity ( final long currentPageParam ) {
    super( currentPageParam );

    setLabelsInit();
  }

  private void setLabelsInit() {
	super.setLabelFirstPage(DEFAULT_LABEL_FIRST_PAGE);
	super.setLabelLastPage(DEFAULT_LABEL_LAST_PAGE);
	super.setLabelGroupPrevious(DEFAULT_LABEL_GROUP_PREV);
	super.setLabelGroupNext(DEFAULT_LABEL_GROUP_NEXT);
  }

	public String getSearchKey() {
		return searchKey;
	}
	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}