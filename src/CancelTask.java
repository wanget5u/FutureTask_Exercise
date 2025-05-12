import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <h3>Obsługuje akcję anulowania zadania</h3>
 *
 * Klasa {@link CancelTask} odpowiada za anulowanie zadania wybranego z listy. Po kliknięciu
 * odpowiedniego przycisku w interfejsie, zadanie, które zostało wybrane przez użytkownika,
 * zostaje anulowane. Anulowanie odbywa się poprzez wywołanie metody `cancel()` na obiekcie
 * `Future`, który reprezentuje zadanie. Po anulowaniu zadania, lista jest odświeżana,
 * a wybrane zadanie zostaje odznaczone.
 */
public class CancelTask implements ActionListener
{
    private final View view;
    private final Model model;
    /**
     * <h3>Konstruktor klasy {@link CancelTask}</h3>
     *
     * Konstruktor, który ustawia referencje do widoku oraz modelu,
     * umożliwiając dostęp do listy zadań oraz obiektów przechowujących zadania w systemie.
     *
     * @param view Obiekt widoku aplikacji.
     * @param model Obiekt modelu aplikacji.
     */
    public CancelTask(View view, Model model)
    {this.view = view;this.model = model;}
    /**
     * <h3>Akcja anulowania zadania</h3>
     *
     * Metoda ta jest wywoływana po kliknięciu przycisku "Cancel". Jeśli użytkownik wybrał
     * jakieś zadanie z listy, metoda wywołuje metodę `cancel(true)` na obiekcie `Future`
     * reprezentującym to zadanie, co powoduje jego anulowanie.
     *
     * <p>Po anulowaniu zadania, lista zadań jest odświeżana, a aktualnie wybrane zadanie
     * zostaje odznaczone.</p>
     *
     * @param event Zdarzenie, które wywołuje akcję (kliknięcie przycisku).
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (view.getTaskList().getSelectedValue() != null)
        {model.taskFutures.get(view.getTaskList().getSelectedValue().taskID - 1).cancel(true);}

        view.getTaskList().clearSelection();
        view.getTaskList().repaint();
    }
}
