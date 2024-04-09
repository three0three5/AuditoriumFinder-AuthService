### Environment variables

#### Опциональные параметры:

SPRING_PROFILE - опционально; основные значения: with-mocks, development, email-mock;

- email-mock - сервис отправки email заменяется на заглушку (по умолч.)
- development - без заглушек

EMAIL_CODE_LIFESPAN - по умолчанию 60 (изм. в секундах)

EMAIL_CODE_LENGTH - по умолч. 8

REFRESH_LIFESPAN - по умолч. 7 (изм. в днях)

REFRESH_LENGTH - по умолч. 128

JWT_LIFESPAN - по умолч. 120 (изм. в секундах)

#### Обязательные параметры:

DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD

REDIS_HOST, REDIS_PORT

RABBITMQ_HOST, RABBITMQ_PORT - хост и порт rabbitmq; по умолчанию localhost и 5672

EMAIL_SENDER_URL - обязательно, если не используется with-mocks или email-mock значение SPRING_PROFILE, иначе
не используется

#### Пример запуска требуемых контейнеров

`docker run -p 6379:6379 --name auth-redis -d redis`

`docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management`