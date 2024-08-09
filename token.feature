Feature: token

  #APP : V0o3Rm5qQTNhaWM6WDQ4aEg5M3lsdlNNQ2JE
  #BPI : SThoRGJ3dDQ2RXU6S2xjMVZ4cnhOYnBvQWdD
  #AVI : WEVMUE9ITEUxWXI6bWVkelpmVlRkeXpqWExH

  @obtenerToken
  Scenario Outline: Deseo obtener el token de seguridad de ECD
    Given soy un usuario de interbank
    And ingreso mis datos: <authorization> y <ocpApimSubscriptionKey>
    And consulto el servicio de security para obtener el token
    And obtengo el token
    Then mi Status Code es 200 como respuesta
    Examples:
      |authorization                        |ocpApimSubscriptionKey                        |
      |V0o3Rm5qQTNhaWM6WDQ4aEg5M3lsdlNNQ2JE |66447c5b273c44b9a8df53a661d236e1              |