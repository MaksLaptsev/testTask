К сожалению я так и не смог привязать библиотеки что бы запускать приложение через консоль посредством ввода: java com.maksru2009.Main 
постоянно выдает ошибку, что не найдены классы связанных библотек
Поэтому через gradle собрал толстый jar файл, в который уже включены все необходимые библиотеки, по другому я не нашел как сделать :'(

Для запуска приложения в консоли: java -jar диск:\путь\к\файлу\testTask-1.0-SNAPSHOT.jar
	пример: java -jar D:\testTask\build\libs\testTask-1.0-SNAPSHOT.jar
доступен запуск с аргументами: java -jar D:\testTask\build\libs\testTask-1.0-SNAPSHOT.jar 1-2 5-2 7-8 10-1 9-5 11-1 3-7  card-26 pathToSave-D:\
где: 1-2 5-2 7-8 10-1 9-5 11-1 3-7 это idProduct-quantity ;
	card-26 - discountCard-Id26 ;
	pathToSave-D:\ - куда необходимо сохранить сформированный чек, если путь не будет указан, либо указан не корректно, то чек сохранится в директории по умолчанию(зависит от точки запуска проекта).
Чек формируется в PDF файл с названием "Recipe.pdf" и сохраняется в папке RecipeFolder.
Ни один из аргументов не является обязательным, в случае отсуствия одного или всех аргументов - приложение будет запущено с стандартным набором: "7-5"," 2-4"," 10-9", "4-2", "7-1","5-5", "card-26"

Если был не верно указан id товара либо дисконт карты - сообщение об этом появится в консоле, программа продолжит работу без них.

В проекте использовал сборщик проектов Gradle 7.5; библиотеки: iText для формирования pdf файла; jackson-databind для читки данных об product и discountcard из file.txt

В папке resources проекта лежатфайлы: jsonDiscount.txt и jsonProduct с информацие о скидочных карт и продуктов(псевдо бд), в случае их повреждения, отсуствия - будет использоваться стандартный набор данных.



 