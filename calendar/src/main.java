import java.time.DayOfWeek;
import java.time.LocalDate;
//使用LocalDate类  绘制当月日历
public class main {
    public static void main(String[] args)
    {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int today = now.getDayOfMonth();
        LocalDate start = now.minusDays(today - 1);//获取本月一号对象

        DayOfWeek weekday = start.getDayOfWeek();//查看本月一号是周几
        int value = weekday.getValue();// 1 = Mon ; 7 = Sun

        System.out.println("Mon  Tue  Wed  Thu  Fri  Sat  Sun");
        for(int i =1; i < value ;i++)
        {
            System.out.print("     ");//一号缩进
        }
        while(start.getMonthValue() == month)
        {
            System.out.printf("%-3d",start.getDayOfMonth());
            if (start.getDayOfMonth()==today)
                System.out.print("* ");   //当日标记
            else System.out.print("  ");

            if(start.getDayOfWeek().getValue()==7) System.out.println();//周末回车
            start = start.plusDays(1);
        }
    }
}
