package org.example;

import java.time.*;
import java.util.IntSummaryStatistics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static void main() {

        //DATY I CZAS

        //LocalDate - tylko DATE (rok msc dzien) bez godziny bez strefy czasowej

        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate birthday = LocalDate.of(1998, 9, 16);
        System.out.println("Urodziny adixa: " + birthday);

        System.out.println(today.getMonth()); //zwraca nam msc slownie

        int dayOfMonth = today.getDayOfMonth(); //dzien msc
        System.out.println(dayOfMonth);

        DayOfWeek dayOfWeek = today.getDayOfWeek(); //wyciaga dzien tygodnia tez slownie bo z enuma
        System.out.println(dayOfWeek);

        LocalDate plus5Days = today.plusDays(5);
        System.out.println("bylo: " + today + "jest: " + plus5Days); //dodaje dni, analogicznie dla msc i roku

        // to samo z minusDays months weeks itd

        boolean isBefore = birthday.isBefore(today);
        System.out.println(isBefore);

        System.out.println(today.getMonthValue()); //to zwraca month ale jako int

        //analogicznie isAfter

        //LocalTime - tylko godzina (HH:mm:ss) bez daty i bez strefy

        LocalTime todayTime = LocalTime.now(); // aktulana godzina
        System.out.println(todayTime);

        LocalTime workStart = LocalTime.of(15, 20); //tworzy godzine tak jak podamy
        System.out.println(workStart);

        int hour = todayTime.getHour(); // wyciaga godzine
        //analogicznie getMinute
        System.out.println(hour);

        LocalTime plus2Hours = todayTime.plusHours(2);
        System.out.println("przed: " + todayTime + " po: " + plus2Hours);

        //analogicznie dla minut

        boolean isBeforeTime = workStart.isBefore(todayTime);
        System.out.println(isBeforeTime);
        //to samo dla isAfter


        //LocalDateTime Data godzina ale bez streft nadal

        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println(nowDateTime);

        LocalDateTime meeting = LocalDateTime.of(2026, 1, 15, 15, 14, 30);
        System.out.println("Meeting: " + meeting);

        LocalDate onlyDate = nowDateTime.toLocalDate(); //tylko data
        LocalTime onlyTime = nowDateTime.toLocalTime(); //tylko godzina

        System.out.println(onlyDate);
        System.out.println(onlyTime);
        //plus i minus oraz before i after analogicznie jak wyzej

        //EPOCH - umowny punkt startowy czasu komputerowego 1970-01-01T0
        Instant epochStart = Instant.EPOCH;
        System.out.println("EPOCH");
        System.out.println(epochStart);

        //Instant - punkt w czasie (UTC), najlepszu do synchronizacji systemow

        Instant nowInstant = Instant.now(); // i w pl i usa da nam ta sama wartosc bo liczy od tego 1970, to jest ta akutalna chwila globalnie
        //dlatego Z na koncu bo Zulu time czyli czas ogolny
        System.out.println("instant: " + nowInstant); // aktualny moment UTC

        long epochMillis = nowInstant.toEpochMilli();
        System.out.println(epochMillis);

        //analogicznie wszystkie plus, minus, before i after

        //roznica localDateTime vs Instant

        System.out.println("Local: " + nowDateTime); // to jest nasz UTC +1
        System.out.println("Instant: " + nowInstant);// to jest UTC wiec godzina wstecz

        //Duration - czas trwania (sec/min/h)

        Duration duraton = Duration.ofMinutes(90);
        System.out.println(duraton); //PT Period Time

        long millis = duraton.toMillis();
        System.out.println(millis);

        long hours = duraton.toHours();
        System.out.println(hours);

        long minutes = duraton.toMinutes();
        System.out.println(minutes);

        long sec = duraton.toSeconds();
        System.out.println(sec);

        //isZero sprawdza czy Duration wynosi dokaldnie 0
        //isNegative sprawdza czy duration jest ujemny

        Duration betweenInstants = Duration.between(Instant.now(), Instant.EPOCH);
        System.out.println(betweenInstants); //to nam daje nam duration miedzy konkretnymi datami

        //Period - roznica kalndarzowa (lata/msc/dni)

        Period p = Period.of(2, 5, 10);
        System.out.println(p);
        //P2Y5M10D = P period  apozniej Y years M months D days

        //getMonths, Years, Days
        //Plus days months years
        //minus to samo
        //isZero i isNegative jw.


        Period betweenDates =
                Period.between(LocalDate.of(2020, 1, 15), LocalDate.of(2020, 2, 15));
        System.out.println(betweenDates);

        //Duration liczy instanty a period localDate czyli kalendarzowo

        //do pocztania:
        //przeklikaj sobie to jescze raz wszytk
        //DateTimeForamtter

        System.out.println("\n------zadania-----\n");
        System.out.println("\nzadanie1\n");
//        ZADANIE 1 – DATA UTWORZENIA I WIEK REKORDU
//                -----------------------------------------------------------------------------
//                Masz informację, kiedy coś zostało utworzone.
//
//                Dane wejściowe:
//        data i godzina utworzenia jako tekst, np."15.01.2026 14:30"
//        format tekstu: "dd.MM.yyyy HH:mm"
//
//        Twoje zadanie:
//        1) Zamień tekst na obiekt daty i czasu.
//        2) Sprawdź, ile czasu minęło od utworzenia do teraz:
//
//                w minutach
//        w godzinach
//        3) Sprawdź, czy obiekt jest starszy niż 24 godziny.
//
//                Podpowiedzi:
//        interesuje Cię „ile czasu minęło”, a nie daty kalendarzowe
//        obliczenia wykonuj na obiektach czasu, nie na tekstach

        String dataUtworzeniaString = "15.01.2026 14:30";
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\s(\\d+):(\\d+)");
        Matcher matcher = pattern.matcher(dataUtworzeniaString);
        LocalDateTime dataUtworzenia = null;

        while (matcher.find()) {
            dataUtworzenia = LocalDateTime.of(Integer.parseInt(matcher.group(3).trim()), Integer.parseInt(matcher.group(2).trim()), Integer.parseInt(matcher.group(1).trim()), Integer.parseInt(matcher.group(4).trim()), Integer.parseInt(matcher.group(5).trim()));
        }

        System.out.println("Obiekt LocalDateTime: " + dataUtworzenia);

        Duration ileMinelo = Duration.between(dataUtworzenia, LocalDateTime.now());
        Long ileMineloMinut = ileMinelo.toMinutes();
        Long ileMineloGodzin = ileMinelo.toHours();
        System.out.println("Do teraz minelo " + ileMineloGodzin + " godzin, czyli " + ileMineloMinut + " minut.");

        if (ileMineloGodzin > 24) {
            System.out.println("Obiekt jest starszy niz 24h");
        } else {
            System.out.println("Obiekt jest mlodszy lub rowny 24h");
        }

        System.out.println("\nzadanie2\n");
//        ZADANIE 2 – WIEK CZŁOWIEKA
//        -----------------------------------------------------------------------------
//                Masz datę urodzenia zapisaną jako tekst, np. "1995-05-20".
//
//                Twoje zadanie:
//        1) Zamień tekst na obiekt daty.
//        2) Oblicz, ile lat ma dana osoba.
//        3) Sprawdź, czy w tym roku miała już urodziny.
//        4) Oblicz, ile dni zostało do jej najbliższych urodzin.
//
//                Podpowiedzi:
//        nie licz wieku na sekundach
//        rok i miesiąc nie mają stałej długości
//                /
        String dataUrodzeniaString = "1995-05-20";
        Pattern pattern1 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)");
        Matcher matcher1 = pattern1.matcher(dataUrodzeniaString);
        LocalDate dataUrodzenia = null;

        while (matcher1.find()) {
            dataUrodzenia = LocalDate.of(Integer.parseInt(matcher1.group(1).trim()), Integer.parseInt(matcher1.group(2).trim()), Integer.parseInt(matcher1.group(3).trim()));
        }

        System.out.println("Obiekt LocalDate: " + dataUrodzenia);
        Period wiek = Period.between(dataUrodzenia, LocalDate.now());
        System.out.println("Dana osoba ma: " + wiek.getYears() + " lat");
        LocalDate urodzinyWTymRoku = dataUrodzenia.withYear(LocalDate.now().getYear());
        Boolean czyMial = urodzinyWTymRoku.isBefore(LocalDate.now());

        if (czyMial) {
            System.out.println("Dana osoba miala juz urodziny");
        } else {
            System.out.println("Dana osoba nie miala jeszcze urodzin");
        }
        LocalDateTime urodzinyWTymRokuDateTime = urodzinyWTymRoku.atStartOfDay();                        // zmiana na localdatetime zeby duration zadzialal bo period nie policzy dokladnie dni
        Duration ileDoUrodzin = Duration.between(LocalDate.now().atStartOfDay(), urodzinyWTymRokuDateTime);
        Long ileDniDoUrodzin = ileDoUrodzin.toDays();
        System.out.println("Do urodzin zostalo : " + ileDniDoUrodzin + " dni");


        System.out.println("\nZadanie3\n");
//                ZADANIE 3 – ZMIANA FORMATU DATY (BARDZO CZĘSTE)
//                -----------------------------------------------------------------------------
//                Masz datę i godzinę zapisaną jako tekst, np.:
//        "15.01.2026 14:30"
//
//        Twoje zadanie:
//        1) Zamień tekst na obiekt daty i czasu.
//        2) Dodaj do tej daty 7 dni kalendarzowych.
//        3) Zamień wynik z powrotem na tekst, ale w innym formacie:
//        "yyyy-MM-dd HH:mm"

//        Ważne:
//        cała logika ma być wykonana na obiektach daty i czasu
//        zamiana na tekst ma być dopiero na końcu
//
//        Podpowiedzi:
//        format służy tylko do wyświetlania
//        tekst to nie data
//*/
        String jakasDataString = "15.01.2026 14:30";
        LocalDateTime jakasData = null;
        Pattern pattern2 = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\s(\\d+):(\\d+)");
        Matcher matcher2 = pattern2.matcher(jakasDataString);

        while (matcher2.find()) {
            jakasData = LocalDateTime.of(Integer.parseInt(matcher2.group(3).trim()), Integer.parseInt(matcher2.group(2).trim()), Integer.parseInt(matcher2.group(1).trim()), Integer.parseInt(matcher2.group(4).trim()), Integer.parseInt(matcher2.group(5).trim()));
        }

        System.out.println("Obiekt LocalDateTime: " + jakasData);
        System.out.println("7 dni pozniej:  " + jakasData.plusDays(7));
        System.out.println("tekst w formacie yyyy-MM-dd HH:mm - " + jakasData.getYear() + "-" + jakasData.getMonthValue() + "-" + jakasData.getDayOfMonth() + " " + jakasData.getHour() + ":" + jakasData.getMinute());
    }
}
