import javax.swing.*;
/**
 * <h3>Metoda main</h3>
 *
 * Metoda `main` jest punktem wejścia do aplikacji. Uruchamia aplikację poprzez:
 * <ul>
 *   <li>Tworzenie obiektu widoku {@link View}</li>
 *   <li>Tworzenie obiektu modelu {@link Model} z określoną liczbą wątków (16)</li>
 *   <li>Inicjalizowanie kontrolera {@link Controller} i powiązanie go z widokiem oraz modelem</li>
 *   <li>Ustawienie widoczności okna aplikacji na true, aby użytkownik mógł interagować z aplikacją</li>
 * </ul>
 *
 */
public class Main
{
    /**
     * <h3>Inicjalizacja aplikacji</h3>
     *
     * Główna metoda, która:
     * <ul>
     *   <li>Tworzy instancję widoku {@link View}, modelu {@link Model}, oraz kontrolera {@link Controller}</li>
     *   <li>Ustawia widoczność głównego okna aplikacji na true, aby użytkownik mógł z niego korzystać.</li>
     * </ul>
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            View view = new View();
            Model model = new Model(32);
            Controller controller = new Controller(view, model);
            view.setVisible(true);
        });
    }
}