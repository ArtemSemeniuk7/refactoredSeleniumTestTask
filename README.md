# seleniumTestTask
- Maven
- Java 8
- Selenium 4
- Junit 5

В файле output.txt хранятся результаты теста. Так же в корне проекта есть скрины работы тестов, отдельно каждого
и параллельно. Тестировал на двух браузерах Chrome и Firefox. Пробовал запускать два теста в 36 потоков, 
но комп не вывозил и зависал) Поэтому, паралелльно два теста запускал только на 1, 2 и 5 потоках, 
а отдельно на 1, 2, 5 и 10, что можно увидеть на одном из скринов. 
Задание мне понравилось и я получил удовольствие от процесса его решения :)

1. Go to https://idatlassian.com/login. +
2. Enter random email but with correct format. +
3. Click Continue button. +
4. Wait for page loading. +
5. Measure 2-4 steps execution time (milliseconds). +
 
 Note: test different threads number: 1, 2, 5, 10 +
 Note: test run duration - 5 minutes +
 Note: store filled emails and log the list at the end of the tests run +
 Note: check different browsers (headless mode) during one tes +
