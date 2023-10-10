import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

// Yo, we're making a class that extends JFrame, basically turning this class into a window.
public class FortuneTellerFrame extends JFrame {

    // Declaring a bunch of components we’ll throw onto our frame.
    private JPanel top, middle, bottom, main;
    private JLabel topLbl;
    private JButton actionBtn, quitBtn;
    private JTextArea textArea;
    private JScrollPane scroller;
    // An ArrayList for the fortunes, and another to make sure we don’t repeat ourselves too quickly.
    private ArrayList<String> fortunes = new ArrayList<>();
    private ArrayList<Integer> repeatChecker = new ArrayList<>();
    private int index;

    // Constructor for our frame. This stuff happens right when an object of this class gets made.
    public FortuneTellerFrame() {
        // Calling the superclass (JFrame) constructor with the title of our window.
        super("Fortune Teller");
        // Initializing our main panel. This will hold all our other panels.
        main = new JPanel();
        // Creating the three parts of our UI and loading up the fortunes.
        createTopPanel();
        createMiddlePanel();
        createBottomPanel();
        loadFortunes();

        // Setting up the layout manager for our main panel and adding the three other panels to it.
        main.setLayout(new BorderLayout());
        main.add(top, BorderLayout.NORTH);
        main.add(middle, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);

        // Toolkit lets us ask some questions about the environment we're running in.
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Getting the screen size so we can size our window nicely.
        Dimension screenSize = kit.getScreenSize();

        // Setting our window size and location.
        setSize(3 * (screenSize.width / 4), 3 * (screenSize.height / 4));
        setLocationRelativeTo(null);  // Centers the window on the screen.
        // Adding the main panel to the frame and making sure the application exits when the window is closed.
        add(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // This method just loads all our fortunes into the ArrayList.
    private void loadFortunes() {
        fortunes.add("The cookie jar of life never depletes.");
        fortunes.add("Your joy can ignite many candles without dimming your own.");
        fortunes.add("In a world of puddles, be a serene lake.");
        fortunes.add("The cookie jar of life never depletes.");
        fortunes.add("Your joy can ignite many candles without dimming your own.");
        fortunes.add("In a world of puddles, be a serene lake.");
        fortunes.add("Laughter can bridge the greatest divides.");
        fortunes.add("A single moment can outshine years.");
        fortunes.add("In every pile of leaves, there is a chance for adventure.");
        fortunes.add("Tomorrow’s winds will find their own directions.");
        fortunes.add("Every stitch in time saves more than nine.");
        fortunes.add("In the cookie of life, fortunes are the chocolate chips.");
        fortunes.add("Your quirks are not quirks, they are your essence.");
        fortunes.add("To watch a seed, you may miss the blossom.");
        fortunes.add("Old wisdom is often the most relished.");
    }

    // Building the top panel with the title label (and possibly an icon).
    private void createTopPanel() {
        top = new JPanel();
        // An image icon is used here, but you’ll need to make sure the path is right or it won't show up!
        ImageIcon icon = new ImageIcon("fortuneTeller.png");
        topLbl = new JLabel("Fortune Teller", icon, SwingConstants.CENTER);
        topLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        top.add(topLbl);
    }

    // Constructing the middle panel which will show our fortunes.
    private void createMiddlePanel() {
        middle = new JPanel();
        textArea = new JTextArea(10, 50);
        textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // Adding a scroll pane in case our fortunes get too long.
        scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(700, 400));
        middle.add(scroller);
    }

    // Creating the bottom panel with the 'Read My Fortune!' and 'Quit' buttons.
    private void createBottomPanel() {
        bottom = new JPanel();
        actionBtn = new JButton("Read My Fortune!");
        // Attaching the mixFortunes() method to the button via an action listener.
        actionBtn.addActionListener(ae -> mixFortunes());
        quitBtn = new JButton("Quit");
        // This makes the application exit when you press the quit button.
        quitBtn.addActionListener(ae -> System.exit(0));
        bottom.add(actionBtn);
        bottom.add(quitBtn);
    }

    // This method mixes up our fortunes and presents one.
    public void mixFortunes() {
        Random random = new Random();
        // If we’ve gone through all the fortunes, we clear our repeat checker.
        if (repeatChecker.size() == fortunes.size()) {
            repeatChecker.clear();
        }
        // We want to remember the last number generated so we don’t immediately repeat fortunes.
        int previousNum = (repeatChecker.isEmpty()) ? -1 : repeatChecker.get(repeatChecker.size() - 1);

        // This loop keeps spinning until we find a fortune we haven’t shown recently.
        while (true) {
            index = random.nextInt(fortunes.size());
            if (index != previousNum && !repeatChecker.contains(index)) {
                break;
            }
        }
        // Add the new index to our repeat checker and show the fortune in the text area.
        repeatChecker.add(index);
        textArea.setText(fortunes.get(index) + "\n");
    }
}
