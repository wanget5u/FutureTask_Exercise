import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <h3>Obsługuje akcję pobierania stanu zadania</h3>
 *
 * Klasa `GetState` implementuje interfejs `ActionListener` i odpowiada za obsługę zdarzenia,
 * które polega na sprawdzeniu stanu wybranego zadania w systemie. Kiedy użytkownik wybierze zadanie
 * z listy i kliknie przycisk "Get State", klasa ta wypisuje informacje o stanie zadania do konsoli.
 *
 * <h3>Opis działania:</h3>
 * - Sprawdza, czy zostało wybrane jakieś zadanie.
 * - Wypisuje do konsoli stan zadania, tj. czy jest zakończone (`isDone()`).
 * - Oczywiście, po wykonaniu akcji, lista zadań jest odświeżana.
 */
public class GetState implements ActionListener
{
    private final View view;
    private final Model model;
    /**
     * <h3>Tworzy obiekt `GetState` i przypisuje obiekty `View` oraz `Model`.</h3>
     *
     * Konstruktor przyjmuje instancje klas `View` i `Model`, które są niezbędne do
     * wyświetlania stanu zadania i interakcji z systemem.
     *
     * @param view Instancja klasy `View`, służy do uzyskiwania dostępu do listy zadań.
     * @param model Instancja klasy `Model`, pozwala na dostęp do danych o zadaniach.
     */
    public GetState(View view, Model model)
    {this.view = view;this.model = model;}
    /**
     * <h3>Wykonuje akcję sprawdzania stanu wybranego zadania</h3>
     *
     * Ta metoda jest wywoływana po kliknięciu przycisku "Get State". Sprawdza, czy wybrane
     * zostało zadanie na liście. Jeśli tak, wypisuje stan zadania (czy jest zakończone) do konsoli.
     *
     * @param event Zdarzenie wywołane przez kliknięcie przycisku "Get State".
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (view.getTaskList().getSelectedValue() != null)
        {
            System.out.println(
                    "Task " + view.getTaskList().getSelectedValue().taskID +
                    " isDone: " + model.taskFutures.get(view.getTaskList().getSelectedValue().taskID - 1).isDone());
        }
        view.getTaskList().clearSelection();
        view.getTaskList().repaint();
    }
}
