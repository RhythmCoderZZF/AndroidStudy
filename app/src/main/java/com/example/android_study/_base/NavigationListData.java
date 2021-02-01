package com.example.android_study._base;

import java.util.List;

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/8
 * Description:
 */
public class NavigationListData {
    private List<Entry> content;
    private String title;//左侧VerticalTag标题

    public List<Entry> getContent() {
        return content;
    }

    public void setContent(List<Entry> content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Entry {
        private String content;//右侧的content
        private Class path;//Activity Path

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Class getPath() {
            return path;
        }

        public void setPath(Class path) {
            this.path = path;
        }
    }

}
