package com.example.news.bean;

import java.util.List;

public class DailyNewsBean {

    /**
     * date : 20191205
     * stories : [{"ga_prefix":"120509","hint":"孟凡康 · 7 分钟阅读","id":9717618,"image_hue":"0x051c46","images":["https://pic1.zhimg.com/v2-9179313fa60cd4f868e2f2ae33110438.jpg"],"title":"《Nature》报道中国科学家发现菌群在迁徙竞争时并非越快越有优势，如何解读此研究？","type":0,"url":"https://daily.zhihu.com/story/9717618"},{"ga_prefix":"120507","hint":"KellyWeaver · 4 分钟阅读","id":9717740,"image_hue":"0x414141","images":["https://pic1.zhimg.com/v2-0de26ed78c8c01c017e1a978447f6508.jpg"],"title":"如果想集齐抽卡游戏的全套卡片，应该如何估算操作成本？","type":0,"url":"https://daily.zhihu.com/story/9717740"},{"ga_prefix":"120506","hint":"VOL.2276","id":9717901,"image_hue":"0xa69274","images":["https://pic1.zhimg.com/v2-c6cdf96c680cfcc81cb4fb8753e7a4d8.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0,"url":"https://daily.zhihu.com/story/9717901"}]
     * top_stories : [{"ga_prefix":"120407","hint":"作者 / 苏澄宇","id":9717531,"image":"https://pic2.zhimg.com/v2-5c87a645d36d140fa87df6e8ca7cb989.jpg","image_hue":"0x879943","title":"斑马的条纹到底是干嘛用的？","type":0,"url":"https://daily.zhihu.com/story/9717531"},{"ga_prefix":"120107","hint":"作者 / 混乱博物馆","id":9717547,"image":"https://pic4.zhimg.com/v2-60f220ee6c5bf035d0eaf2dd4736342b.jpg","image_hue":"0xb39a7d","title":"为什么狗会如此亲近人类?","type":0,"url":"https://daily.zhihu.com/story/9717547"},{"ga_prefix":"112716","hint":"作者 / 丘寒","id":9717774,"image":"https://pic4.zhimg.com/v2-2379bf3e788a57c55a036bfb0b9b4aff.jpg","image_hue":"0x384c62","title":"我在成都，观雪山","type":0,"url":"https://daily.zhihu.com/story/9717774"},{"ga_prefix":"112509","hint":"作者 / 卢西","id":9717456,"image":"https://pic3.zhimg.com/v2-8e9852df41f6c0624096f01dd9f72e0e.jpg","image_hue":"0x57778f","title":"假如乘坐超音速飞机时突然弹射出去，会不会在音障上被撞成碎片？","type":0,"url":"https://daily.zhihu.com/story/9717456"},{"ga_prefix":"112807","hint":"作者 / 放学请找我","id":9717680,"image":"https://pic3.zhimg.com/v2-1d2851cd7ab83c91e5f04910ae9f970a.jpg","image_hue":"0xb3a27d","title":"我在网上合法买了正规电影让大家一起看，违法吗？","type":0,"url":"https://daily.zhihu.com/story/9717680"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * ga_prefix : 120509
         * hint : 孟凡康 · 7 分钟阅读
         * id : 9717618
         * image_hue : 0x051c46
         * images : ["https://pic1.zhimg.com/v2-9179313fa60cd4f868e2f2ae33110438.jpg"]
         * title : 《Nature》报道中国科学家发现菌群在迁徙竞争时并非越快越有优势，如何解读此研究？
         * type : 0
         * url : https://daily.zhihu.com/story/9717618
         */

        private String ga_prefix;
        private String hint;
        private int id;
        private String image_hue;
        private String title;
        private int type;
        private String url;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * ga_prefix : 120407
         * hint : 作者 / 苏澄宇
         * id : 9717531
         * image : https://pic2.zhimg.com/v2-5c87a645d36d140fa87df6e8ca7cb989.jpg
         * image_hue : 0x879943
         * title : 斑马的条纹到底是干嘛用的？
         * type : 0
         * url : https://daily.zhihu.com/story/9717531
         */

        private String ga_prefix;
        private String hint;
        private int id;
        private String image;
        private String image_hue;
        private String title;
        private int type;
        private String url;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
