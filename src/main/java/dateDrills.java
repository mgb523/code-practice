import org.junit.Test;

import java.time.*;
import java.util.*;

public class dateDrills {

    @Test
    public void helloWorld() {
        System.out.println("HELLO WORLD!");
    }

    // Write a function that determines the date of the halfway point between 2 dates
    @Test
    public void findHalfwayDate() {
        List<LocalDate> dateRange = new ArrayList<>();
        dateRange.add(LocalDate.of( 2022 , Month.JANUARY , 31 ));
        dateRange.add(LocalDate.of( 2022 , Month.JANUARY , 1 ));

        // Convert to Long from epoch
        Long date1 = Long.valueOf(dateRange.get(0).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        Long date2 = Long.valueOf(dateRange.get(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // Find halfway date
        Long halfwayDate = (date1 + date2) / 2;

        System.out.println("Halfway epoch: " + halfwayDate);
        System.out.println("Halfway date: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(halfwayDate), TimeZone.getDefault().toZoneId()));
    }

    @Test
    public void findInBetweenDate() {
        LocalDate date1 = LocalDate.of( 2022 , Month.JANUARY , 1 );
        LocalDate date2 = LocalDate.of( 2022 , Month.JANUARY , 31 );
        int sliderProgress = 33;

        // Convert dates to epoch values
        Long date1Epoch = Long.valueOf(date1.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        Long date2Epoch = Long.valueOf(date2.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // Calculate millisecond delta in range
        Long delta = Math.abs(date2Epoch - date1Epoch) * sliderProgress / 100;

        Long sliderDateEpoch = date1Epoch + delta;

        System.out.println("In progress epoch: " + sliderDateEpoch);
        System.out.println("In progress date: " + LocalDate.ofInstant(Instant.ofEpochMilli(sliderDateEpoch), TimeZone.getDefault().toZoneId()));
    }

    @Test
    public void sortDates() {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of( 2022 , Month.DECEMBER , 31 ));
        dates.add(LocalDate.of( 2022 , Month.JANUARY , 1 ));
        dates.add(LocalDate.of( 2021 , Month.DECEMBER , 30 ));
        dates.add(LocalDate.of( 1967 , Month.NOVEMBER , 30 ));
        dates.add(LocalDate.of( 1078 , Month.DECEMBER , 31 ));
        dates.add(LocalDate.of( 1971 , Month.MAY , 23 ));
        dates.add(LocalDate.of( 2022 , Month.DECEMBER , 30 ));
        dates.add(LocalDate.of( 2022 , Month.DECEMBER , 15 ));
        dates.add(LocalDate.of( 1969 , Month.APRIL , 18 ));
        dates.add(LocalDate.of( 1942 , Month.JULY , 26 ));
        dates.add(LocalDate.of( 1919 , Month.MARCH , 12 ));
        dates.add(LocalDate.of( 2022 , Month.DECEMBER , 17 ));
        dates.add(LocalDate.of( 2022 , Month.JANUARY , 1 ));
        dates.add(LocalDate.of( 1944 , Month.FEBRUARY , 20 ));
        dates.add(LocalDate.of( 2004 , Month.JANUARY , 29 ));

        // Sort dates in range(
        dates.sort(new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate o1, LocalDate o2) {
                if (o1.isBefore(o2)) {
                    return -1;
                }
                else if (o1.isAfter(o2)) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });

        // Print the sorted dates
        for (LocalDate sortedDate : dates) {
            System.out.println(String.valueOf(sortedDate));
        }
    }

    @Test
    public void formattedStringToDOY() {
        String unformattedDate = "2009.1236";
        //String unformattedDate = "2012.97123812";

        String[] stringArray = unformattedDate.split("\\.");
        Integer year = Integer.valueOf(stringArray[0]);

        // Convert percentage string to a percentage
            // METHOD 1:
            float percentage = Integer.valueOf(stringArray[1]);
            while (percentage > 1) {
                percentage = percentage / 10;
            }

//            // METHOD 2:
//            float percentage = Float.valueOf("." + stringArray[1]);

        System.out.println("Percentage: " + percentage);

        // Figure out if it is a leap year
        // 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, and 2048
        Integer numberOfDays = (year % 4 == 0) ? 366 : 365;
        System.out.println(year + ": number of days: " + numberOfDays);


        float day = numberOfDays * (percentage);
        String doy = String.valueOf(day);
        System.out.println("DOY: " + Integer.valueOf(doy.split("\\.")[0]));
    }

    @Test
    public void militaryTimeConversion() {
        String s = "07:05:45PM";

        // Write your code here
        s = s.toLowerCase();
        String[] sArray = s.split(":");
        Integer hour = Integer.valueOf(sArray[0]);
        if (s.contains("pm")) {
            hour = hour < 12 ? 12 + hour : hour;
        } else {
            hour = hour == 12 ? 0 : hour;
        }
        String milHourStr = hour > 10 ? String.valueOf(hour) : "0" + String.valueOf(hour);
        s = milHourStr + ":" + sArray[1] + ":" +  sArray[2];
        s = s.replaceAll("[am|pm]", "");

        System.out.println(s);
    }
}