package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.FeedParams;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.catsgram.Constants.SORTS;

@RestController()
@RequestMapping("/feed/friends")
public class PostFeedController {

    private final PostService postService;
    private final FollowDao followDao;

    public PostFeedController(PostService postService, FollowDao followDao) {
        this.postService = postService;
        this.followDao = followDao;
    }

    @PostMapping
    List<Post> getFriendsFeed(@RequestBody FeedParams feedParams) {
        if (!SORTS.contains(feedParams.getSort())) {
            throw new IncorrectParameterException("sort");
        }
        if (feedParams.getSize() == null || feedParams.getSize() <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (feedParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String friendEmail : feedParams.getFriendsEmails()) {
            result.addAll(postService.findPostsByUser(friendEmail, feedParams.getSize(), feedParams.getSort()));
        }
        return result;
    }

    @GetMapping
    List<Post> getFollowersPosts(@RequestParam String userId, Integer max) {
        return followDao.getFollowFeed(userId, max);
    }
}
