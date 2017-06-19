/**
 * Created by Kamil on 11.06.2017.
 */
public class Wiadomosc {
    String msg;
    String typ;

    public Wiadomosc() {
    }

    public Wiadomosc(String msg, String typ) {
        this.msg = msg;
        this.typ = typ;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
