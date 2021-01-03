import java.time.LocalDate;
//使用LocalDate类  绘制当月日历
public class main {
    public static void main(String[] args)
    {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int today = now.getDayOfMonth();
                
    }
}
