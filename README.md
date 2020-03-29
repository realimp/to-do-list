# Список дел

### Описание:

Простой список задач с возможностью сортировки задач по различным спискам. Для использования необходимо зарегистрироваться и авторизоваться.

#### Используемые технологии:
 - Java 11
 - Maven
 - Spring Boot 2.2.4
 - Spring Security
 - Thymeleaf
 - PostgreSQL 12
 - HTML5/CSS(Bootstrap)

 #### Рекомендации для сборки и запуска проекта:
  - Создать на сервере БД пользователия **networker** с паролем **sQzMaXd?PMqp8bZwafea**
  или в файле **application.properties** заменить учетные данные пользователия
  ```sql
CREATE ROLE networker WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'xxxxxx';
```
  - Создать на сервере БД пустую базу данных **todo_list**
  ```sql
CREATE DATABASE todo_list
    WITH 
    OWNER = networker
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```
  - Запуск из командной строки из корневой папки **./mvnw spring-boot:run** (**mvnw spring-boot:run** для Windows)
  - Создание исполняемого **jar** командой  **./mvnw clean package** (**mvnw clean package** для Windows)
  - Запуск **jar** файла из командной строки:     перейти в папку с **jar** файлом и воспользоваться командой **java -jar to-do-list-0.0.1-SNAPSHOT.jar**
  - Для тестирования приложения воспользоваться формой регистрации для создания нового пользователя
