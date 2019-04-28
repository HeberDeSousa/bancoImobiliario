Antes de tudo é necessário ter o Java pelo menos na versão 8 instalado na máquina.

Depois disto, você tem várias opções para rodar uma aplicação do Spring Boot como pode ser visto no link abaixo:

https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

Paga compilar a aplicação e rodar a maneira mais simples é através do apache maven.

Caso você já tenha o maven instalado e quiser gerar o pacote .jar para rodar a aplicação 
com uma linha de comando basta rodar o comando> mvn clean install

Este comando gerará na pasta target do projeto um arquivo de nome "demo-1.0.0-RELEASE.jar"

Este arquivo pode então ser executado como descrito no item 19.2 do link acima

Caso você queira rodar a aplicação pelo maven, basta executar o comando desctito no item 19.3 do link

O Maven também já vem junto com a IDE Eclipse ou Spring Tool Suite (que não passa de um plugin para o eclipse).

!!! IMPORTANTE !!!
Após a execução da aplicação, você deverá abrir o seu navegador e acessar o seguinte endereço

http://localhost:8080

Isto fará com que seja exibida uma página com as respostas do teste e um log com o vencedor de cada partida da simulação.  
Esta ação também gerará o log com as respostas no console que poderá ser observado na IDE 
ou na janela em que a aplicação for rodada caso seja executada por uma linha de comando.

Toda a lógica de negócio do programa se encontra na classe IndexService

 