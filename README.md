## 1 简介
为了简化开发者接入开放平台能力,提供了服务端的SDK,开发者可以依托此SDK快速接入开放平台。
## 2 如何接入
在项目中POM文件引入依赖
```lang
<dependency>
    <groupId>com.github.kwaiopen</groupId>
    <artifactId>kwai-open-sdk</artifactId>
    <version>1.0.3</version>
</dependency>
```
关于SDK版本号更新,请关注平台公告.
## 3 包含组件


|组件|介绍|
|-|-|
|KwaiOpenOauthApi|oauth2.0协议的接口封装|
|KwaiOpenUserApi|获取用户信息的相关接口封装|
|KwaiOpenVideoApi|发布内容能力的相关接口封装|
|KwaiOpenLiveApi|直播能力的相关接口封装|

<br/>

#### 3.1 KwaiOpenOauthApi


#### 3.1.1初始化
```lang
KwaiOpenOauthApi kwaiOpenOauthApi = KwaiOpenOauthApi.init(appId);
```
建议声明为javabean或者静态变量.
<br/>
#### 3.1.2 KwaiOpenOauthApi.getAccessToken
授权code置换access_token
|请求参数|说明|
|-|-|-|
|code|授权流程获取的授权code|
|appSecret|注册app时获取的appSecret|

|返回参数|说明|
|-|-|-|
|accessToken|访问令牌,需要开发者自己保存,访问openAPI时需要传递|
|expiresIn|访问令牌过期时间,单位(秒)|
|refreshToken|刷新令牌,需要开发者自己保存,在accessToken过期前刷新accessToken|
|openId|授权用户对应的唯一ID,开发者需要保存起来和当前的用户做关联|
|scopes|本次授权token中包含的scope列表,如果请求的openAPI对应的scope没有包含在这个列表里,需要让用户授权|
|refreshTokenExpiresIn|refreshToken的过期时间,单位(秒)|
调用demo
```lang
try{
	AccessTokenRequest request = new AccessTokenRequest(code, appSecret);
	AccessTokenResponse response = kwaiOpenOauthApi.getAccessToken(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/>     

#### 3.1.3 KwaiOpenOauthApi.refreshToken
在accessToken过期前,需要调用此接口刷新accessToken和refreshToken
|请求参数|说明|
|-|-|-|
|refreshToken|刷新令牌,确保refreshToken的仍在有效时间内|
|appSecret|注册app时获取的appSecret|

|返回参数|说明|
|-|-|-|
|accessToken|访问令牌,需要开发者自己保存,访问openAPI时需要传递|
|expiresIn|访问令牌过期时间,单位(秒)|
|refreshToken|刷新令牌,需要开发者自己保存,在accessToken过期前刷新accessToken|
|scopes|本次授权token中包含的scope列表,如果请求的openAPI对应的scope没有包含在这个列表里,需要让用户授权|
|refreshTokenExpiresIn|refreshToken的过期时间,单位(秒)|
调用demo
```lang
try{
	RefreshTokenRequest request = new RefreshTokenRequest(refreshToken, appSecret);
        RefreshTokenResponse refreshTokenResponse = kwaiOpenOauthApi.refreshToken(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```

#### 3.2 KwaiOpenUserApi
#### 3.2.1初始化
```lang
KwaiOpenUserApi kwaiOpenUserApi = KwaiOpenUserApi.init(appId);
```
建议声明为javabean或者静态变量.
<br/> 
#### 3.2.2 KwaiOpenUserApi.getUserInfo
获取用户信息
|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_info==,<br/>同时需要确保accessToken在有效期内|

|返回参数|-|说明|
|-|-|-|
|userInfo|-|用户信息|
|-|name|用户昵称|
|-|sex|用户性别|
|-|fan|用户粉丝数|
|-|follow|用户关注数|
|-|head|用户头像图片地址|
|-|bigHead|用户高清头像图片地址|
|-|city|用户城市|
调用demo
```lang
try{
	UserInfoRequest request = new UserInfoRequest(accessToken);
        UserInfoResponse userInfo = kwaiOpenUserApi.getUserInfo(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/>    

#### 3.2.3 KwaiOpenUserApi.getUserPhone<功能内测中>
获取用户电话信息
|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_phone==,<br/>同时需要确保accessToken在有效期内|

|返回参数|-|说明|
|-|-|-|
|userPhoneInfo|-|电话信息|
|-|countryCode|电话的区域码,国内为 86|
|-|phoneNumber|用户的电话号码|
调用demo
```lang
try{
	UserPhoneRequest request = new UserPhoneRequest(accessToken, appSecret);
        UserPhoneResponse userPhone = kwaiOpenUserApi.getUserPhone(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/>  


#### 3.3 KwaiOpenVideoApi
#### 3.3.1初始化
```lang
KwaiOpenVideoApi kwaiOpenVideoApi = KwaiOpenVideoApi.init(appId);
```
建议声明为javabean或者静态变量.
<br/> 
 

#### 3.3.2 KwaiOpenVideoApi.queryVideoList
查询用户的作品列表	

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_info==,<br/>同时需要确保accessToken在有效期内|
|count|每页视频数,可以不赋值,默认为20|
|cursor|上一页的最后一个视频ID,获取第一页的时候不赋值,获取第二页的时候传递上一页请求的lastCursor|


|返回参数|-|说明|
|-|-|-|
|lastCursor|-|本页最后一个作品ID,查询下一页时使用|
|videoList|-|本页视频列表|
|-|photoId|发布的作品ID|
|-|caption|发布的作品标题|
|-|cover|作品封面地址|
|-|playUrl|作品播放链接(未转码完成时,此字段没有值)|
|-|createTime|创建时间|
|-|likeCount|作品点赞数|
|-|commentCount|作品评论数|
|-|viewCount|作品观看数|
|-|pending|作品状态(是否还在处理中，不能观看)|
调用demo
```lang
try{
        VideoListRequest request = new VideoListRequest(accessToken, 20);
        VideoListResponse videoListResponse = kwaiOpenVideoApi.queryVideoList(videoListRequest);
        VideoListRequest nextPage = new VideoListRequest(accessToken, videoListResponse.getLastCursor(), 20);
        VideoListResponse nextPageResponse = kwaiOpenVideoApi.queryVideoList(nextPage);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}
```
<br/>  


#### 3.3.3 KwaiOpenVideoApi.queryVideoInfo
查询用户的作品列表	

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_info==,<br/>同时需要确保accessToken在有效期内|
|photoId|作品ID|


|返回参数|-|说明|
|-|-|-|
|videoInfo|-|作品信息|
|-|photoId|发布的作品ID|
|-|caption|发布的作品标题|
|-|cover|作品封面地址|
|-|playUrl|作品播放链接(未转码完成时,此字段没有值)|
|-|createTime|创建时间|
|-|likeCount|作品点赞数|
|-|commentCount|作品评论数|
|-|viewCount|作品观看数|
|-|pending|作品状态(是否还在处理中，不能观看)|
调用demo
```lang
try{
        VideoInfoRequest request = new VideoInfoRequest(accessToken, photoId);
        VideoInfoResponse response = kwaiOpenVideoApi.queryVideoInfo(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}
```
<br/>  

#### 3.3.4 KwaiOpenVideoApi.createVideo
一键创建视频,封装了上传和发布的全部过程.
如果上传文件大于10M,会自动进行单线程分片上传.
如果相对上传过程需要进行更多的控制,可以使用下面的分步处理接口(3.3.5 - 3.3.9)

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_publish==,<br/>同时需要确保accessToken在有效期内|
|videoFileData|视频的二进制数据|
|caption|视频标题|
|cover|视频的封面图片,请使用jpg文件|
|stereoType|全景参数,NOT_SPHERICAL_VIDEO SPHERICAL_VIDEO_360 SPHERICAL_VIDEO_180,非全景视频不需要传递|

|返回参数|-|说明|
|-|-|-|
|videoInfo|-|发布的作品信息|
|-|photoId|发布的作品ID|
|-|caption|发布的作品标题|
|-|cover|作品封面地址|
|-|playUrl|作品播放链接(未转码完成时,此字段没有值)|
|-|createTime|创建时间|
|-|likeCount|作品点赞数|
|-|commentCount|作品评论数|
|-|viewCount|作品观看数|
|-|pending|作品状态(是否还在处理中，不能观看)|
```lang
try{
        byte[] videoFileData = getVideoData();
        byte[] coverFileData = getConverData();
	CreateVideoRequest request = new CreateVideoRequest(accessToken);
	request.setCaption("一键发布测试");
        request.setCover(coverFileData);
        request.setVideoFileData(videoFileData);
        CreateVideoResponse response = kwaiOpenVideoApi.createVideo(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}
```
<br/> 

#### 3.3.5 KwaiOpenVideoApi.startUpload
分步处理接口,第一步,开始上传文件,获得uploadToken

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_publish==,<br/>同时需要确保accessToken在有效期内|


|返回参数|说明|
|-|-|-|
|uploadToken|上传token|
|endpoint|上传服务器地址|

```lang
try{
	StartUploadRequest request = new StartUploadRequest(accessToken);
        StartUploadResponse startUploadResponse = kwaiOpenVideoApi.startUpload(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/> 

#### 3.3.5 KwaiOpenVideoApi.uploadFile
分步处理接口,第二步,上传文件,
如果对上传过程不敏感时,可以使用此接口.
如果上传文件大于10M,会自动进行单线程分片上传.
如果需要对上传过程进行处理(自己处理分片,多线程上传分片,断点续传等功能),可以使用下面的处理接口自行上传()


|请求参数|说明|
|-|-|-|
|uploadToken|startUpload接口获取到的uploadToken|
|endPoint|startUpload接口获取到的endPoint|
|fileData|需要上传的文件二进制数据|


|返回参数|说明|
|-|-|-|
|uploadToken|上传token|
|endpoint|上传服务器地址|
|fragmentWay|是否是分片方式上传的|

```lang
try{
	UploadFileRequest uploadFileRequest = new UploadFileRequest(startUploadResponse, videoFileData);
        UploadFileResponse response =  kwaiOpenVideoApi.uploadFile(uploadFileRequest);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/> 

#### 3.3.6 KwaiOpenVideoApi.uploadFileSingle
分步处理接口,第二步,上传文件,
对于小于10M的文件调用此方法,不分片直接上传,
不需要调用uploadFileFragmentComplete


|请求参数|说明|
|-|-|-|
|uploadToken|startUpload接口获取到的uploadToken|
|endPoint|startUpload接口获取到的endPoint|
|fileData|需要上传的文件二进制数据|


```lang
try{
	UploadSingleRequest request = new UploadSingleRequest(startUploadResponse, videoFileData);
        UploadSingleResponse response =  kwaiOpenVideoApi.uploadFileSingle(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/> 


#### 3.3.6 KwaiOpenVideoApi.uploadFileFragment
分步处理接口,第二步,上传文件,
对于大于10M的文件调用此方法,自行分片之后多次调用此方法上传每个分片
上传完成后需要调用uploadFileFragmentComplete 完成上传

|请求参数|说明|
|-|-|-|
|uploadToken|startUpload接口获取到的uploadToken|
|endPoint|startUpload接口获取到的endPoint|
|fileData|需要上传的文件二进制数据|
|fragmentId|分片ID,从0开始自增|

调用示例

```lang
try{
	byte[] fileData = getFile();
        List<byte[]> bytes = ArrayUtils.splitBytes(fileData, 8 * 1024 * 1024);
        int fragment = 0;
        for (byte[] aByte : bytes) {
            kwaiOpenVideoApi.uploadFileFragment(new UploadFragmentRequest(startUploadResponse, aByte, fragment));
            fragment++;
        }

        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}

```
<br/> 


#### 3.3.7 KwaiOpenVideoApi.uploadFileFragmentComplete
分步处理接口,第二步,上传文件,分片上传结束
分片上传完成后需要调用此接口结束上传

|请求参数|说明|
|-|-|-|
|uploadToken|startUpload接口获取到的uploadToken|
|endPoint|startUpload接口获取到的endPoint|
|fragmentCount|总分片数|




调用示例

```lang
try{
	UploadCompleteRequest request = new UploadCompleteRequest(startUploadResponse, fragmentCount);
        UploadCompleteResponse response =  kwaiOpenVideoApi.uploadFileFragmentComplete(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 

#### 3.3.8 KwaiOpenVideoApi.getFragmentResumeInfo
分步处理接口,第二步,上传文件,获取断点信息
分片上传中间有部分步骤失败时,可以调用此接口获取到已经上传成功的分片,然后将没有成功的分片进行续传,续传完成后调用uploadFileFragmentComplete完成上传

|请求参数|说明|
|-|-|-|
|uploadToken|startUpload接口获取到的uploadToken|
|endPoint|startUpload接口获取到的endPoint|

|返回参数|-|说明|
|-|-|-|
|existed|-|是否已经上传完毕了|
|fragmentIndex|-|从0开始的已上传的连续分片id (-1 表示没有分片)|
|fragmentList|-|已上传的分片|
|-|id|已上传的分片ID|
|-|size|已上传的分片大小|
|-|checksum||
|fragmentIndexBytes|-|从0开始的已上传的连续分片size |
|tokenId|-|上传id 无需关注|

调用示例

```lang
try{
	GetFragmentResumeInfoRequest request = new GetFragmentResumeInfoRequest(uploadToken, endPoint);
        GetFragmentResumeInfoResponse fragmentResumeInfo =  kwaiOpenVideoApi.getFragmentResumeInfo(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 

#### 3.3.9 KwaiOpenVideoApi.videoPublish
分步处理接口,第三步,发布视频
调用uploadFileSingle成功后 , 
或者分片上传调用uploadFileFragmentComplete成功后
调用此接口进行视频发布

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_info==,<br/>同时需要确保accessToken在有效期内|
|caption|视频标题|
|cover|视频的封面图片,请使用jpg文件|
|stereoType|全景参数,NOT_SPHERICAL_VIDEO SPHERICAL_VIDEO_360 SPHERICAL_VIDEO_180,非全景视频不需要传递|
|uploadToken|startUpload接口获取到的uploadToken|

|返回参数|-|说明|
|-|-|-|
|videoInfo|-|发布的作品信息|
|-|photoId|发布的作品ID|
|-|caption|发布的作品标题|
|-|cover|作品封面地址|
|-|playUrl|作品播放链接(未转码完成时,此字段没有值)|
|-|createTime|创建时间|
|-|likeCount|作品点赞数|
|-|commentCount|作品评论数|
|-|viewCount|作品观看数|
|-|pending|作品状态(是否还在处理中，不能观看)|
调用示例

```lang
try{
        VideoPublishRequest videoPublishRequest = new VideoPublishRequest(access_token);
	videoPublishRequest.setCaption("发布标题");
	videoPublishRequest.setCover(coverData);
	videoPublishRequest.setUploadToken(uploadToken);
        VideoPublishResponse videoPublishResponse = videoPublish(videoPublishRequest);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 


#### 3.4 KwaiOpenLiveApi
#### 3.4.1初始化
```lang
KwaiOpenLiveApi kwaiOpenLiveApi = KwaiOpenLiveApi.init(appId);
```
建议声明为javabean或者静态变量.
<br/>


#### 3.4.2 KwaiOpenVideoApi.getPushUrl
获取推流地址 开始直播
获取推流地址后,请自行使用RTMP方式进行推流

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_live==,<br/>同时需要确保accessToken在有效期内|
|file|直播封面，不超过2M，只能上传jpg格式，推荐尺寸720x1280，|
|caption|直播标题，长度不超过13个汉字，默认|
|panoramic|非全景直播 0、180 全景直播 1、360 全景直播 2,非必传,默认0|
|shopLive|是否为电商（小黄车）直播，非必传,默认 false|
|deviceName|设备名称，用于数据聚合分析,非必传|
|liveSceneType|直播场景分类id，值域参考如下分类接口返回值（例如全景直播 liveSceneType=22141）|

|返回参数|-|说明|
|-|-|-|
|content|-|推流信息|
|-|pushUrl|推流地址|
|-|liveStreamName|推流名称|
调用示例

```lang
try{
        GetPushUrlResquest resquest = new GetPushUrlResquest(accessToken);
        resquest.setCaption("测试直播");
        resquest.setFile(coverData);
        GetPushUrlResponse response = kwaiOpenLiveApi.getPushUrl(resquest);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 

#### 3.4.3 KwaiOpenVideoApi.getAllScene
获取直播分类

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_live==,<br/>同时需要确保accessToken在有效期内|

返回参数
见 [文档](https://open.kuaishou.com/platform/openApi?menu=24)

调用示例

```lang
try{
         GetAllSceneRequest request = new GetAllSceneRequest(accessToken);
        GetAllSceneResponse response = kwaiOpenLiveApi.getAllScene(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 



#### 3.4.4 KwaiOpenVideoApi.stopPush
结束直播

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_live==,<br/>同时需要确保accessToken在有效期内|
|liveStreamName|获取推流地址中的推流名称|


调用示例

```lang
try{
        StopPushRequest request = new StopPushRequest(accessToken,liveStreamName);
        StopPushResponse response = kwaiOpenLiveApi.stopPush(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 


#### 3.4.5 KwaiOpenVideoApi.pushStatus
获取推流状态

|请求参数|说明|
|-|-|-|
|accessToken|getAccessToken接口获取到的accessToken,<br/>需要确保scope包含 ==user_video_live==,<br/>同时需要确保accessToken在有效期内|
|liveStreamName|获取推流地址中的推流名称|

|返回参数|-|说明|
|-|-|-|
|content|-|推流状态|
|-|liveStreamName|推流ID|
|-|status|直播中 LIVING、未开播 LIVE_NOT_START、已关播 LIVE_END|
|-|statusMsg|状态原因说明|

调用示例

```lang
try{
        PushStatusRequest request = new PushStatusRequest(accessToken,liveStreamName);
        PushStatusResponse response = kwaiOpenLiveApi.pushStatus(request);
        //调用成功
}catch(KwaiOpenException e){
	//调用失败
}


```
<br/> 
