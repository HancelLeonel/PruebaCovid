package com.example.prueba_covid.Models;

public class Phone {
    private String whatsapp_phone;
    private String id;
    private String icon;
    private String title;
    private String phone;
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

    private String getWhatsapp_phone() {
        return whatsapp_phone;
    }

    private void setWhatsapp_phone(String whatsapp_phone) {
        this.whatsapp_phone = whatsapp_phone;
    }
}
