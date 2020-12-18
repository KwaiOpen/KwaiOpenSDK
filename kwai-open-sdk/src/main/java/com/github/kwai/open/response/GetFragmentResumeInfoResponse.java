package com.github.kwai.open.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kwai.open.model.FragmentInfo;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-16
 */
public class GetFragmentResumeInfoResponse extends BaseResponse {

    private Boolean existed;

    @JsonProperty("fragment_index")
    private Integer fragmentIndex;


    @JsonProperty("fragment_list")
    private List<FragmentInfo> fragmentList;


    @JsonProperty("fragment_index_bytes")
    private Integer fragmentIndexBytes;

    @JsonProperty("token_id")
    private String tokenId;

    public Boolean getExisted() {
        return existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }

    public Integer getFragmentIndex() {
        return fragmentIndex;
    }

    public void setFragmentIndex(Integer fragmentIndex) {
        this.fragmentIndex = fragmentIndex;
    }

    public List<FragmentInfo> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<FragmentInfo> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public Integer getFragmentIndexBytes() {
        return fragmentIndexBytes;
    }

    public void setFragmentIndexBytes(Integer fragmentIndexBytes) {
        this.fragmentIndexBytes = fragmentIndexBytes;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UploadFragmentResumeResponse{");
        sb.append("existed=").append(existed);
        sb.append(", fragmentIndex='").append(fragmentIndex).append('\'');
        sb.append(", fragmentList=").append(fragmentList);
        sb.append(", fragmentIndexBytes=").append(fragmentIndexBytes);
        sb.append(", tokenId='").append(tokenId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
