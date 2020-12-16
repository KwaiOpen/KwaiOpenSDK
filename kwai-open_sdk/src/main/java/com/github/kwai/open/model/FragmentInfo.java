package com.github.kwai.open.model;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public class FragmentInfo {

    private Integer id;
    private Integer size;
    private String checksum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FragmentInfo{");
        sb.append("id=").append(id);
        sb.append(", size=").append(size);
        sb.append(", checksum='").append(checksum).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
