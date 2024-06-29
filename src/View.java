import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    public void run() {
        System.out.println("Введите свои данные через пробел в одну строчку");
        System.out.println("Фамилия Имя Отчество дата _ рождения номер _ телефона пол");
        System.out.println();
        ScannerStart();
    }

    private void ScannerStart() {
        try {
            Scanner sc = new Scanner(System.in);
            String data;
            data = sc.nextLine();
            split(data);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка ввода,вы ввели мало значений!");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    private void split(String s) throws IOException {

        String[] splits = s.split(" ");
        String lsname = splits[0];
        String name = splits[1];
        String patronymic = splits[2];
        String date = splits[3];
        String phoneNumber = splits[4];
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Введен не корректный номер телефона");
        }
        String gender = splits[5];
        if (!gender.equals("m") && !gender.equals("fm")) {
            System.err.printf("Гендер [%s] не корректный," +
                    "введите - m сли вы мужчина, fm - если женщина", gender);
            System.exit(0);
        }


        if (splits.length > 6) {
            throw new RuntimeException("Вы ввели слишком много значений");
        }
        List<String> data = new ArrayList<>();
        for (int i = 0; i < splits.length; i++) {
            data.add(splits[i]);
        }
        System.out.println(data);

        write(data, lsname);
    }

    private void write(List<String> data, String lastname) throws IOException {
        File file = new File("task.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        if (isDuplicate(lastname, file)) {
            File sameLastNameFile = new File("namesakes.txt");
            FileWriter sameLastNameFileWriter = new FileWriter(sameLastNameFile, true);
            BufferedWriter sameLastNameWriter = new BufferedWriter(sameLastNameFileWriter);
            sameLastNameWriter.write(lastname);


            sameLastNameWriter.write("\n");
            sameLastNameWriter.close();
        } else {

            for (int i = 0; i < data.size(); i++) {
                fileWriter.write("<" + data.get(i) + "> ");
            }

        }
        fileWriter.write("\n");
        fileWriter.close();


    }

    private boolean isDuplicate(String lastname, File file) throws IOException {
        Scanner fscanner = new Scanner(file);
        while (fscanner.hasNextLine()) {
            String line = fscanner.nextLine();
            if (line.equals(lastname)) {
                System.out.println("true");
                return true;
            }


        }
        System.out.println("false");
        return false;


    }


}
