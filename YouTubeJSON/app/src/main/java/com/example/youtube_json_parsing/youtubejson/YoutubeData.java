package com.example.youtube_json_parsing.youtubejson;

/**
 * Created by Mradul on 3/31/2015.
 */
public class YoutubeData {
    String url,loader,title,description;
    String image;

    public YoutubeData(String url, String loader, String title, String description, String image){

        setUrl(url);
        setLoader(loader);
        setTitle(title);
        setDescription(description);
        setImage(image);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
