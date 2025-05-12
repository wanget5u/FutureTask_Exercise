import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Future;
/**
 * <h3>Obsługuje akcję pobierania wyniku zadania</h3>
 *
 * Klasa {@link GetResult} jest odpowiedzialna za obsługę akcji związanej z pobieraniem wyniku
 * dla wybranego zadania. Zostaje wywołana po kliknięciu przycisku "Get result". Po
 * wybraniu zadania z listy, metoda ta uruchamia osobny wątek, który próbuje uzyskać wynik
 * zadania. Jeśli zadanie zakończyło się pomyślnie, wynik zostaje wyświetlony w oknie dialogowym.
 * W przypadku błędu lub anulowania zadania, użytkownik otrzymuje komunikat o błędzie.
 */
public class GetResult implements ActionListener
{
    private final View view;
    private final Model model;
    /**
     * <h3>Konstruktor klasy {@link GetResult}</h3>
     *
     * Konstruktor, który ustawia widok i model, dzięki którym możliwe będzie
     * pobranie zadania oraz jego wyniku.
     *
     * @param view Obiekt widoku aplikacji.
     * @param model Obiekt modelu aplikacji.
     */
    public GetResult(View view, Model model)
    {this.view = view;this.model = model;}
    /**
     * <h3>Akcja pobierania wyniku zadania</h3>
     *
     * Metoda wywoływana po kliknięciu przycisku "Get result". Jeśli wybrane zostało jakieś
     * zadanie na liście, metoda ta pobiera jego wynik z obiektu `Future`, a następnie
     * wyświetla go w oknie dialogowym. Jeśli zadanie zostało anulowane lub wystąpił błąd,
     * użytkownik otrzymuje stosowny komunikat.
     *
     * <p>Metoda uruchamia osobny wątek, aby pobrać wynik zadania, a w przypadku zakończenia,
     * informuje użytkownika o rezultatach.</p>
     *
     * @param event Zdarzenie, które wywołuje akcję (kliknięcie przycisku).
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (view.getTaskList().getSelectedValue() != null)
        {
            Task selectedTask = view.getTaskList().getSelectedValue();
            Future<Integer> futureTask = model.taskFutures.get(selectedTask.taskID - 1);

            // Uruchomienie wątku, który pobierze wynik zadania
            new Thread(() ->
            {
                try
                {
                    // Próba pobrania wyniku zadania
                    Integer result = futureTask.get();

                    // Utworzenie etykiety z wynikiem
                    JLabel label = new JLabel("Task " + selectedTask.taskID + " result: " + result);
                    label.setFont(new Font("SansSerif", Font.BOLD, 16));
                    label.setForeground(new Color(230, 230, 230));

                    // Wyświetlenie wyniku w oknie dialogowym
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null, label, "Task Result", JOptionPane.INFORMATION_MESSAGE));

                }
                catch (Exception e)
                {
                    // W przypadku błędu lub anulowania zadania, wyświetlenie komunikatu o błędzie
                    JLabel label = new JLabel("Task was cancelled or an error occurred");
                    label.setFont(new Font("SansSerif", Font.BOLD, 16));
                    label.setForeground(new Color(230, 230, 230));

                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null, label, "Task Result", JOptionPane.INFORMATION_MESSAGE));
                }
            }).start();

            view.getTaskList().clearSelection();
            view.getTaskList().repaint();
        }
    }
}
