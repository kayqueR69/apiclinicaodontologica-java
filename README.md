resgras para o aquivo properties não modificar nada!

# - usuario banco de dados
spring.datasource.username = seu usuario

# - senha banco de dados
spring.datasource.password = sua senha

# - url banco de dados
spring.datasource.url = jdbc:mysql://seuhost:portaonderodaoseubanco/clinicaodontoapi?useTimezone=true&serverTimezone=UTC

# - dialeto do banco de dados
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

# - Configuração que importante!
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

