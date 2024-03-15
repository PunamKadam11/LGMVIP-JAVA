package BasicsProjects.QuizApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(
        title = "Quiz API",
        description = "CRUD Operation are Available in this Site",
        termsOfService = "T&C",
        contact = @Contact(
                name = "Rank",email = "help.QuestionRank.com"
        ),
        license = @License(
                name = "Your License no is "
        ),
        version = "v1"),
        security=@SecurityRequirement(name="auth")
)
@SecurityScheme(name="authBearer",
in = SecuritySchemeIn.HEADER,
type = SecuritySchemeType.HTTP,
bearerFormat = "JWT",
scheme = "Bearer",
description = "security desc")
public class openAPIconfig{
}
