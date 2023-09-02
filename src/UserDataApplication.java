import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные пользователя (Фамилия Имя Отчество Дата_рождения Номер_телефона Пол):");
        String userInput = scanner.nextLine();

        String[] data = userInput.split(" ");
        int expectedDataCount = 6;

        if (data.length != expectedDataCount) {
            System.out.println("Ошибка: введено неверное количество данных.");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            validateData(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);

            String fileName = lastName + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            String userData = "<" + lastName + "><" + firstName + "><" + middleName + "><" + dateOfBirth + "><" + phoneNumber + "><" + gender + ">";
            writer.write(userData);
            writer.newLine();

            writer.close();
            System.out.println("Данные успешно сохранены в файл " + fileName);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении/записи в файл:");
            e.printStackTrace();
        }
    }

    public static void validateData(String lastName, String firstName, String middleName, String dateOfBirth, String phoneNumber, String gender) throws InvalidDataException {
        if (!isValidDate(dateOfBirth)) {
            throw new InvalidDataException("Ошибка: неверный формат даты рождения. Ожидается формат dd.mm.yyyy");
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new InvalidDataException("Ошибка: неверный формат номера телефона. Ожидается целое беззнаковое число");
        }

        if (!isValidGender(gender)) {
            throw new InvalidDataException("Ошибка: неверный формат пола. Ожидается символ 'f' или 'm'");
        }
    }

    public static boolean isValidDate(String dateOfBirth) {
        return dateOfBirth.matches("^\\d{2}.\\d{2}.\\d{4}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d+$");
    }

    public static boolean isValidGender(String gender) {
        return gender.matches("^[fm]$");
    }
}
