package br.edu.ifma.ticketif.util.formatters;

import java.util.Date;

public abstract class Formatter {

    public abstract Date parse(String s);
    public abstract String toString(Date d);

}
