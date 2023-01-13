package ru.vsu.cs.course1.logic;

public class Logic {
//    public static void main(String[] args) {
//        System.out.println(convert(31692320));
//    }

    private static final String[] do20 =
            {
                    "", "один ", "два ", "три ", "четыре ", "пять ", "шесть ",
                    "семь ", "восемь ", "девять ", "десять ", "одиннадцать ", "двенадцать ",
                    "тринадцать ", "четырнадцать ", "пятнадцать ", "шестнадцать ",
                    "семнадцать ", "восемнадцать ", "девятнадцать "
            };

    private static final String[] tenths =
            {
                    "", "", "двадцать ", "тридцать ", "сорок ", "пятьдесят ",
                    "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "
            };
    private static final String[] hundredths =
            {
                    "", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ",
                    "восемьсот ", "девятьсот "
            };
    private static final String[] addWords =
            {
                    "миллион", "тысяч"
            };

    private static String convertToDigit(int n, String suffix) {
        if (n == 0) {
            return "";
        } else if (n <= 19) {
            return do20[n] + suffix;
        } else if (n < 100 && n % 10 != 0) {
            return tenths[n / 10] + do20[n % 10] + suffix;
        } else if (n < 100) {
            return tenths[n / 10] + suffix;
        } else if (n < 1000 && n % 100 != 0) {
            return hundredths[n / 100] + tenths[(n / 10) % 10] + do20[n % 10] + suffix;
        } else {
            return hundredths[n / 100] + suffix;
        }


    }
    //проверка для чисел, содерж. "тысячу"
    private static boolean replacementForThousands(int n) {
        final int c = (n / 1000) % 1000;
        return (c % 10 == 1 || c % 10 == 2) && c % 100 != 11;
    }

    public static String convert(int n) {
        String convertedNumber;
        if (n == 0) {
            return "Ноль";
        } else {

            //convertedNumber = "";
            String thousand;

            String millions = convertToDigit(n / 1000000, addWords[0]);
            //миллионные доли в числе
            thousand = convertToDigit((n / 1000) % 1000, addWords[1]);
            if (replacementForThousands(n)){
                thousand = thousand.replaceAll("один", "одна");
                thousand = thousand.replaceAll("два", "две");
            }

            String hundredths = convertToDigit(n % 1000, "");
            convertedNumber = millions + okonchaniaMill(n / 1000000) + thousand + okonchaniaThous((n / 1000) % 1000) + hundredths;
        }

        return convertedNumber.substring(0, 1).toUpperCase() + convertedNumber.substring(1);
    }
    //добавления окончаний к доп. словам
    public static String okonchaniaThous(int number) {
        if (number % 10 == 1 && number % 100 != 11) {
            return "a ";
        } else if (number % 100 >= 12 && number % 100 <= 19) {
            return "";
        } else if (number % 10 >= 2 && number % 10 <= 4) {
            return "и ";
        } else return "";
    }

    public static String okonchaniaMill(int number) {
        if (number != 0){
            if (number % 10 == 1 && number % 100 != 11) {
                return "";
            } else if (number % 10 > 1 && number % 10 <= 4) {
                return "а ";
            } else return "ов ";}
        else return "";
    }

}
