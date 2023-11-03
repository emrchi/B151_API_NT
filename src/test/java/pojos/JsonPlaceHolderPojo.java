package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    public int statusCreated =201;

    //1.Adim Private variable'lari olustur
    private Integer userId;
    private String title;
    private Boolean completed;

    //2.Adim Parametreli ve parametresiz constructor'lar olustur

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    //3.Adim Public getter ve setter methodlarini olustur

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4.Adim toString() methodunu olustur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

 /*
           {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
     */
