package com.github.kwai.open.model;

import java.util.List;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class LiveSceneInfo {

    private Integer id;
    private String name;
    private String icon;
    private String category;
    private Integer screenType;
    private List<String> launchUrls;
    private String offline;
    private String backgroundUrl;
    private Boolean game;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getScreenType() {
        return screenType;
    }

    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }

    public List<String> getLaunchUrls() {
        return launchUrls;
    }

    public void setLaunchUrls(List<String> launchUrls) {
        this.launchUrls = launchUrls;
    }

    public String getOffline() {
        return offline;
    }

    public void setOffline(String offline) {
        this.offline = offline;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Boolean getGame() {
        return game;
    }

    public void setGame(Boolean game) {
        this.game = game;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LiveSceneInfo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", screenType=").append(screenType);
        sb.append(", launchUrls=").append(launchUrls);
        sb.append(", offline='").append(offline).append('\'');
        sb.append(", backgroundUrl='").append(backgroundUrl).append('\'');
        sb.append(", game=").append(game);
        sb.append('}');
        return sb.toString();
    }
}
