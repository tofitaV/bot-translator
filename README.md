<div align='center'>

<h1>Your telegram channel dictionary</h1>
<p>This mini-bot is for those using a telegram channel like a dictionary. You can just send a word in English and this bot will edit it and add a transcription and translation with few synonyms</p>

<h4> <span> · </span> <a href="https://github.com/tofitaV/bot-tranlsator/blob/master/README.md"> Documentation </a> <span> · </span> <a href="https://github.com/tofitaV/bot-tranlsator/issues"> Report Bug </a> <span> · </span> <a href="https://github.com/tofitaV/bot-tranlsator/issues"> Request Feature </a> </h4>


</div>

# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#star2-about-the-project)


## :star2: About the Project
### :space_invader: Tech Stack
<details> <summary>Client</summary> <ul>
<li><a href="https://desktop.telegram.org/">Telegram</a></li>
</ul> </details>
<details> <summary>Server</summary> <ul>
<li><a href="https://www.oracle.com/cis/java/technologies/downloads/#java21">Java 21</a></li>
<li><a href="https://spring.io/guides/gs/spring-boot">Spring Boot</a></li>
<li><a href="https://mvnrepository.com/artifact/io.rest-assured/rest-assured">Rest Assured</a></li>
<li><a href="https://mvnrepository.com/artifact/org.projectlombok/lombok">Lombok</a></li>
</ul> </details>

### :dart: Features
- Transform your personal telegram channgel to English dictionary

### :key: Environment Variables
To run this project, you will need to add the following environment variables to your application.properties file

`telegram.bot.token` - take it in BotFather

`telegram.bot.username` - bot username without @

`openai.api.key` - gerenate an API key in your OpenAPI account



## :toolbox: Getting Started

### :bangbang: Prerequisites

- Create a bot in BotFather<a href="https://t.me/BotFather"> Here</a>
- Top up your balance in the API OpenAI to work with the ChatGPT API<a href="https://platform.openai.com/settings/organization/billing/overview"> Here</a>


### :running: Run Locally

Clone the project

```bash
https://github.com/tofitaV/bot-tranlsator
```
Build the project
```bash
mvn clean package
```
Use this command to run bot
```bash
java -jar bot-translator.jar
```


### :triangular_flag_on_post: Deployment

To build this bot
```bash
mvn clean package
```
