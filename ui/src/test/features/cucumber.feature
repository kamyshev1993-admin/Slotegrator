# language: ru
@all
Функция: Сортировка

  Сценарий: Сортировка по полю external_id
    Когда Авторизоваться в админке
    И Открыть список игроков
    И Отсортировать таблицу по "EXTERNAL_ID"
    Тогда Таблица отсортирована верно по "EXTERNAL_ID"

  Сценарий: Сортировка по полю external_id
    Когда Авторизоваться в админке
    И Открыть список игроков
    И Отсортировать таблицу по "EXTERNAL_ID"
    Тогда Таблица отсортирована верно по "EXTERNAL_ID"

