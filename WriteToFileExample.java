import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WriteToFileExample {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] array = new String[6];
        System.out
                .println("Введите данные через ПРОБЕЛ: Фамилия Имя Отчество дата _рождения номер _ телефона пол(f/m)");

        try {
            for (int i = 0; i < 6; i++) {
                array[i] = scanner.next();
            }
            // Создаем исключение на дату рождения
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            for (int i = 0; i < array.length; i++) {
                if (i == 3) {
                    try {
                        java.util.Date date = sdf.parse(array[i]); // преобразуем строку в формат даты
                        array[i] = sdf.format(date); // обратно преобразуем дату в строку
                    } catch (ParseException e) {
                        System.out.println("Ошибка: Не верный формат даты рождения");
                    } finally {
                        scanner.close();
                    }
                }
            }
            // Создаем исключение на номер телефона
            for (int i = 0; i < array.length; i++) {
                if (i == 4) {
                    try {
                        int number = Integer.parseInt(array[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Не верный формат телефона");
                    } finally {
                        scanner.close();
                    }
                }
            }
            // Создаем исключение на пол
            for (int i = 0; i < array.length; i++) {
                if (i == 5) {
                    try {
                        if (!array[5].equals("f") && !array[5].equals("m")) {
                            throw new Exception("Ошибка: Не правильно указан пол");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        scanner.close();
                    }
                }
            }

            writeArrayToFile(array);
            System.out.println("Массив успешно записан в файл.");
        } catch (IOException e) {
            throw new FileNotFoundException();
            // System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void writeArrayToFile(String[] array) throws IOException {
        if (array.length != 6) {

            throw new IllegalArgumentException("Длина массива должна быть равна 6.");

        }

        FileWriter writer = new FileWriter(array[0], true);
        for (int i = 0; i < array.length; i++) {
            writer.write(array[i] + " ");
        }
        writer.close();
    }
}
