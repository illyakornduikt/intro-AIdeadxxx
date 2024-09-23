import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Swing App"); // название окна 
        frame.setSize(600, 600); // размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закривает окно 

        JButton button = new JButton("Click Me!"); // создает текст для кнопки
        frame.add(button); // создает саму кнопку

        // Добавляем обработчик событий для кнопки
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button was clicked!");
            }
        });
        frame.setLocationRelativeTo(null); // при запуске виравниевает окно по цетру

        frame.setVisible(true); // делает окно видним
    }
}
