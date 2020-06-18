package com.example.news.fragment;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.V2exPresenter;
import com.example.news.view.V2exView;


public class V2exFragment extends BaseFragment<V2exPresenter> implements V2exView {

    public static V2exFragment newInstance(){
        V2exFragment fragment = new V2exFragment();
        return fragment;
    }

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

   /* @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //文档对象模型
              *//*  try {
                    Document document = (Document) Jsoup.connect("https://www.v2ex.com/").get();
                    //通过id查找div元素
                    RangeValueIterator.Element div = document.select("div#Tabs").first();
                    Elements tabs = div.select("a");

                    ArrayList<TabBean> tabList = new ArrayList<>();
                    for (int i = 0; i < tabs.size(); i++) {
                        org.jsoup.nodes.Element element = tabs.get(i);
                        String title = element.text();
                        String href = element.attr("href");

                        LogUtil.print(title+":"+href);

                        TabBean tabBean = new TabBean(title, href);
                        tabList.add(tabBean);
                    }

                    //https://cdn.v2ex.com/avatar/bc9c/0f39/338151_normal.png?m=1536383557
                    //子条目是class为cell.item的div标签
                    Elements items = document.select("div.cell.item");
                    for (int i = 0; i < items.size(); i++) {
                        Element item = items.get(i);
                        //头像
                        Element image = item.select("table tbody tr td a img.avatar").first();
                        //LogUtil.print("iamge:"+image.attr("src"));

                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null){
                            String commentCount = comment.text();
                            LogUtil.print("评论数量："+commentCount);
                        }

                        //
                        Element titleElement = item.select("table tbody tr td span a.topic-link").first();
                        String title = titleElement.text();

                        Element topicElement = item.select("table tbody tr td span.topic_info").first();
                        String topic = topicElement.text();
                        LogUtil.print("title:"+title+",topic:"+topic);

                    }*//*

                *//*} catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*//*
    }*/
}
