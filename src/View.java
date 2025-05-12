import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
/** <h3>
 * Klasa {@link View} odpowiada za interfejs użytkownika aplikacji.
 * Tworzy i zarządza wszystkimi komponentami graficznymi, w tym oknem głównym, panelami, przyciskami oraz listą zadań.
 * Umożliwia wyświetlanie informacji o zadaniach oraz interakcję z użytkownikiem.
 * </h3>
 */
public class View
{
    // Komponenty okna
    private final JFrame frame;

    // Panele zawierające różne sekcje interfejsu
    private JPanel framePanel;
        private JPanel taskPanel;
            private JTextPane taskNameTitle;

            // Lista zadań i model do jej wyświetlania
            private DefaultListModel<Task> listModel;
            private JList<Task> taskList;

        // Panel, na którym rysowane są zadania
        private JPanel screenPanel;

        // Panel przycisków
        private JPanel buttonPanel;
            private JButton createTaskButton;
            private JButton getStateButton;
            private JButton cancelTaskButton;
            private JButton getResultButton;

            private JButton lowerSpeedButton;
            private JButton raiseSpeedButton;

    /** <h3>
     * Inicjalizuje i dodaje wszystkie komponenty do okna aplikacji.
     * Ustawia układ, style oraz właściwości poszczególnych elementów.
     * </h3>
     */
    public void addComponents()
    {
        // Główny panel, do którego należą wszystkie komponenty
        framePanel = new JPanel();
        {
            framePanel.setLayout(new BorderLayout(0, 0));
            framePanel.setBackground(new Color(69, 69, 69));
        }

        // Panel z listą zadań
        taskPanel = new JPanel();
        {
            taskPanel.setLayout(new BorderLayout());
            taskPanel.setBorder(BorderFactory.createLineBorder(new Color(39, 37, 37), 2));
            taskPanel.setPreferredSize(new Dimension(200, 90));

            taskNameTitle = new JTextPane();
            {
                taskNameTitle.setText("Running tasks: ");
                taskNameTitle.setSize(new Dimension(200, 40));
                taskNameTitle.setPreferredSize(new Dimension(200, 40));

                taskNameTitle.setEditable(false);
                taskNameTitle.setCaretColor(null);
                taskNameTitle.setSelectionColor(null);

                taskNameTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
                taskNameTitle.setBackground(new Color(100, 100, 100));
                taskNameTitle.setForeground(new Color(230, 230, 230));
            }
            taskPanel.add(taskNameTitle, BorderLayout.NORTH);

            // Lista zadań
            listModel = new DefaultListModel<>();
            taskList = new JList<>(listModel);
            {
                taskList.setBorder(new MatteBorder(2, 0, 0, 0, new Color(39, 37, 37)));
                taskList.setBackground(new Color(66, 66, 66));
                taskList.setForeground(new Color(230, 230, 230));
                taskList.setFont(new Font("SansSerif", Font.BOLD, 18));
                taskList.setCellRenderer(new TaskListCellRenderer());
            }

            JScrollPane scrollPane = new JScrollPane(taskList);
            scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
            taskPanel.add(scrollPane, BorderLayout.CENTER);
        }

        // Panel wyświetlania zadań na ekranie
        screenPanel = new JPanel();
        {
            screenPanel.setLayout(null);
            screenPanel.setBorder(new MatteBorder(2, 0, 2, 2, new Color(39, 37, 37)));
            screenPanel.setBackground(new Color(53, 53, 53));
        }

        // Panel przycisków
        buttonPanel = new JPanel();
        {
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            buttonPanel.setBackground(new Color(129, 129, 129));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            {
                // Przyciski
                createTaskButton = new JButton("Create");
                createTaskButton.setBackground(new Color(213, 213, 213));

                cancelTaskButton = new JButton("Cancel");
                cancelTaskButton.setBackground(new Color(213, 213, 213));

                getStateButton = new JButton("Get state");
                getStateButton.setBackground(new Color(213, 213, 213));

                getResultButton = new JButton("Get result");
                getResultButton.setBackground(new Color(213, 213, 213));

                lowerSpeedButton = new JButton("-");
                lowerSpeedButton.setBackground(new Color(213, 213, 213));

                raiseSpeedButton = new JButton("+");
                raiseSpeedButton.setBackground(new Color(213, 213, 213));
            }
            buttonPanel.add(createTaskButton);
            buttonPanel.add(cancelTaskButton);
            buttonPanel.add(getStateButton);
            buttonPanel.add(getResultButton);

            buttonPanel.add(Box.createGlue());

            buttonPanel.add(lowerSpeedButton);
            buttonPanel.add(raiseSpeedButton);
        }

        framePanel.add(taskPanel, BorderLayout.WEST);
        framePanel.add(screenPanel, BorderLayout.CENTER);
        framePanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(framePanel);
    }
    /** <h3>
     * Konstruktor klasy {@link View}.
     * Inicjalizuje okno aplikacji i ustawia odpowiednie parametry dla komponentów.
     * Ustawia także wygląd okien dialogowych i uruchamia ciągłą aktualizację ekranu.
     * </h3>
     */
    public View()
    {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("UTP10_GM_S31230");
        frame.setLocationRelativeTo(null);

        addComponents();

        // Ustawienia wyglądu okien dialogowych
        UIManager.put("OptionPane.background", new Color(69, 69, 69));
        UIManager.put("Panel.background", new Color(69, 69, 69));
        UIManager.put("OptionPane.messageForeground", new Color(202, 202, 202));

        // Ciągła aktualizacja panelu ekranu
        new Thread(() ->
        {
            while (!Thread.currentThread().isInterrupted())
            {screenPanel.repaint();}
        }).start();
    }
    /**<h3>
     * Ustawia widoczność okna aplikacji.
     * </h3>
     *
     * @param visible Określa, czy okno powinno być widoczne.
     */
    public void setVisible(boolean visible)
    {frame.setVisible(visible);}
    /** <h3>
     * Zwraca listę zadań wyświetlaną w panelu.
     * </h3>
     *
     * @return JList z zadaniami.
     */
    public JList<Task> getTaskList()
    {return taskList;}
    /** <h3>
     * Zwraca model listy zadań, który przechowuje dane o zadaniach.
     * </h3>
     *
     * @return DefaultListModel zawierający zadania.
     */
    public DefaultListModel<Task> getListModel()
    {return listModel;}
    /** <h3>
     * Zwraca przycisk do zmniejszenia prędkości.
     * </h3>
     *
     * @return Przycisk zmniejszenia prędkości.
     */
    public JButton getLowerSpeedButton()
    {return lowerSpeedButton;}
    /** <h3>
     * Zwraca przycisk do zwiększenia prędkości.
     * </h3>
     *
     * @return Przycisk zwiększenia prędkości.
     */
    public JButton getRaiseSpeedButton()
    {return raiseSpeedButton;}
    /** <h3>
     * Zwraca panel wyświetlania zadań na ekranie.
     * </h3>
     *
     * @return Panel do wyświetlania zadań.
     */
    public JPanel getScreenPanel()
    {return screenPanel;}
    /** <h3>
     * Zwraca przycisk do tworzenia nowego zadania.
     * </h3>
     *
     * @return Przycisk do tworzenia zadania.
     */
    public JButton getCreateTaskButton()
    {return createTaskButton;}
    /** <h3>
     * Zwraca przycisk do anulowania zadania.
     * </h3>
     *
     * @return Przycisk do anulowania zadania.
     */
    public JButton getCancelTaskButton()
    {return cancelTaskButton;}
    /** <h3>
     * Zwraca przycisk do sprawdzenia stanu zadania.
     * </h3>
     *
     * @return Przycisk do sprawdzenia stanu zadania.
     */
    public JButton getGetStateButton()
    {return getStateButton;}
    /** <h3>
     * Zwraca przycisk do uzyskania wyniku zadania.
     * </h3>
     *
     * @return Przycisk do uzyskania wyniku.
     */
    public JButton getGetResultButton()
    {return getResultButton;}
}