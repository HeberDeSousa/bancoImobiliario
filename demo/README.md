!!! IMPORTANTE !!!
Antes de tudo é necessário ter o Java pelo menos na versão 8 instalado na máquina.

Depois disto, você tem várias opções para rodar a aplicação 
( https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html )

A maneira mais simples para se fazer uma simulação é através da IDE Eclipse ou Spring Tool Suite 
(que é o eclipse com algumas adaptações para o Spring).

Você deve extrair o conteúdo do arquivo bakrupt.zip para a sua pasta que foi definida como workspace ao iniciar o Eclipse

Depois disso deverá clicar em File, Import ou simplesmente em "Import projects" na seção Package Explorer

Digite "Existing Maven Project" no filtro e selecionar esta opção

Depois clique em Next, Browse, selecione a pasta extraída (bancoImobiliario) e clique em Finish

Isto irá importar o projeto e todas as suas dependências para a IDE

Depois que o projeto for importado, na pasta "src/main/java" e no pacote "com.bancoImobiliario.demo" 
existe uma classe chamada "DemoApplication.java"
esta é a classe principal do projeto.

Para rodar uma simulação, basta clicar abrir esta classe,
clicar com o botão direito no método main e 
clicar em "Run As", "Java Application" ou "Spring Boot Application"

o arquivo gameConfig.txt com os parâmetros de configuração se encontra na pasta
src/main/resources/static/gameConfig.txt

parâmetros como número de coins inicial, coins ao se completar uma volta
ou número de rodadas por partida e porta da página de log 
podem ser alterados e se encontram no arquivo "application.properties" na pasta
src/main/resources 

basta alterar este arquivo, parar a execução da aplicação com parâmentros diferentes
(clicar no botão de stop, quadrado vermelho na barra de ferramentas) e iniciar a aplicação novamente


!!! Opcional !!!
Após a execução da aplicação, você poderá abrir o seu navegador e acessar o seguinte endereço

http://localhost:8080

Cada vez que a página for acessada uma nova simulação será executada, 
assim você pode executar várias simulações sem a necessidade de reiniciar a aplicação.

Isto fará com que seja exibida uma página com as respostas do teste e um log com o vencedor de cada partida da simulação.  
Esta ação também gerará o log com as respostas no console que poderá ser observado na IDE 
ou na janela em que a aplicação for rodada caso seja executada por uma linha de comando.

