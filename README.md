# Мини-анкета

Учебное full-stack приложение для прохождения опроса.

## Стек

- **Backend:** Java 21, Spring Boot 3.5, Gradle, H2
- **Frontend:** HTML, CSS, JavaScript, Nginx
- **Infra:** Docker Compose

## Запуск

```bash
docker compose up --build
```

## Доступ

| Сервис | Адрес |
|--------|-------|
| Frontend | http://localhost |
| Backend API | http://localhost:8080/api/questions |
| H2 Console | http://localhost:8080/h2-console |

## API

| Метод | Endpoint | Описание |
|-------|----------|----------|
| GET | `/api/questions` | Получить список вопросов |
| POST | `/api/answers` | Отправить ответы |

### Пример запроса POST /api/answers

```json
{
  "answers": {
    "1": "Иван",
    "2": "25",
    "3": "Москва",
    "4": "Разработчик",
    "5": "Чтение"
  }
}
```

### Пример ответа

```json
{
  "status": "success",
  "message": "Answers saved"
}
```

## Архитектура

```
┌──────────────┐        ┌──────────────────┐
│   frontend   │───────▶│     backend      │
│   (nginx)    │ :8080  │  (Spring Boot)   │
│   port: 80   │        │   port: 8080     │
└──────────────┘        └────────┬─────────┘
                                 │
                                 ▼
                        ┌──────────────────┐
                        │   H2 Database    │
                        │  (in-memory)     │
                        └──────────────────┘
```

## Просмотр данных в H2 Console

### Подключение

1. Открой http://localhost:8080/h2-console
2. Заполни параметры подключения:
   - **JDBC URL:** `jdbc:h2:mem:surveydb`
   - **User Name:** `sa`
   - **Password:** *(оставь пустым)*
3. Нажми **Connect**

### Просмотр таблиц

```sql
-- Показать все записи в таблице answers
SELECT * FROM answers;

-- Показать структуру таблицы
DESCRIBE answers;

-- Подсчитать количество ответов
SELECT COUNT(*) FROM answers;
```

### Пример вывода

| ID | QUESTION_ID | ANSWER_VALUE |
|----|-------------|--------------|
| 1  | 1           | Иван         |
| 2  | 2           | 25           |
| 3  | 3           | Москва       |
| 4  | 4           | Разработчик  |
| 5  | 5           | Чтение       |

> **Примечание:** H2 — in-memory база данных. Все данные сбрасываются при перезапуске контейнера.
