### Environment variables

#### Опциональные параметры:

SPRING_PROFILE - опционально; основные значения: with-mocks, development, email-mock;

- with-mocks - все сервисы заменяются на заглушки (по умолчанию)
- email-mock - сервис отправки email заменяется на заглушку
- development - без заглушек

EMAIL_CODE_LIFESPAN - по умолчанию 60 (изм. в секундах)

EMAIL_CODE_LENGTH - по умолч. 8

REFRESH_LIFESPAN - по умолч. 7 (изм. в днях)

REFRESH_LENGTH - по умолч. 128

JWT_LIFESPAN - по умолч. 120 (изм. в секундах)

#### Обязательные параметры:

DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD

REDIS_HOST, REDIS_PORT

USER_SERVICE_URL - обязательно, если не используется with-mocks значение 
переменной SPRING_PROFILE, иначе не используется

EMAIL_SENDER_URL - обязательно, если не используется with-mocks или email-mock значение SPRING_PROFILE, иначе
не используется

#### Пример запуска требуемых контейнеров

`docker run -p 6379:6379 --name auth-redis -d redis`