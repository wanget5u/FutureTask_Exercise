import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <h3>Obsługuje zwiększenie prędkości zadań</h3>
 *
 * Klasa `RaiseSpeed` implementuje interfejs `ActionListener` i jest odpowiedzialna za zwiększenie prędkości
 * wykonania zadań. Jest wywoływana po kliknięciu przycisku "Raise Speed" w interfejsie użytkownika.
 * Zmienia wartość zmiennej statycznej `Task.speed`, co wpływa na tempo poruszania się zadań.
 */
public class RaiseSpeed implements ActionListener
{
    /**
     * <h3>Zwiększa prędkość zadań o 1</h3>
     *
     * Metoda ta jest wywoływana po kliknięciu przycisku związanym z akcją zwiększenia prędkości.
     * Zwiększa wartość zmiennej statycznej `Task.speed` o 1, co powoduje wzrost prędkości poruszania się zadań.
     *
     * @param event Zdarzenie wywołane przez kliknięcie przycisku "Raise Speed".
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {Task.speed += 1;}
}
