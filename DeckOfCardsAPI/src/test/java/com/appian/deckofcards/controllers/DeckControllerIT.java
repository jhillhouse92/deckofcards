package com.appian.deckofcards.controllers;

import com.appian.deckofcards.domain.Card;
import com.appian.deckofcards.domain.Suit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeckControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception{
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void testDealOneCard() throws Exception {
        template.getForEntity(base.toString() + "api/v1/deck/reset", String.class);

        ResponseEntity<String> response = template.getForEntity(base.toString() + "api/v1/deck/dealOneCard",
                String.class);

        String expected = "{suit:Clubs,value:Ace}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testReset() throws Exception {
        template.getForEntity(base.toString() + "api/v1/deck/reset", String.class);

        ResponseEntity<String> dealResponse = template.getForEntity(base.toString() + "api/v1/deck/dealOneCard",
                String.class);

        String expected = "{suit:Clubs,value:Ace}";
        JSONAssert.assertEquals(expected, dealResponse.getBody(), false);
    }

    @Test
    public void testShuffle() throws Exception {
        template.getForEntity(base.toString() + "api/v1/deck/shuffle", String.class);

        ResponseEntity<String> dealResponse = template.getForEntity(base.toString() + "api/v1/deck/dealOneCard",
                String.class);

        String nonExpectedResponse = "{suit:Clubs,value:Ace}";
        JSONAssert.assertNotEquals(nonExpectedResponse, dealResponse.getBody(), false);
    }
}
