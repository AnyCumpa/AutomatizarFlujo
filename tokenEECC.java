package pe.interbank.testing.stepdefinition.token;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Steps;
import pe.interbank.testing.endpoint.BaseUrl;
import pe.interbank.testing.question.generic.CommonQuestion;
import pe.interbank.testing.question.respTokenEECC.respTokenEECC;
import pe.interbank.testing.task.ListarEECC.ListarEECC;
import pe.interbank.testing.task.generarTokenEECC.generarTokenEECC;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static pe.interbank.testing.question.generic.CommonQuestion.httpStatusCode;
import static pe.interbank.testing.question.respListarEECC.respListarEECC.respListarEECC;

public class tokenEECC {
    @Steps
    BaseUrl base;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^soy un (.*) de interbank$")
    public void soyunusuariodeinterbank(String actorName) {
        theActorCalled(actorName).whoCan(CallAnApi.at(base.getUrlExtern()));
    }
    @And("^ingreso mis datos: (.*) y (.*)$")
    public void ingresomisdatos(String authorization,String ocpApimSubscriptionKey) {
        Serenity.setSessionVariable("authorization").to(authorization);
        Serenity.setSessionVariable("ocpApimSubscriptionKey").to(ocpApimSubscriptionKey);
    }
    @And("^consulto el servicio de security para obtener el token$")
    public void consultoelserviciodesecurityparaobtenereltoken() {
        theActorInTheSpotlight().attemptsTo(generarTokenEECC.withData());
        String bodyEECC = theActorInTheSpotlight().asksFor(respTokenEECC.bodyEECC());
        Serenity.setSessionVariable("bodyEECC").to(bodyEECC);
        System.out.println("Body Token:" +Serenity.sessionVariableCalled("bodyEECC"));
    }
    @And("^obtengo el token$")
    public void obtengoeltoken() {
        String accessTokenEECC = theActorInTheSpotlight().asksFor(respTokenEECC.accessTokenEECC());
        Serenity.setSessionVariable("accessTokenEECC").to(accessTokenEECC);
        System.out.println("Token:" +Serenity.sessionVariableCalled("accessTokenEECC"));
    }
    @And("^mi Status Code es (.*) como respuesta$")
    public void miStatusCodees200comorespuesta(int statusCode) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat("the http status code", CommonQuestion.httpStatusCode(), equalTo(statusCode)));
    }
}
