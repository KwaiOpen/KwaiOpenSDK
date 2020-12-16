package com.github.kwai.open.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.model.FragmentInfo;
import com.github.kwai.open.request.GetFragmentResumeInfoRequest;
import com.github.kwai.open.request.StartUploadRequest;
import com.github.kwai.open.utils.ArrayUtils;
import com.github.kwai.open.utils.HttpUtils;
import com.github.kwai.open.utils.ValidatorUtils;
import com.github.kwai.open.constant.Constant;
import com.github.kwai.open.request.CreateVideoRequest;
import com.github.kwai.open.request.ResumeFragmentRequest;
import com.github.kwai.open.request.UploadCompleteRequest;
import com.github.kwai.open.request.UploadFileRequest;
import com.github.kwai.open.request.UploadFragmentRequest;
import com.github.kwai.open.request.UploadSingleRequest;
import com.github.kwai.open.request.VideoInfoRequest;
import com.github.kwai.open.request.VideoListRequest;
import com.github.kwai.open.request.VideoPublishRequest;
import com.github.kwai.open.response.CreateVideoResponse;
import com.github.kwai.open.response.ResumeFragmentResponse;
import com.github.kwai.open.response.StartUploadResponse;
import com.github.kwai.open.response.UploadCompleteResponse;
import com.github.kwai.open.response.UploadFileResponse;
import com.github.kwai.open.response.UploadFragmentResponse;
import com.github.kwai.open.response.GetFragmentResumeInfoResponse;
import com.github.kwai.open.response.UploadSingleResponse;
import com.github.kwai.open.response.VideoInfoResponse;
import com.github.kwai.open.response.VideoListResponse;
import com.github.kwai.open.response.VideoPublishResponse;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-10
 */
public class KwaiOpenVideoApi implements IOpenAPI {

    private String appId = "";

    private KwaiOpenVideoApi(String appId) {
        this.appId = appId;
    }


    /**
     * 初始化SDK
     *
     * @param appId appId
     */
    public static KwaiOpenVideoApi init(String appId) {
        return new KwaiOpenVideoApi(appId);
    }

    @Override
    public String getAppId() {
        return appId;
    }


    /**
     * [内容]开始上传 需要 user_video_publish 授权
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public StartUploadResponse startUpload(StartUploadRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.postMultipart(Constant.KWAI_OPEN_API_START_UPLOAD_URL, request.toQueryParam(this), StartUploadResponse.class);
    }

    /**
     * [内容]一键上传文件,如果大于10M会自动执行分片上传
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public UploadFileResponse uploadFile(UploadFileRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        UploadFileResponse response = new UploadFileResponse();
        response.setUploadToken(request.getUploadToken());
        response.setEndPoint(request.getEndPoint());
        if (request.getFileData().length > Constant.KWAI_OPEN_FRAGMENT_SIZE) {
            List<byte[]> fragment = ArrayUtils.splitBytes(request.getFileData(), Constant.KWAI_OPEN_FRAGMENT_SIZE);
            for (int i = 0; i < fragment.size(); i++) {
                uploadFileFragment(new UploadFragmentRequest(request.getUploadToken(), request.getEndPoint(), fragment.get(i), i));
            }
            UploadCompleteResponse uploadCompleteResponse = uploadFileFragmentComplete(new UploadCompleteRequest(request.getUploadToken(), request.getEndPoint(), fragment.size()));
            response.setResult(uploadCompleteResponse.getResult());
        } else {
            UploadSingleRequest uploadSingleRequest = new UploadSingleRequest(request.getUploadToken(), request.getEndPoint(), request.getFileData());
            UploadSingleResponse uploadSingleResponse = uploadFileSingle(uploadSingleRequest);
            response.setResult(uploadSingleResponse.getResult());
        }
        return response;
    }


    /**
     * [内容]单独上传文件
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public UploadSingleResponse uploadFileSingle(UploadSingleRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        String url = String.join("", "http://", request.getEndPoint(), Constant.KWAI_OPEN_API_UPLOAD_URL);
        return HttpUtils.postBinary(url, request.toQueryParam(), request.getFileData(), UploadSingleResponse.class);
    }


    /**
     * [内容]分片上传每一片文件
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public UploadFragmentResponse uploadFileFragment(UploadFragmentRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        String url = String.join("", "http://", request.getEndPoint(), Constant.KWAI_OPEN_API_UPLOAD_FRAGMENT_URL);
        return HttpUtils.postBinary(url, request.toQueryParam(), request.getFileData(), UploadFragmentResponse.class);
    }

    /**
     * [内容]获取断点续传信息
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public GetFragmentResumeInfoResponse getFragmentResumeInfo(GetFragmentResumeInfoRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        String url = String.join("", "http://", request.getEndPoint(), Constant.KWAI_OPEN_API_UPLOAD_FRAGMENT_RESUME_URL);
        return HttpUtils.get(url, request.toQueryParam(), GetFragmentResumeInfoResponse.class);
    }

    /**
     * [内容] 断点续传视频
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public ResumeFragmentResponse resumeFragment(ResumeFragmentRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        ResumeFragmentResponse response = new ResumeFragmentResponse();
        response.setUploadToken(request.getUploadToken());
        response.setEndPoint(request.getEndPoint());
        GetFragmentResumeInfoResponse fragmentResumeInfo = getFragmentResumeInfo(new GetFragmentResumeInfoRequest(request.getUploadToken(), request.getEndPoint()));
        if (fragmentResumeInfo.getExisted()) {
            return response;
        }
        if (request.getVideoFileData().length > Constant.KWAI_OPEN_FRAGMENT_SIZE) {
            List<byte[]> fragment = ArrayUtils.splitBytes(request.getVideoFileData(), Constant.KWAI_OPEN_FRAGMENT_SIZE);


            Map<Integer, FragmentInfo> donePart = fragmentResumeInfo.getFragmentList().stream().collect(Collectors.toMap(
                FragmentInfo::getId, fragmentInfo -> fragmentInfo
            ));
            for (int i = 0; i < fragment.size(); i++) {
                if (donePart.containsKey(i)) {
                    continue;
                }
                uploadFileFragment(new UploadFragmentRequest(request.getUploadToken(), request.getEndPoint(), fragment.get(i), i));
            }
            UploadCompleteResponse uploadCompleteResponse = uploadFileFragmentComplete(new UploadCompleteRequest(request.getUploadToken(), request.getEndPoint(), fragment.size()));
            response.setResult(uploadCompleteResponse.getResult());
        } else {
            UploadSingleRequest uploadSingleRequest = new UploadSingleRequest(request.getUploadToken(), request.getEndPoint(), request.getVideoFileData());
            UploadSingleResponse uploadSingleResponse = uploadFileSingle(uploadSingleRequest);
            response.setResult(uploadSingleResponse.getResult());
        }
        return response;
    }

    /**
     * [内容]完成分片上传
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public UploadCompleteResponse uploadFileFragmentComplete(UploadCompleteRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        String url = String.join("", "http://", request.getEndPoint(), Constant.KWAI_OPEN_API_UPLOAD_COMPLETE_URL);
        return HttpUtils.postMultipart(url, request.toQueryParam(), Collections.EMPTY_MAP, UploadCompleteResponse.class);
    }

    /**
     * [内容]上传完毕后 发布视频
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public VideoPublishResponse videoPublish(VideoPublishRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.postMultipart(Constant.KWAI_OPEN_API_VIDEO_PUBLISH_URL, request.toQueryParam(this), request.toDataParam(), Arrays.asList(request.toFileParam()), VideoPublishResponse.class);
    }


    /**
     * [内容] 一键上传发布视频
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public CreateVideoResponse createVideo(CreateVideoRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        CreateVideoResponse response = new CreateVideoResponse();
        StartUploadRequest startUploadRequest = new StartUploadRequest(request.getAccessToken());
        StartUploadResponse startUploadResponse = startUpload(startUploadRequest);
        UploadFileRequest uploadFileRequest = new UploadFileRequest(startUploadResponse, request.getVideoFileData());
        uploadFile(uploadFileRequest);
        VideoPublishRequest videoPublishRequest = new VideoPublishRequest(request, startUploadResponse);
        VideoPublishResponse videoPublishResponse = videoPublish(videoPublishRequest);
        response.setResult(videoPublishResponse.getResult());
        response.setVideoInfo(videoPublishResponse.getVideoInfo());
        return response;
    }


    /**
     * [内容]查询视频列表
     * count 默认为 20
     * 获取第一页时 cursor 无需赋值
     * 如需要下一页时,request的cursor需要传递上一个 response 的 getLastCursor
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public VideoListResponse queryVideoList(VideoListRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_API_VIDEO_LIST_URL, request.toQueryParam(this), VideoListResponse.class);
    }

    /**
     * [内容] 查询视频信息
     *
     * @param request
     * @return
     * @throws KwaiOpenException
     */
    public VideoInfoResponse queryVideoInfo(VideoInfoRequest request) throws KwaiOpenException {
        ValidatorUtils.valid(request);
        return HttpUtils.get(Constant.KWAI_OPEN_API_VIDEO_INFO_URL, request.toQueryParam(this), VideoInfoResponse.class);
    }

}
