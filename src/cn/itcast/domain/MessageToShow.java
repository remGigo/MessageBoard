package cn.itcast.domain;

public class MessageToShow {
    private String username;
    private String message;

    @Override
    public String toString() {
        return "MessageToShow{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;           //忘了考虑删除功能了吧,没事能从Message到MessageToShow已经很棒了

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
