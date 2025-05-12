import java.util.LinkedList;
import java.util.concurrent.*;
/** <h3>
 * Klasa {@link Model} reprezentuje warstwę logiki aplikacji, odpowiedzialną za zarządzanie zadaniami
 * oraz ich wykonywanie w sposób asynchroniczny przy użyciu wątków.
 * Używa ona klasy ExecutorService do uruchamiania zadań w tle.
 * </h3>
 */
public class Model
{
    /**
     * ExecutorService do zarządzania wątkami w aplikacji.
     * Wykorzystuje pulę wątków do wykonywania zadań w tle.
     */
    public ExecutorService executorService;
    /**
     * Lista przechowująca przyszłe wyniki zadań (obiekty Future).
     * Każdy element w tej liście odpowiada przyszłemu wynikowi jednego zadania.
     */
    public LinkedList<Future<Integer>> taskFutures;
    /**
     * Liczba wątków w puli wątków.
     * Określa, ile wątków będzie jednocześnie dostępnych do przetwarzania zadań.
     */
    public int nThreads;
    /** <h3>
     * Konstruktor klasy {@link Model}.
     * Inicjalizuje ExecutorService z określoną liczbą wątków oraz tworzy pustą listę na przyszłe wyniki zadań.
     * </h3>
     *
     * @param nThreads Liczba wątków w puli, które będą obsługiwać zadania.
     */
    public Model(int nThreads)
    {
        this.nThreads = nThreads;

        // Tworzenie instancji ThreadPoolExecutor z podaną liczbą wątków w puli.
        this.executorService =
                new ThreadPoolExecutor(
                        nThreads, nThreads, 0L,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        taskFutures = new LinkedList<>();
    }
}
