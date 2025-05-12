import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <h3>Obsługuje zmniejszenie prędkości zadań</h3>
 *
 * Klasa `LowerSpeed` implementuje interfejs `ActionListener` i jest odpowiedzialna za zmniejszanie prędkości
 * wykonania zadań. Jest wywoływana po kliknięciu przycisku "Lower Speed" w interfejsie użytkownika.
 * Zmienia wartość zmiennej statycznej `Task.speed`, co wpływa na tempo poruszania się zadań.
 */
public class LowerSpeed implements ActionListener
{
    /**
     * <h3>Zmniejsza prędkość zadań o 1</h3>
     *
     * Metoda ta jest wywoływana po kliknięciu przycisku związanym z akcją zmniejszenia prędkości.
     * Jeśli prędkość jest większa lub równa 2, zostaje zmniejszona o 1. W przeciwnym przypadku,
     * gdy prędkość wynosi 1, wyświetlany jest komunikat o błędzie w konsoli, ponieważ prędkość nie
     * może zostać ustawiona na wartość mniejszą niż 1.
     *
     * @param event Zdarzenie wywołane przez kliknięcie przycisku "Lower Speed".
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (Task.speed >= 2)
        {Task.speed -= 1;}
        else
        {System.err.println("Cannot set speed lower than 1. ");}
    }
}
