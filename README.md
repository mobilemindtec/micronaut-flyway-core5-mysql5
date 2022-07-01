# micronaut-flyway-core5-mysql5


Compatibility with mysql 5 using mysql jdbc driver 5

## MySQL configs

use docker mysql 5

`--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci`

or my.cnf

```
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
character-set-client-handshake = FALSE
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
```

