package com.github.kwai.open.response;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class UploadFragmentResponse  extends BaseResponse  {


    private String checksum;

    private Long size;



    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
