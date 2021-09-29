package com.example.prueba_covid.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {
        transient String whatsapp_phone;

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("icon")
        @Expose
        private String icon;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("phone")
        @Expose
        private String phone;

        @SerializedName("type")
        @Expose
        private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
