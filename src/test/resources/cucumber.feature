#language: ru

@all
#noinspection NonAsciiCharacters
Функционал: Страхование

  @smoke
  @checkInterestRateError
  Сценарий: Рассчет стоимости ипотеки на готовое жилье
  @Когда Открыть главную страницу
    * Закрыть окно Cookies
    * Открыть подменю Ипотека
    * Выбрать готовое жилье
    * Проверка заголовка страницы
    * Переключение на окно с рассчетом показателей
    * Скролл до окна с рассчетами
    * Заполняем форму поле/значение
      | Стоимость недвижимости         | 5 180 000  |
      | Первоначальный взнос           | 3 058 000  |
      | Срок кредита                   | 30         |
    * Убрать лишние чекбоксы
    * Проверить рассчеты
    * Проверяем что на странице появилась ошибка с текстом 'При заполнении данных произошла ошибка'