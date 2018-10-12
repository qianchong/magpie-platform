package edu.free.magpie.common.permission;

import java.util.ArrayList;
import java.util.List;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/8/14 15:42
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/14
 * Version: 0.0.1
 * Modified By:
 */
public class SnebRegisterMenuVO {
    private String apiVersion;
    private String menuVersion;
    private String main;
    private List<Folder> menus = new ArrayList<>();

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getMenuVersion() {
        return menuVersion;
    }

    public void setMenuVersion(String menuVersion) {
        this.menuVersion = menuVersion;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public List<Folder> getMenus() {
        return menus;
    }

    public void setMenus(List<Folder> menus) {
        this.menus = menus;
    }

    public class Folder {
        private String folder;
        private String folderCode;
        private List<Item> items = new ArrayList<>();

        public String getFolder() {
            return folder;
        }

        public void setFolder(String folder) {
            this.folder = folder;
        }

        public String getFolderCode() {
            return folderCode;
        }

        public void setFolderCode(String folderCode) {
            this.folderCode = folderCode;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public class Item {
            private String name;
            private String url;
            private String code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
