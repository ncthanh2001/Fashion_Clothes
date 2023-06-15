package com.shop.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;


public enum Infor {
    REGISTER("Xin chào","Cảm Ơn Bạn đã Đăng Ký thành viên của Shop"),
    FORGOTPASS("RESETPASS","Mật khẩu mới của bạn là ");
    private String tittle;
    private String content;

    Infor(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //    PAGE_001("PAGE_001", "Login", "/login"),
//    PAGE_002("PAGE_002", "Add new user", "/addUser"),
//    PAGE_003("PAGE_003", "List user", "/listUser");
//
//    private String pageID;
//    private String pageTitle;
//    private String pageURL;
//
//    /**
//     * constructor
//     *
//     * @param pageID
//     * @param pageTitle
//     * @param pageURL
//     */
//    private Infor(String pageID, String pageTitle, String pageURL) {
//        this.pageID = pageID;
//        this.pageTitle = pageTitle;
//        this.pageURL = pageURL;
//    }
//
//    // getter & setter
//    public String getPageID() {
//        return pageID;
//    }
//
//    public void setPageID(String pageID) {
//        this.pageID = pageID;
//    }
//
//    public String getPageTitle() {
//        return pageTitle;
//    }
//
//    public void setPageTitle(String pageTitle) {
//        this.pageTitle = pageTitle;
//    }
//
//    public String getPageURL() {
//        return pageURL;
//    }
//
//    public void setPageURL(String pageURL) {
//        this.pageURL = pageURL;
//    }
}
