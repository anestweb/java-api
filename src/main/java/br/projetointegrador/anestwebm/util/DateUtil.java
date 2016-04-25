package br.projetointegrador.anestwebm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class DateUtil {

    public static int ageFromDate(final Date date) {
        final Calendar now = GregorianCalendar.getInstance();
        now.setTime(new Date());

        final Calendar born = GregorianCalendar.getInstance();
        born.setTime(date);

        // Se d1 >= d0, então já fez anivesário esse ano.
        int d0 = born.get(Calendar.MONTH) * 100 + born.get(Calendar.DAY_OF_MONTH);
        int d1 = now.get(Calendar.MONTH) * 100 + now.get(Calendar.DAY_OF_MONTH);

        int years = Math.abs(now.get(Calendar.YEAR) - born.get(Calendar.YEAR));

        return (d1 >= d0) ? years : (years - 1);
    }

}
