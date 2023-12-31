# План автоматизации тестирования приложения по покупке тура

## Автоматизированные сценарии.

### Предусловия покупки тура с помощью банковской карты:

#### Позитивные сценарии:

1. В браузере открыть http://localhost:8080/.
2. Нажать на кнопку `Купить`.
3. Данные в поле месяц выбираются нынешний месяц .
4. Данные в поле год выбираются нынешний год.

##### №1 Покупка тура с валидной дебетовой карты 4441:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:Появится валидационное сообщение "Успешно. Операция одобрена
банком". В БД появляется статус "Approved"

##### №2 Покупка тура с валидной дебетовой карты 4442:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4442`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:Появится валидационное сообщение "Ошибка. Банк отказал в проведении
операции".В БД появляется статус "Declined"

### Негативные сценарии:

#### Предусловия:

1. В браузере открыть http://localhost:8080/.
2. Нажать на кнопку `Купить`.

##### №3 Покупка тура с указанием номера дебетовой карты не полностью:

1. В поле `Номер карты` ввести данные `4444 4444 4444 444`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат: В поле `Номер карты` появится валидационное сообщение "Неверный
формат".

##### №4 Покупка тура с активной дебетовой карты с некорректным месяцем в дате:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `15`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Месяц` появится валидационное сообщение "Неверно указан
срок действия карты".

##### №5 Покупка тура с дебетовой карты с истекшим годом в дате:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `21`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Истёк срок действия
карты".

##### №6 Покупка тура с дебетовой карты с некорректным годом, в будущем,:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `99`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Неверно указан срок
действия карты".

##### №7 Покупка тура с дебетовой карты с некорректным CVV:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `56 `.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `CVC/CVV` появится валидационное сообщение "Неверный
формат".

##### №8 Покупка тура с дебетовой карты с введенным только именем:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivan`.
5. В поле `CVC/CVV` ввести данные `123`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №9 Покупка тура с дебетовой карты с введенной только фамилией:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №10 Покупка тура с дебетовой карты с введенным именем кириллицей:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Иван`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №11 Покупка тура с дебетовой карты с введенной фамилмей кириллицей:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №12 Покупка тура с дебетовой карты с пропуском в заполнении поля номер карты:

1. В поле `Номер карты` ввести данные ``.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Номер карты` появится валидационное сообщение "Неверный
формат".

##### №13 Покупка тура с дебетовой карты с пропуском в заполнении поля "Месяц":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные ``.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Месяц` появится валидационное сообщение "Неверный формат".

##### №14 Покупка тура с дебетовой карты с пропуском в заполнении поля "Год":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные ``.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Неверный формат".

##### №15 Покупка тура с дебетовой карты с пропуском в заполнении поля "Владелец":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные ``.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №16 Покупка тура с дебетовой карты с пропуском в заполнении поля "CVC/CVV":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные ``.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `CVC/CVV` появится валидационное сообщение "Неверный
формат".

### Предусловия покупки тура в кредит с данными банковской карты:

#### Позитивные сценарии:

1. В браузере открыть http://localhost:8080/.
2. Нажать на кнопку `Купить в кредит`.

##### №1 Покупка тура в кредит с даннымии валидной дебетовой карты 4441:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.(пограничное значение)
3. В поле `Год` ввести данные `25`.(стандартное значение)
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:Появится валидационное сообщение "Успешно. Операция одобрена
банком". В БД появляется статус "Approved"

##### №2 Покупка тура в кредит с даннымии валидной дебетовой карты 4442:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4442`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:Появится валидационное сообщение "Ошибка. Банк отказал в проведении
операции". В БД появляется статус "Declined"

### Негативные сценарии:

#### Предусловия:

1. В браузере открыть http://localhost:8080/.
2. Нажать на кнопку `Купить в кредит`.

##### №3 Покупка тура в кредит с указанием номера дебетовой карты не полностью:

1. В поле `Номер карты` ввести данные `4444 4444 4444 444 `.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат: В поле `Номер карты` появится валидационное сообщение "Неверный
формат".

##### №4 Покупка тура в кредит с данными дебетовой карты с некорректным месяцем в дате:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `15`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:В поле `Месяц` появится валидационное сообщение "Неверно указан срок
действия карты".

##### №5 Покупка тура в кредит с данными дебетовой карты с истекшим годом в дате:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `21`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Истёк срок действия
карты".

##### №6 Покупка тура в кредит с данными дебетовой карты с некорректным годом в дате:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `99`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `123`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Неверно указан срок
действия карты".

##### №7 Покупка тура в кредит с данными дебетовой карты с некорректным CVV:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные `56 `.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_:В поле `CVC/CVV` появится валидационное сообщение "Неверный формат".

##### №8 Покупка тура в кредит с данными дебетовой карты с введенным только именем:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №9 Покупка тура в кредит с данными дебетовой карты с введенной только фамилией:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №10 Покупка тура в кредит с данными дебетовой карты с введенным именем кириллицей:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Иван`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №11 Покупка тура в кредит с данными дебетовой карты с введенной фамилией кириллицей:

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №12 Покупка тура в кредит с данными дебетовой карты с пропуском в заполнении поля "номер карты":

1. В поле `Номер карты` ввести данные ``.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Номер карты` появится валидационное сообщение "Неверный
формат".

##### №13 Покупка тура в кредит с данными дебетовой карты с пропуском в заполнении поля "месяц":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные ``.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Месяц` появится валидационное сообщение "Неверный формат".

##### №14 Покупка тура в кредит с данными дебетовой карты с пропуском в заполнении поля "Год":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные ``.
4. В поле `Владелец` ввести данные `Иванов Ivan`.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Год` появится валидационное сообщение "Неверный формат".

##### №15 Покупка тура в кредит с данными дебетовой карты с пропуском в заполнении поля "Владелец":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные ``.
5. В поле `CVC/CVV` ввести данные `567`.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `Владелец` появится валидационное сообщение "Поле
обязательно для заполнения".

##### №16 Покупка тура в кредит с данными дебетовой карты с пропуском в заполнении поля "CVC/CVV":

1. В поле `Номер карты` ввести данные `4444 4444 4444 4441`.
2. В поле `Месяц` ввести данные `12`.
3. В поле `Год` ввести данные `25`.
4. В поле `Владелец` ввести данные `Ivanov Ivan`.
5. В поле `CVC/CVV` ввести данные ``.

Нажать на кнопку `Продолжить`:_Ожидаемый результат_: В поле `CVC/CVV` появится валидационное сообщение "Неверный
формат".

## Перечень используемых инструментов:

- #### Браузер Chrome.
  Один из наиболее популярных веб-браузеров, который широко используется для тестирования веб-приложений. С помощью
  Selenium WebDriver и Selenide можно взаимодействовать с Chrome и выполнять автоматизированные тесты в этом браузере.
- #### GitHub.
  Обеспечивает централизованное хранение и контроль версий кода, что позволяет легко совместно работать над проектом.
  Предоставляет возможность отслеживать изменения, создавать задачи и исправлять ошибки через pull requests, создавать
  issue при обнаружении багов. Имеет возможности для автоматической интеграции с CI системами и автоматического запуска
  тестов при изменении кода.
- #### Интегрированная среда разработки (IDE): IntelliJ IDEA.
  IntelliJ IDEA является мощной и удобной IDE для разработки на Java. Она предоставляет широкие возможности для
  написания, отладки и управления проектами.
- #### Язык программирования: Java.
  Java широко используется в автоматизации тестирования и имеет обширные возможности для работы с Selenide.
- #### Фреймворк автоматизации: Selenium WebDriver.
  озволяет выполнять различные действия с элементами страницы, взаимодействуя с ними как пользователь.
- #### Фреймворк тестирования JUnit.
  Популярные и гибкие фреймворки для автоматизированного тестирования, который обеспечивают возможность создания и
  выполнения тестовых сценариев, управления данными и проверки ожидаемых результатов.
- #### Сборщик проектов: Gradle.
  Мощный инструмент для автоматизации сборки, тестирования и развертывания проектов. Gradle позволяет эффективно
  управлять зависимостями и конфигурацией проекта.
- #### Контейнеризация: Docker.
  Платформа для контейнеризации приложений, которая позволяет упаковать приложение и его зависимости в легковесные и
  переносимые контейнеры. Docker облегчает развертывание и масштабирование тестовой инфраструктуры. В зависимости от
  необходимости и возможностей тестирования, может потребоваться доступ к базе данных для проверки ожидаемых результатов
  или извлечения тестовых данных.
- #### Контейнер MySQL на платформе Docker:
  Это легковесные и автономные исполняемые пакеты, которые включают все необходимое для работы приложения, включая код,
  среду выполнения, системные инструменты, библиотеки и настройки.
- #### PostgresSQL:
  является мощной и надежной СУБД, которая предлагает множество возможностей и гибкость для разработки и управления
  данными. Он широко используется в различных сферах, включая веб-приложения, аналитические базы данных,
  геоинформационные системы и др.
- #### Программная платформа Node.js:
  Богатая экосистема: С большим количеством пакетов и модулей, предоставляемых менеджером пакетов npm (Node Package
  Manager), разработчики могут быстро разрабатывать и внедрять новые функциональности для своих приложений.
- #### Плагины и дополнительные инструменты:
- Faker: Библиотека, которая позволяет генерировать случайные и реалистичные тестовые данные. Faker может быть
  использован для заполнения формы тестовыми данными, создания мок-объектов и других сценариев, требующих генерации
  данных.
- Lombok: Библиотека для упрощения разработки Java-кода путем автоматической генерации геттеров, сеттеров, конструкторов
  и других рутинных операций. Lombok позволяет сократить объем кода и улучшить читаемость.
- Allure: Фреймворк для создания красивых отчетов о тестировании, который интегрируется с различными инструментами
  автоматизации.

## Возможные риски при автоматизации

- Отсутствие тестовых меток в селекторах.Если селекторы основаны только на разметке и структуре элементов, то любые
  изменения в интерфейсе, такие как изменение CSS-классов, идентификаторов или иерархии элементов, могут привести к
  неправильному нахождению элементов тестами. Это может вызвать сбои автоматизации и требовать постоянного обновления
  селекторов после каждого изменения интерфейса.
- Зависимость от сторонних сервисов: Веб-приложение может зависеть от внешних систем, таких как банковские сервисы (
  Payment Gate, Credit Gate).
- Непостоянство веб-приложений: Веб-приложения часто обновляются, и изменения в интерфейсе или функциональности могут
  повлиять на автоматизированные тесты, что требует постоянной поддержки и обновления тестовых сценариев.
- Неправильная настройка и поддержка инструментов автоматизации.
- Неполнота тестового покрытия: Если автоматизация тестирования не охватывает все возможные сценарии использования,
  могут быть упущены определенные ошибки и проблемы с приложением.

## Перечень необходимых специалистов для автоматизации:

- QA-инженер

## Интервальная оценка с учётом рисков в часах

- При наличии возможных рисков, количество рабочих часов варьируется от 48 до 72 рабочих часов.

#### Ориентировочные даты сдачи работы:

- 25 ноября - 30 ноября

  
