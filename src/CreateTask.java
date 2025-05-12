import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <h3>Obsługuje tworzenie nowych zadań</h3>
 *
 * Klasa {@link CreateTask} odpowiada za tworzenie nowych zadań w systemie. Po kliknięciu
 * przycisku "Create" w interfejsie użytkownika, metoda `actionPerformed` tworzy nowe zadanie,
 * dodaje je do listy zadań oraz wysyła do wykonania przy pomocy `ExecutorService`.
 * Jeśli liczba aktywnych zadań przekroczy maksymalną liczbę dostępnych wątków, zadanie
 * nie zostanie dodane do kolejki.
 *
 * <h3>Przykład użycia:</h3>
 * - Kliknięcie przycisku "Create" powoduje utworzenie nowego zadania.
 * - Nowo utworzone zadanie jest dodawane do listy zadań, a także wysyłane do wykonania.
 *
 */
public class CreateTask implements ActionListener
{
    private final View view;
    private final Model model;
    /**
     * <h3>Konstruktor klasy {@link CreateTask}</h3>
     *
     * Konstruktor ustawia referencje do widoku oraz modelu, co umożliwia dostęp
     * do interfejsu użytkownika oraz obiektów, które przechowują listę zadań oraz obsługują ich wykonanie.
     *
     * @param view Obiekt widoku aplikacji.
     * @param model Obiekt modelu aplikacji, który zarządza zadaniami.
     */
    public CreateTask(View view, Model model)
    {this.view = view; this.model = model;}
    /**
     * <h3>Akcja tworzenia nowego zadania</h3>
     *
     * Metoda ta jest wywoływana po kliknięciu przycisku "Create". Tworzy ona nowe zadanie,
     * dodaje je do listy zadań oraz wysyła do wykonania za pomocą `ExecutorService`.
     * Jeśli liczba aktywnych zadań przekracza liczbę dostępnych wątków (zdefiniowaną przez `nThreads`),
     * zadanie nie zostanie dodane, a w konsoli zostanie wyświetlony komunikat o błędzie.
     *
     * <p>Po dodaniu zadania do listy, lista zadań jest odświeżana.</p>
     *
     * @param event Zdarzenie, które wywołuje akcję (np. kliknięcie przycisku "Create").
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (model.taskFutures.size() <= model.nThreads)
        {
            Task task = new Task(view);

            model.taskFutures.add(model.executorService.submit(task));
            view.getListModel().insertElementAt(task, 0);
        }
        else
        {System.err.println("ExecutorService is full ");}

        view.getTaskList().clearSelection();
        view.getTaskList().repaint();
    }
}
