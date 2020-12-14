package com.kuaishou.open.sdk.model;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class UserInfo {


    private String name;
    private String sex;
    private Long fan;
    private Long follow;
    private String head;
    private String bigHead;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getFan() {
        return fan;
    }

    public void setFan(Long fan) {
        this.fan = fan;
    }

    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBigHead() {
        return bigHead;
    }

    public void setBigHead(String bigHead) {
        this.bigHead = bigHead;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", fan=").append(fan);
        sb.append(", follow=").append(follow);
        sb.append(", head='").append(head).append('\'');
        sb.append(", bigHead='").append(bigHead).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
