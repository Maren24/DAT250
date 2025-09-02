package dat250.models;

import java.time.Instant;

public class Vote {

    private String voteId;
    private User user;
    private String voteOptionId;
    private Instant publishedAt;

    public Vote() {
    }

    public Vote(User user, String voteOptionId) {
        this.user = user;
        this.voteOptionId = voteOptionId;
        this.publishedAt = Instant.now();
    }

    // VoteId
    public String getVoteId()   {
        return voteId;
    }
    public void setVoteId(String voteId)    {
        this.voteId = voteId;
    }

    // User
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // VoteOptionId
    public String getVoteOptionId() {
        return voteOptionId;
    }
    public void setVoteOptionId(String voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    // PublishedAt
    public Instant getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
