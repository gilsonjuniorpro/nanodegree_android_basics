package com.news.br.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gilsonjuniorpro on 5/3/17.
 */

public class Guide implements Parcelable {

    private String status;
    private String userTier;
    private int total;
    private int startIndex;
    private int pageSize;
    private int currentPage;
    private int pages;
    private String orderBy;
    private String id;
    private String type;
    private String sectionId;
    private String sectionName;
    private String webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String apiUrl;
    private boolean isHosted;

    public Guide(String status, String userTier, int total, int startIndex, int pageSize,
                 int currentPage, int pages, String orderBy, String id, String type, String sectionId,
                 String sectionName, String webPublicationDate, String webTitle, String webUrl,
                 String apiUrl, boolean isHosted) {
        this.status = status;
        this.userTier = userTier;
        this.total = total;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pages = pages;
        this.orderBy = orderBy;
        this.id = id;
        this.type = type;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.isHosted = isHosted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public boolean isHosted() {
        return isHosted;
    }

    public void setHosted(boolean hosted) {
        isHosted = hosted;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.userTier);
        dest.writeInt(this.total);
        dest.writeInt(this.startIndex);
        dest.writeInt(this.pageSize);
        dest.writeInt(this.currentPage);
        dest.writeInt(this.pages);
        dest.writeString(this.orderBy);
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.sectionId);
        dest.writeString(this.sectionName);
        dest.writeString(this.webPublicationDate);
        dest.writeString(this.webTitle);
        dest.writeString(this.webUrl);
        dest.writeString(this.apiUrl);
        dest.writeByte(this.isHosted ? (byte) 1 : (byte) 0);
    }

    public Guide() {
    }

    protected Guide(Parcel in) {
        this.status = in.readString();
        this.userTier = in.readString();
        this.total = in.readInt();
        this.startIndex = in.readInt();
        this.pageSize = in.readInt();
        this.currentPage = in.readInt();
        this.pages = in.readInt();
        this.orderBy = in.readString();
        this.id = in.readString();
        this.type = in.readString();
        this.sectionId = in.readString();
        this.sectionName = in.readString();
        this.webPublicationDate = in.readString();
        this.webTitle = in.readString();
        this.webUrl = in.readString();
        this.apiUrl = in.readString();
        this.isHosted = in.readByte() != 0;
    }

    public static final Creator<Guide> CREATOR = new Creator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel source) {
            return new Guide(source);
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[size];
        }
    };
}
