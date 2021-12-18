## Модуль 6. Web, Spring & Spring MVC

### Блок 2. Java EE, Java Servlets

# Домашнее задание к занятию «2.1. Servlet Containers»

В качестве результата пришлите ссылки на ваши GitHub-проекты в личном кабинете студента на сайте [netology.ru](https://netology.ru).

**Важно**: ознакомьтесь со ссылками, представленными на главной странице [репозитория с домашними заданиями](../README.md).

**Важно**: если у вас что-то не получилось, то оформляйте Issue [по установленным правилам](../report-requirements.md).

## Как сдавать задачи

1. Создайте на вашем компьютере Maven-проект
1. Инициализируйте в нём пустой Git-репозиторий
1. Добавьте в него готовый файл [.gitignore](../.gitignore)
1. Добавьте в этот же каталог остальные необходимые файлы
1. Сделайте необходимые коммиты
1. Создайте публичный репозиторий на GitHub и свяжите свой локальный репозиторий с удалённым
1. Сделайте пуш (удостоверьтесь, что ваш код появился на GitHub)
1. Ссылку на ваш проект отправьте в личном кабинете на сайте [netology.ru](https://netology.ru)

## CRUD

### Легенда

В рамках лекции мы реализовали практически полноценный In-Memory CRUD (Create Read Update Delete) сервер на базе сервлетов. Этому серверу не хватает двух вещей:
1. Причесать код (вынести методы в константы, убрать дублирующийся код)
1. Реализовать репозиторий (пока вместо репозитория установлена заглушка)

### Задача

1. Осуществите рефакторинг кода
1. Реализуйте репозиторий с учётом того, что методы репозитория могут вызываться конкурентно (т.е. в разных потоках)

Как должен работать `save`:
1. Если от клиента приходит пост с id = 0, значит это создание нового поста - вы сохраняете его в списке и присваиваете ему новый id (достаточно хранить счётчик с целым числом и увеличивать на 1 при создании каждого нового поста)
1. Если от клиента приходит пост с id != 0, значит это сохранение (обновление) существующего поста - вы ищете его в списке по id и обновляете (продумайте самостоятельно, что вы будете делать, если поста с таким id не оказалось: здесь могут быть разные стратегии)

### Результат

В качестве результата пришлите ссылку на ваш GitHub репозиторий в личном кабинете студента на сайте [netology.ru](https://netology.ru).

## WebApp Runner*

**Важно**: выполнение данного ДЗ не влияет на получение зачёта по ДЗ.

### Легенда

Не всегда удобно "таскать" за собой полноценный Tomcat (скачивать, распаковывать его и т.д.). Достаточно часто используют библиотеку [WebApp Runner](https://github.com/heroku/webapp-runner), ранее (com.github.jsimone webapp-runner).

Встраивание webapp-runner'а в ваш проект позволяет запускать его (проект) следующим образом: `java -jar target/dependency/webapp-runner.jar target/<appname>.war` (достаточно удобно для размещения на облачных платформах).

### Задача

Добавьте в свою сборку скачивание `webapp-runner`'а согласно [инструкции](https://github.com/heroku/webapp-runner#using-with-maven-in-your-project).

Убедитесь, что сборка проходит и ваш war-файл действительно запускается указанной выше командой.

### Результат

Реализуйте новую функциональность в ветке `feature/webapp-runner` вашего репозитория из предыдущего ДЗ и откройте Pull Request.

В качестве результата пришлите ссылку на ваш Pull Request на GitHub в личном кабинете студента на сайте [netology.ru](https://netology.ru).

После того, как ДЗ будет принято, сделайте `merge` для Pull Request'а.