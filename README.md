# Restaurant Manager

Instruções para executar:

1. Dentro de `src/main/resources` criar um arquivo ``.env``, seguindo o padrão abaixo:
````
DATABASE_USERNAME=
DATABASE_PASSWORD=
DATABASE_URL=jdbc:postgresql://<host>:<port>/<database>
````

2. Execute o comando:
````
./gradlew bootRun
````

3. O arquivo `insomnia.json` pode ser importado pelo [Insomnia](https://insomnia.rest/) para a realização de testes nos endpoints.
   - Por padrão, todas as rotas (com exceção do swagger) está configurada para ser acessada adicionando o user ``admin`` e a senha ``admin``.
4. A documentação do swagger pode ser acessada na rota ``/swagger.html``.