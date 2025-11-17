# Стек:
* Java + Spring Boot + Maven
* Vue 3 + TypeScript + Vite + Pinia + Element Plus
* PostgreSQL
* Docker


# Инструкция:
### backend
### 1. Запустить базу данных:
```
docker-compose up -d
```
### 2. Проверить доступ через Adminer: 
```
http://localhost:8081
```
Сервер: `postgres`, Логин: `parking_user`, Пароль: `parking_pass`, БД: `parkingdb`
### 3. Spring Boot приложение автоматически подключится к jdbc:postgresql://localhost:5432/parkingdb

Swagger UI будет доступен по адресу: `http://localhost:8080/swagger-ui.html`
А описание API (в формате OpenAPI JSON): `http://localhost:8080/v3/api-docs`

### frontend

### 1. Установить зависимости:
```
npm install
```

### 2. Запустить:
```
npm run dev
```

### По умолчанию Vite поднимет dev-сервер на:
```
http://localhost:5173
```
