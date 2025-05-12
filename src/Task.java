import javax.swing.*;
import java.awt.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
/** <h3>
 * Klasa reprezentująca zadanie do wykonania w tle {@link Callable}. Zadanie to animuje kwadrat na ekranie w obrębie panelu,
 * zmieniając jego położenie oraz kolor. </h3>
 */
public class Task implements Callable<Integer>
{
    public static int globalTaskID = 1;
    public static int speed = 1;

    public final int taskID;
    /** Tablica przechowująca współrzędne (x, y) kwadratu */
    private final int[] coordinates = new int[2];
    private final Color squareColor;

    private final View view;

    public int taskResult;
    private TaskState state;
    /** <h3>
     * Enum reprezentujący stany zadania. </h3>
     */
    public enum TaskState
    {
        RUNNING(0), CANCELLED(-1), DONE(1);

        private final int value;

        TaskState(int value)
        {this.value = value;}
    }
    /** <h3>
     * Konstruktor klasy {@link Task}.
     * Generuje unikalny identyfikator zadania oraz losuje współrzędne i kolor kwadratu.
     * </h3>
     *
     * @param view Widok, w którym kwadrat ma się pojawić.
     */
    public Task(View view)
    {
        taskID = globalTaskID++;
        taskResult = 0;
        this.view = view;

        // Losowanie koloru kwadratu
        this.squareColor = new Color(
                ThreadLocalRandom.current().nextInt(128) + 128,
                ThreadLocalRandom.current().nextInt(128) + 128,
                ThreadLocalRandom.current().nextInt(128) + 128);

        // Losowanie współrzędnych w obrębie ekranu
        this.coordinates[0] = ThreadLocalRandom.current().nextInt(view.getScreenPanel().getSize().width);
        this.coordinates[1] = ThreadLocalRandom.current().nextInt(view.getScreenPanel().getSize().height);
    }

    @Override
    public String toString()
    {return "Task " + taskID;}

    /** <h3>
     * Główna metoda wykonywana w wątku, która animuje kwadrat na ekranie, zmieniając jego położenie w pętli.
     *
     * @return Wynik zadania (wynik ruchów kwadratu). </h3>
     */
    @Override
    public Integer call()
    {
        state = TaskState.RUNNING;

        // Tworzenie panelu, który będzie reprezentował kwadrat
        JPanel square = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics graphics)
            {
                super.paintComponent(graphics);
                graphics.setColor(squareColor);
                graphics.fillRect(0, 0, 50, 50);
            }
        };

        // Ustawianie lokalizacji i rozmiaru kwadratu
        square.setLocation(coordinates[0], coordinates[1]);
        square.setSize(new Dimension(50, 50));

        // Tworzenie JTextPane, który wyświetli numer zadania na kwadracie
        JTextPane taskNumber = new JTextPane();
        taskNumber.setOpaque(false);
        taskNumber.setBackground(new Color(0, 0, 0, 0));
        taskNumber.setText(String.valueOf(taskID));
        taskNumber.setFont(new Font("SansSerif", Font.BOLD, 22));

        // Dodanie numeru zadania do kwadratu
        square.add(taskNumber);

        // Dodanie kwadratu do widoku
        SwingUtilities.invokeLater(() ->
        {
            view.getScreenPanel().add(square);
            view.getScreenPanel().revalidate();
            view.getScreenPanel().repaint();
        });

        int lastMove = 0;
        int count = 0;

        while (!Thread.currentThread().isInterrupted())
        {
            // Sprawdzenie, czy kwadrat znajduje się w obrębie panelu
            if (!isPointInPanel(coordinates))
            {
                state = TaskState.DONE;
                callIsDone(square);  // Zakończenie zadania
                return taskResult;
            }

            // Ustawienie nowej pozycji kwadratu
            square.setLocation(coordinates[0], coordinates[1]);

            // Odświeżenie widoku (panelu)
            SwingUtilities.invokeLater(() -> view.getScreenPanel().repaint());

            // Co określoną liczbę kroków, losowanie nowego kierunku ruchu
            if (count == 18)
            {
                lastMove = ThreadLocalRandom.current().nextInt(4);
                count = 0;
            }
            else
            {count++;}

            // Ruch kwadratu w jednym z czterech kierunków
            if (lastMove == 0) {coordinates[0] += speed;}
            else if (lastMove == 1) {coordinates[0] -= speed;}
            else if (lastMove == 2) {coordinates[1] += speed;}
            else {coordinates[1] -= speed;}

            taskResult += lastMove;

            try
            {Thread.sleep(15);}
            catch (Exception exception)
            {
                // Jeśli wątek zostanie przerwany, zadanie zostaje anulowane
                state = TaskState.CANCELLED;
                callIsDone(square);
                return taskResult;
            }
        }

        state = TaskState.DONE;
        callIsDone(square); // Zakończenie zadania
        return taskResult;
    }

    /** <h3>
     * Pomocnicza metoda, która obsługuje zakończenie zadania.
     * Usuwa kwadrat z widoku i informuje o zakończeniu lub anulowaniu zadania.
     *
     * @param square Kwadrat do usunięcia z widoku. </h3>
     */
    private void callIsDone(JPanel square)
    {
        if (state == TaskState.CANCELLED)
        {System.out.println("Task " + taskID + " was cancelled");}
        else
        {System.out.println("Task " + taskID + " has ended.");}

        view.getScreenPanel().remove(square);
        view.getTaskList().repaint();
        Thread.currentThread().interrupt();
    }
    /** <h3>
     * Sprawdza, czy współrzędne kwadratu mieszczą się w obrębie panelu. </h3>
     *
     * @param coordinates Współrzędne kwadratu (x, y).
     * @return True, jeśli punkt mieści się w obrębie panelu, False w przeciwnym przypadku.
     */
    private boolean isPointInPanel(int[] coordinates)
    {
        return
                coordinates[0] >= 0 &&
                        coordinates[0] < view.getScreenPanel().getWidth() &&
                        coordinates[1] >= 0 &&
                        coordinates[1] < view.getScreenPanel().getHeight();
    }
    /** <h3>
     * Zwraca stan zadania. </h3>
     *
     * @return Stan zadania (RUNNING, CANCELLED, DONE).
     */
    public TaskState getState()
    {return this.state;}
}