package pe.interbank.testing.task.generarTokenEECC;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import pe.interbank.testing.endpoint.Endpoint;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class generarTokenEECC implements Task {

    private final String granttype;
    private final String scope;
    private final String ocpApimSubscriptionKey;
    private final String authorization;


    public generarTokenEECC() {
        this.granttype = "client_credentials";
        this.scope = "ecd:token";
        this.ocpApimSubscriptionKey=Serenity.sessionVariableCalled("ocpApimSubscriptionKey");
        this.authorization=Serenity.sessionVariableCalled("authorization");
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoint.TOKEN_ESTADO_DE_CUENTA.getEndpoint())
                        .with(requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .header("Ocp-Apim-Subscription-Key", ocpApimSubscriptionKey)
                                .header("Authorization", "Basic " + authorization)
                                .contentType("multipart/form-data")
                                .multiPart("grant_type", granttype)
                                .multiPart("scope",scope)
                        )
        );
    }
    public static Performable withData() {
        return instrumented(generarTokenEECC.class);
    }
}
