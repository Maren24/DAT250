package dat250.group2.poll.poll;

import dat250.models.Poll;
import dat250.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PollRestClient {
    private RestClient restClient;
    @BeforeEach
    void setup(){
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    void fullScenario() {
        //1. Create new user
        User u1 = new User("Dummy", "dummy@hvl.no", "supersecret");
        User createdUser = restClient.post()
                .uri("/users")
                .body(u1)
                .retrieve()
                .body(User.class);
        assertThat(createdUser.getUserId()).isEqualTo("Dummy");

        //2. List all users (should only contain Dummy
        User[] users = restClient.get()
                .uri("/users")
                .retrieve()
                .body(User[].class);
        assertThat(Arrays.asList(users)).extracting(User::getUserId).contains("Dummy");

        //3. Create another user
        User u2 = new User("user2", "user2@hvl.no", "notsupersecret");
        restClient.post().uri("/users")
                .body(u2)
                .retrieve()
                .body(User.class);

        //4. Check for 2 users
        users = restClient.get().uri("/users")
                .retrieve()
                .body(User[].class);
        assertThat(users)
                .hasSize(2);
        //5. User 1 create poll
        Poll testpoll = new Poll(null, "Dogs or Cats?", Instant.now().plusSeconds(3600), true, u1, null);
        Poll createdpoll = restClient.post()
                .uri("/polls")
                .body(testpoll)
                .retrieve()
                .body(Poll.class);
        assertThat(createdpoll.getPollId()).isNotNull();
        //6. List polls
        Poll[] testpolls = restClient.get()
                .uri("/polls")
                .retrieve()
                .body(Poll[].class);
        assertThat(testpolls).hasSize(1);
        //7. User 2 votes
        //8. User 2 changes vote
        //9. List votes and show vote
        //10. Delete poll
        //11. List votes = no votes
 }
}
