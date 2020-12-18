package com.github.kwai.open;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.kwai.open.api.KwaiOpenLiveApi;
import com.github.kwai.open.api.KwaiOpenOauthApi;
import com.github.kwai.open.api.KwaiOpenUserApi;
import com.github.kwai.open.api.KwaiOpenVideoApi;
import com.github.kwai.open.request.GetPushUrlResquest;
import com.github.kwai.open.request.StartUploadRequest;
import com.github.kwai.open.response.AccessTokenResponse;
import com.github.kwai.open.response.CreateVideoResponse;
import com.github.kwai.open.response.GetAllSceneResponse;
import com.github.kwai.open.response.RefreshTokenResponse;
import com.github.kwai.open.response.StopPushResponse;
import com.github.kwai.open.utils.ArrayUtils;
import com.github.kwai.open.request.AccessTokenRequest;
import com.github.kwai.open.request.CreateVideoRequest;
import com.github.kwai.open.request.GetAllSceneRequest;
import com.github.kwai.open.request.PushStatusRequest;
import com.github.kwai.open.request.RefreshTokenRequest;
import com.github.kwai.open.request.ResumeFragmentRequest;
import com.github.kwai.open.request.StopPushRequest;
import com.github.kwai.open.request.UploadCompleteRequest;
import com.github.kwai.open.request.UploadFragmentRequest;
import com.github.kwai.open.request.UploadSingleRequest;
import com.github.kwai.open.request.UserInfoRequest;
import com.github.kwai.open.request.UserPhoneRequest;
import com.github.kwai.open.request.VideoInfoRequest;
import com.github.kwai.open.request.VideoListRequest;
import com.github.kwai.open.request.VideoPublishRequest;
import com.github.kwai.open.response.GetPushUrlResponse;
import com.github.kwai.open.response.PushStatusResponse;
import com.github.kwai.open.response.ResumeFragmentResponse;
import com.github.kwai.open.response.StartUploadResponse;
import com.github.kwai.open.response.UserInfoResponse;
import com.github.kwai.open.response.UserPhoneResponse;
import com.github.kwai.open.response.VideoInfoResponse;
import com.github.kwai.open.response.VideoListResponse;
import com.github.kwai.open.response.VideoPublishResponse;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
class KwaiOpenOauthApiTest {

    private String accessToken = "*";
    private String appId = "*";
    private String appSecret = "*";
    private String code = "*";
    private String refreshToken = "*";

    private KwaiOpenOauthApi kwaiOpenOauthApi;
    private KwaiOpenUserApi kwaiOpenUserApi;
    private KwaiOpenLiveApi kwaiOpenLiveApi;
    private KwaiOpenVideoApi kwaiOpenVideoApi;

    @BeforeEach
    public void init() {
        kwaiOpenOauthApi = KwaiOpenOauthApi.init(appId);
        kwaiOpenVideoApi = KwaiOpenVideoApi.init(appId);
        kwaiOpenLiveApi = KwaiOpenLiveApi.init(appId);
        kwaiOpenUserApi = KwaiOpenUserApi.init(appId);
    }

    private static byte[] demoFile(String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream in = new FileInputStream(fileName)) {

            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return out.toByteArray();

    }


    @Test
    void getAccessToken() throws KwaiOpenException {
        AccessTokenRequest request = new AccessTokenRequest(code, appSecret);
        AccessTokenResponse accessTokenResponse = kwaiOpenOauthApi.getAccessToken(request);
        System.out.println(accessTokenResponse);

    }

    @Test
    void refreshToken() throws KwaiOpenException {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken, appSecret);
        RefreshTokenResponse refreshTokenResponse = kwaiOpenOauthApi.refreshToken(request);
        System.out.println(refreshTokenResponse);
    }

    @Test
    void getUserInfo() throws KwaiOpenException {
        UserInfoRequest request = new UserInfoRequest(accessToken);
        UserInfoResponse userInfo = kwaiOpenUserApi.getUserInfo(request);
        System.out.println(userInfo);

    }

    @Test
    void getUserPhone() throws KwaiOpenException {
        UserPhoneRequest request = new UserPhoneRequest(accessToken, appSecret);
        UserPhoneResponse userPhone = kwaiOpenUserApi.getUserPhone(request);
        System.out.println(userPhone);
    }

    @Test
    void startUpload() throws KwaiOpenException {
        StartUploadRequest request = new StartUploadRequest(accessToken);
        StartUploadResponse startUploadResponse = kwaiOpenVideoApi.startUpload(request);
        System.out.println(startUploadResponse);

    }

    @Test
    void createVideo() throws KwaiOpenException {
        CreateVideoRequest request = new CreateVideoRequest(accessToken);
        request.setCaption("一键发布测试");
        request.setCover(demoFile("/Users/wuge/Documents/timg.jpeg"));
        request.setVideoFileData(demoFile("/Users/wuge/Documents/1608035947363906.mp4"));
        CreateVideoResponse response = kwaiOpenVideoApi.createVideo(request);
        System.out.println(response);

    }


    @Test
    void createSingleVideo() throws KwaiOpenException {
        StartUploadRequest request = new StartUploadRequest(accessToken);
        StartUploadResponse startUploadResponse = kwaiOpenVideoApi.startUpload(request);
        System.out.println(startUploadResponse);
        byte[] fileData = demoFile("/Users/wuge/Documents/kwai_video.a7616d99.mp4");
        kwaiOpenVideoApi.uploadFileSingle(new UploadSingleRequest(startUploadResponse, fileData));
        byte[] coverData = demoFile("/Users/wuge/Documents/timg.jpeg");
        VideoPublishRequest videoPublishRequest = new VideoPublishRequest();
        videoPublishRequest.setCaption("测试6");
        videoPublishRequest.setCover(coverData);
        videoPublishRequest.setAccessToken(accessToken);
        videoPublishRequest.setUploadToken(startUploadResponse.getUploadToken());
        VideoPublishResponse videoPublishResponse = kwaiOpenVideoApi.videoPublish(videoPublishRequest);
        System.out.println(videoPublishResponse);
    }

    @Test
    void createFragmentVideo() throws KwaiOpenException {
        StartUploadRequest request = new StartUploadRequest();
        request.setAccessToken(accessToken);
        StartUploadResponse startUploadResponse = kwaiOpenVideoApi.startUpload(request);
        System.out.println(startUploadResponse);
        byte[] fileData = demoFile("/Users/wuge/Documents/1608035947363906.mp4");
        List<byte[]> bytes = ArrayUtils.splitBytes(fileData, 5 * 1024 * 1024);
        int fragment = 0;
        for (byte[] aByte : bytes) {
            kwaiOpenVideoApi.uploadFileFragment(new UploadFragmentRequest(startUploadResponse, aByte, fragment));
            fragment++;
        }

        kwaiOpenVideoApi.uploadFileFragmentComplete(new UploadCompleteRequest(startUploadResponse, bytes.size()));

        byte[] coverData = demoFile("/Users/wuge/Documents/timg.jpeg");
        VideoPublishRequest videoPublishRequest = new VideoPublishRequest();
        videoPublishRequest.setCaption("测试哈哈哈哈好好哄哄耦合");
        videoPublishRequest.setCover(coverData);
        videoPublishRequest.setAccessToken(accessToken);
        videoPublishRequest.setUploadToken(startUploadResponse.getUploadToken());
        VideoPublishResponse videoPublishResponse = kwaiOpenVideoApi.videoPublish(videoPublishRequest);
        System.out.println(videoPublishResponse);

    }

    @Test
    void createFragmentResumeVideo() throws KwaiOpenException {
        StartUploadRequest request = new StartUploadRequest();
        request.setAccessToken(accessToken);
        StartUploadResponse startUploadResponse = kwaiOpenVideoApi.startUpload(request);
        System.out.println(startUploadResponse);
        byte[] fileData = demoFile("/Users/wuge/Documents/1608035947363906.mp4");
        List<byte[]> bytes = ArrayUtils.splitBytes(fileData, 5 * 1024 * 1024);
        kwaiOpenVideoApi.uploadFileFragment(new UploadFragmentRequest(startUploadResponse, bytes.get(1), 1));
        //模拟传一半断开

        ResumeFragmentRequest resumeFragmentRequest = new ResumeFragmentRequest(startUploadResponse, fileData);
        ResumeFragmentResponse resumeFragmentResponse = kwaiOpenVideoApi.resumeFragment(resumeFragmentRequest);

        System.out.println(resumeFragmentResponse);
        byte[] coverData = demoFile("/Users/wuge/Documents/timg.jpeg");
        VideoPublishRequest videoPublishRequest = new VideoPublishRequest();
        videoPublishRequest.setCaption("测试断点续传功能123123");
        videoPublishRequest.setCover(coverData);
        videoPublishRequest.setAccessToken(accessToken);
        videoPublishRequest.setUploadToken(startUploadResponse.getUploadToken());
        VideoPublishResponse videoPublishResponse = kwaiOpenVideoApi.videoPublish(videoPublishRequest);
        System.out.println(videoPublishResponse);

    }


    @Test
    void queryVideoList() throws KwaiOpenException {
        VideoListRequest videoListRequest = new VideoListRequest(accessToken, 3);
        VideoListResponse videoListResponse = kwaiOpenVideoApi.queryVideoList(videoListRequest);
        System.out.println(videoListResponse);

        videoListRequest = new VideoListRequest(accessToken, videoListResponse.getLastCursor(), 3);
        videoListResponse = kwaiOpenVideoApi.queryVideoList(videoListRequest);
        System.out.println(videoListResponse);

    }

    @Test
    void queryVideoInfo() throws KwaiOpenException {
        String photoId = "3xqt2f8tmgrk3f2";
        VideoInfoRequest videoInfoRequest = new VideoInfoRequest(accessToken, photoId);
        VideoInfoResponse response = kwaiOpenVideoApi.queryVideoInfo(videoInfoRequest);
        System.out.println(response);
    }

    @Test
    void getPushUrl() throws KwaiOpenException {
        GetPushUrlResquest resquest = new GetPushUrlResquest();
        resquest.setAccessToken(accessToken);
        resquest.setCaption("哈哈");
        resquest.setDeviceName("1");
        resquest.setFile(demoFile("/Users/wuge/Documents/timg.jpeg"));

        resquest.setShopLive(false);
        GetPushUrlResponse response = kwaiOpenLiveApi.getPushUrl(resquest);
        System.out.println(response);
    }

    @Test
    void getAllScene() throws KwaiOpenException {
        GetAllSceneRequest request = new GetAllSceneRequest(accessToken);
        GetAllSceneResponse response = kwaiOpenLiveApi.getAllScene(request);
        System.out.println(response);
    }

    @Test
    void stopPush() throws KwaiOpenException {
        StopPushRequest request = new StopPushRequest(accessToken,"r2g3hs_1MFk");
        StopPushResponse response = kwaiOpenLiveApi.stopPush(request);
        System.out.println(response);
    }

    @Test
    void pushStatus() throws KwaiOpenException {
        PushStatusRequest request = new PushStatusRequest(accessToken,"MlReVYwOjLQ");
        PushStatusResponse response = kwaiOpenLiveApi.pushStatus(request);
        System.out.println(response);
    }
}