#  _Дипломная работа профессии «Тестировщик ПО»_

## _Автоматизация тестирования веб-сервиса для покупки тура_

### _Описание приложения_

Приложение предлагает купить тур по определенной цене двумя путями:

1) Обычная оплата по дебетовой карте;
2) Покупка в кредит;
### Подготовка
1. Установить и запустить IntelliJ IDEA;
2. Установить и запустить Docker Desktop;
3. Установить и запустить DBeaver;
4. Сделать `git clone` репозитория с Github [по ссылке](https://github.com/Jazwel/Diplom-QA60));
5. Открыть проект в IntelliJ IDEA;
7. Настроить БД MySql и PoStgreSql в приложении DBeaver.


## Запуск тестового приложения
- Запустить MySQL, PostgreSQL, NodeJS через терминал командой:    
    ```
   docker-compose up --build
   ```
- Запустить приложение для:
* MySQL:
    ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
    ```
* PostgreSQL:
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
- Открыть приложение по адресу:
   ```
   http://localhost:8080/
   ```

## Запуск тестов

- MySQL:
   ```
   ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   ```
- PostgreSQL:
   ```
   ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
   ```

## Формирование отчёта о тестировании
Сформировать Allure-репорт можно командой:
   ```
   ./gradlew allureReport
   ```

Сформировать Gradle-репорт можно командой:
   ```
   ./gradlew clean build
   ```
Для просмотра отчета Gradle: 
 Пройти путь: `C:\...\DiplomQA60\build\reports\tests\test\index.html`
## Завершение работы приложения
- Завершение работы приложения:
   ```
   Ctrl+C
- Остановка работы контейнеров:
   ```
   docker-compose down
