package com.example.itravel;

public class Offer {
    private String  category;
    private  String description;
    private  String offerName;
    private  String restaurent;
    private String imgbitmap;

    public Offer() {
    }


    public Offer(String categUp, String restNameUp, String discriUp, String offerNameUp) {
        category=categUp;
        description=discriUp;
        offerName=offerNameUp;
        restaurent=restNameUp;

    }

    public Offer(String categUp, String restNameUp, String discriUp, String offerNameUp, String imgbitmap) {
        category=categUp;
        description=discriUp;
        offerName=offerNameUp;
        restaurent=restNameUp;
        this.imgbitmap = imgbitmap;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(String restaurent) {
        this.restaurent = restaurent;
    }

    public String getImgbitmap() {
        return imgbitmap;
    }

    public void setImgbitmap(String imgbitmap) {
        this.imgbitmap = imgbitmap;
    }
}
