import javax.swing.*;
import java.awt.*;
/**
 * <h3>Renderowanie komórek listy zadań</h3>
 *
 * Klasa `TaskListCellRenderer` rozszerza klasę `DefaultListCellRenderer` i jest odpowiedzialna
 * za personalizację wyglądu komórek w liście zadań. Dzięki tej klasie każda komórka listy może
 * mieć różne kolory w zależności od stanu zadania (np. zakończone, anulowane).
 *
 * <h3>Opis działania:</h3>
 * - Jeśli zadanie jest zakończone (`DONE`), komórka jest pomalowana na zielono.
 * - Jeśli zadanie zostało anulowane (`CANCELLED`), komórka jest pomalowana na czerwono.
 * - Komórka zadania ma standardowy szary kolor tła, jeśli nie jest zadaniem.
 */
public class TaskListCellRenderer extends DefaultListCellRenderer
{
    /**
     * <h3>Personalizuje wygląd komórek listy zadań</h3>
     *
     * Ta metoda odpowiada za wyświetlanie niestandardowego wyglądu komórek w liście zadań.
     * Kolor tła i tekstu komórki zmienia się w zależności od stanu zadania.
     *
     * @param list Lista, w której wyświetlane są zadania.
     * @param value Obiekt reprezentujący dane w danej komórce (w tym przypadku zadanie).
     * @param index Indeks aktualnej komórki.
     * @param isSelected Flaga informująca, czy komórka jest aktualnie zaznaczona.
     * @param cellHasFocus Flaga informująca, czy komórka ma fokus.
     *
     * @return Komponent (komórka) z odpowiednio ustawionym wyglądem.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (cellHasFocus)
        {component.setFocusTraversalKeysEnabled(false);}

        // Jeżeli wartość komórki jest instancją klasy Task
        if (value instanceof Task)
        {
            Task task = (Task) value;

            // Jeśli zadanie zostało anulowane
            if (task.getState() == Task.TaskState.CANCELLED)
            {
                component.setBackground(new Color(255, 69, 75));  // Czerwony kolor
                component.setForeground(Color.BLACK);
            }
            // Jeśli zadanie zostało zakończone
            else if (task.getState() == Task.TaskState.DONE)
            {
                component.setBackground(new Color(79, 255, 79));  // Zielony kolor
                component.setForeground(Color.BLACK);
            }
        }
        else
        {component.setBackground(new Color(66, 66, 66));}

        return component;
    }
}
