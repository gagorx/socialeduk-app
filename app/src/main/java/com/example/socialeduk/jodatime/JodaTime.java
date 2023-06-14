package com.example.socialeduk.jodatime;

import java.sql.Timestamp;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class JodaTime {

    public String difference(Long time){
        DateTime dateTime = new DateTime(time);
        DateTime now = DateTime.now();
        Duration duration = new Duration(dateTime, now);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendMinutes().appendSuffix(" minuto atrás", " minutos atrás")
                .appendHours().appendSuffix(" horas atrás", " horas atrás")
                .appendDays().appendSuffix(" dias atrás", " dias atrás")
                .appendWeeks().appendSuffix(" semanas atrás", " semanas atrás")
                .appendMonths().appendSuffix(" meses atrás", " meses atrás")
                .appendYears().appendSuffix(" anos atrás", " anos atrás")
                .printZeroNever()
                .toFormatter();

        return formatter.print(duration.toPeriod());
    }

}
