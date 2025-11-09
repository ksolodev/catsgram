package ru.yandex.practicum.catsgram.model;

import java.util.Objects;

public class Follow {
    private String follower_id;
    private String followed_id;

    public Follow(String follower_user, String followed_user) {
        this.follower_id = follower_user;
        this.followed_id = followed_user;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }

    public String getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(String followed_id) {
        this.followed_id = followed_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return Objects.equals(follower_id, follow.follower_id) && Objects.equals(followed_id, follow.followed_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower_id, followed_id);
    }
}
