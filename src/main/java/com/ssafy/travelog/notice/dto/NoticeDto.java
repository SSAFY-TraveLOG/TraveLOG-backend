package com.ssafy.travelog.notice.dto;

public class NoticeDto {
    static int noticeNo;
    static String subject;
    static String content;
    static String registerTime;
    static String modifiedTime;

    public static int getNoticeNo() {
        return noticeNo;
    }

    public static void setNoticeNo(int noticeNo) {
        NoticeDto.noticeNo = noticeNo;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        NoticeDto.subject = subject;
    }

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        NoticeDto.content = content;
    }

    public static String getRegisterTime() {
        return registerTime;
    }

    public static void setRegisterTime(String registerTime) {
        NoticeDto.registerTime = registerTime;
    }

    public static String getModifiedTime() {
        return modifiedTime;
    }

    public static void setModifiedTime(String modifiedTime) {
        NoticeDto.modifiedTime = modifiedTime;
    }
}
