/** <h3>
 * Klasa kontrolera w architekturze MVC (Model-View-Controller).
 * Klasa ta obsługuje zdarzenia generowane przez interfejs użytkownika (widok) i komunikuje się z modelem.
 * </h3>
 */
public class Controller
{
    /** <h3>
     * Konstruktor klasy {@link Controller}.
     * Rejestruje nasłuchiwacze zdarzeń dla przycisków w widoku.
     * Każdy przycisk jest powiązany z funkcjonalnością dostarczoną przez klasy z pakietu Functionality.
     * </h3>
     *
     * @param view Widok, który zawiera przyciski i interfejs użytkownika.
     * @param model Model, który przechowuje dane aplikacji i logikę biznesową.
     */
    public Controller(View view, Model model)
    {
        // Przycisk "Create Task" wywołuje funkcję tworzenia nowego zadania.
        view.getCreateTaskButton().addActionListener(new CreateTask(view, model));

        // Przycisk "Cancel Task" wywołuje funkcję anulowania zadania.
        view.getCancelTaskButton().addActionListener(new CancelTask(view, model));

        // Przycisk "Get State" wywołuje funkcję pobierania stanu zadania.
        view.getGetStateButton().addActionListener(new GetState(view, model));

        // Przycisk "Get Result" wywołuje funkcję pobierania wyniku zadania.
        view.getGetResultButton().addActionListener(new GetResult(view, model));

        // Przycisk "Lower Speed" zmienia szybkość ruchu kwadratów w zadaniach na niższą.
        view.getLowerSpeedButton().addActionListener(new LowerSpeed());

        // Przycisk "Raise Speed" zmienia szybkość ruchu kwadratów w zadaniach na wyższą.
        view.getRaiseSpeedButton().addActionListener(new RaiseSpeed());
    }
}
