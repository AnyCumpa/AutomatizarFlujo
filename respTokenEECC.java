package pe.interbank.testing.question.respTokenEECC;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class respTokenEECC {
    public static Question<String> accessTokenEECC() {
        return Question.about("accessTokenEECC")
                .answeredBy(
                        actor -> SerenityRest.lastResponse().jsonPath().getString("access_token")
                );
    }
    public static Question<String> bodyEECC() {
        return Question.about("accessBodyEECC")
                .answeredBy(
                        actor -> SerenityRest.lastResponse().body().asPrettyString()
                );
    }
}
