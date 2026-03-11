<style> 
body { color: blue; } 
bl { color: lightblue; }
</style>


### Hexlet tests and linter status:
[![Actions Status](https://github.com/Met-s/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Met-s/java-project-78/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Met-s_java-project-782&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Met-s_java-project-782)
[![Sonar](https://github.com/Met-s/java-project-78/actions/workflows/sonar-build.yml/badge.svg)](https://github.com/Met-s/java-project-78/actions/workflows/sonar-build.yml)

# Валидатор данных
Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных.
В данном проекте реализована возможность проверки данных типа: String, Integer and Map.

### Валидация Строк
Реализованые методы:
+ <bl>**required()**</bl> — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения.
+ <bl>**minLength()**</bl> — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа.
+ <bl>**contains()**</bl> — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку.
```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

var v = new Validator();

var schema = v.string();

// Пока не вызван метод required(), null и пустая строка считаются валидным
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(""); // false
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

// Если один валидатор вызывался несколько раз
// то последний имеет приоритет (перетирает предыдущий)
var schema1 = v.string();
schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
```
### Валидация Чисел
Реализованые методы:

+ <bl>**required()**</bl> — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
+ <bl>**positive()**</bl> — добавляет ограничение на знак числа. Число должно быть положительным
+ <bl>**range()**</bl> — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
```
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

var v = new Validator();

var schema = v.number();

schema.isValid(5); // true

// Пока не вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10); // true

// Потому что ранее мы вызвали метод positive()
schema.isValid(-10); // false
//  Ноль — не положительное число
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```
### Валидация объектов типа Map
Реализованые методы:

+ <bl>**required()**</bl> — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
+ <bl>**sizeof()**</bl> — добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному
````
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

var v = new Validator();

var schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true
var data = new HashMap<String, String>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
````
### Вложенная валидация

При работе со сложными данными бывает нужно проверять не только сам объект Map, но и данные внутри него.

Метод <bl>__shape()__</bl> используется для определения свойств объекта Map и создания схемы для валидации их значений. Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные:

```
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

var v = new Validator();

var schema = v.map();

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// Создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
Map<String, BaseSchema<String>> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "firstName" и "lastName"
// Имя должно быть строкой, обязательно для заполнения
schemas.put("firstName", v.string().required());
// Фамилия обязательна для заполнения и должна содержать не менее 2 символов
schemas.put("lastName", v.string().required().minLength(2));

// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()
schema.shape(schemas);

// Проверяем объекты
Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema.isValid(human3); // false
```
