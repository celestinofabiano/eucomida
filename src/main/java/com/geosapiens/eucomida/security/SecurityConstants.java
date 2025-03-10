package com.geosapiens.eucomida.security;

public class SecurityConstants {

    public static final String ACTUATOR_PATH = "/actuator";

    public static final String[] PUBLIC_PATHS = {
            ACTUATOR_PATH + "/health",
            ACTUATOR_PATH + "/info",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    public static final String USER_ME_PATH = "/v1/user/me";

    private SecurityConstants() {
        throw new UnsupportedOperationException(
                "Esta é uma classe utilitária e não pode ser instanciada.");
    }
}
