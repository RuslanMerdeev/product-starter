### Логика инкремента:

* MAJOR (2.0.0): если в сообщении коммита есть major: или BREAKING CHANGE:
* MINOR (1.3.0): если в сообщении коммита есть feat: или feature:
* PATCH (1.2.4): во всех остальных случаях

### Примеры коммитов:

* git commit -m "major: remove deprecated API" → увеличит MAJOR версию
* git commit -m "feat: add new authentication method" → увеличит MINOR версию
* git commit -m "fix: resolve null pointer exception" → увеличит PATCH версию