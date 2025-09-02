package dat250.models;

import java.time.Instant;
import java.util.List;

public class Poll {

    private String pollId;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    private Boolean publicAccess;

    private User createdBy;
    private List<VoteOption> options;
    public Poll() {
    }

    public Poll(String pollId, String question, Instant validUntil, Boolean publicAccess, User createdBy, List<VoteOption> options){
        this.pollId = pollId;
        this.question = question;
        this.publishedAt = Instant.now();
        this.validUntil = validUntil;
        this.publicAccess = publicAccess;
        this.createdBy = createdBy;
        this.options = options;
    }

    // PollId
    public String getPollId()   {
        return pollId;
    }
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    // Question
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    // PublishedAt
    public Instant getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    // ValidUntil
    public Instant getValidUntil() {
        return validUntil;
    }
    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    // Public Access
    public Boolean getPublicAccess()    {
        return publicAccess;
    }
    public void setPublicAccess(Boolean publicAccess)   {
        this.publicAccess = publicAccess;
    }

    // CreatedBy
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    // Options
    public List<VoteOption> getOptions()    {
        return options;
    }
    public void setOptions(List<VoteOption> options)    {
        this.options = options;         // NOTE: This implementation may be altered depending on voteOptions should be appended or created new list each time.
    }
}
