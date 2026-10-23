import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Quiz Master - a timed multiple-choice quiz built with Java Swing.
 * Compile: javac QuizMaster.java
 * Run:     java QuizMaster
 */
public class QuizMaster extends JFrame {
    private static final Color BG = new Color(13, 20, 38);
    private static final Color PANEL = new Color(23, 33, 57);
    private static final Color PANEL_LIGHT = new Color(31, 45, 73);
    private static final Color ACCENT = new Color(91, 141, 255);
    private static final Color TEXT = new Color(241, 245, 255);
    private static final Color MUTED = new Color(168, 182, 211);
    private static final Color GREEN = new Color(54, 190, 130);
    private static final Color RED = new Color(235, 95, 111);
    private static final int QUESTION_SECONDS = 15;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel root = new JPanel(cardLayout);
    private final JComboBox<String> categoryBox = new JComboBox<>(
            new String[]{"Mixed Quiz", "Java Fundamentals", "Computer Basics"});
    private final JLabel timerLabel = new JLabel("15s");
    private final JLabel progressLabel = new JLabel("QUESTION 1 OF 10");
    private final JLabel scoreLabel = new JLabel("SCORE 0");
    private final JLabel questionLabel = new JLabel();
    private final JLabel feedbackLabel = new JLabel(" ");
    private final JProgressBar timerBar = new JProgressBar(0, QUESTION_SECONDS);
    private final JProgressBar progressBar = new JProgressBar(0, 10);
    private final JButton[] optionButtons = new JButton[4];
    private final JButton nextButton = new JButton("NEXT QUESTION  >");
    private final JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 12, 12));

    private final List<Question> questionBank = buildQuestionBank();
    private List<Question> activeQuestions = new ArrayList<>();
    private int questionIndex = 0;
    private int score = 0;
    private int secondsLeft = QUESTION_SECONDS;
    private boolean answered = false;
    private Timer countdown;

    public QuizMaster() {
        super("Quiz Master | Full Stack Java Programming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 590));
        setSize(900, 650);
        setLocationRelativeTo(null);
        root.setBackground(BG);
        root.add(buildWelcomeScreen(), "welcome");
        root.add(buildQuizScreen(), "quiz");
        root.add(buildResultScreen(), "result");
        setContentPane(root);
        countdown = new Timer(1000, e -> tick());
        cardLayout.show(root, "welcome");
    }

    private JPanel buildWelcomeScreen() {
        JPanel page = new JPanel(new GridBagLayout());
        page.setBackground(BG);
        JPanel card = new JPanel();
        card.setBackground(PANEL);
        card.setBorder(new EmptyBorder(38, 48, 38, 48));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(560, 470));

        JLabel logo = new JLabel("Q");
        logo.setOpaque(true);
        logo.setBackground(ACCENT);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 28));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(56, 56));
        logo.setMaximumSize(new Dimension(56, 56));
        logo.setBorder(new EmptyBorder(3, 3, 3, 3));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(logo);
        card.add(Box.createVerticalStrut(22));

        JLabel eyebrow = label("INTERACTIVE KNOWLEDGE CHALLENGE", 12, ACCENT, true);
        eyebrow.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(eyebrow);
        card.add(Box.createVerticalStrut(8));

        JLabel title = label("Quiz Master", 38, TEXT, true);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(12));

        JLabel desc = label("Test your skills. Beat the clock. Learn as you go.", 15, MUTED, false);
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(desc);
        card.add(Box.createVerticalStrut(30));

        JLabel choose = label("CHOOSE YOUR QUIZ", 11, MUTED, true);
        choose.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(choose);
        card.add(Box.createVerticalStrut(8));
        categoryBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
        categoryBox.setBackground(PANEL_LIGHT);
        categoryBox.setForeground(TEXT);
        categoryBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        categoryBox.setBorder(new EmptyBorder(8, 10, 8, 10));
        card.add(categoryBox);
        card.add(Box.createVerticalStrut(16));

        JLabel detail = label("10 questions   •   15 seconds each   •   Instant feedback", 12, MUTED, false);
        detail.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(detail);
        card.add(Box.createVerticalStrut(22));

        JButton start = primaryButton("START QUIZ  →");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(e -> startQuiz());
        card.add(start);
        card.add(Box.createVerticalStrut(18));
        JLabel controls = label("Select an answer, read the explanation, and continue.", 11, MUTED, false);
        controls.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(controls);

        page.add(card);
        return page;
    }

    private JPanel buildQuizScreen() {
        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setBackground(BG);
        page.setBorder(new EmptyBorder(24, 34, 24, 34));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel brand = label("QUIZ MASTER", 14, TEXT, true);
        JLabel category = label("FULL STACK JAVA PROGRAMMING", 10, MUTED, true);
        header.add(brand, BorderLayout.WEST);
        header.add(category, BorderLayout.EAST);
        page.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel meta = new JPanel(new BorderLayout(12, 0));
        meta.setOpaque(false);
        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        progressLabel.setForeground(MUTED);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        scoreLabel.setForeground(GREEN);
        meta.add(progressLabel, BorderLayout.WEST);
        meta.add(scoreLabel, BorderLayout.EAST);
        meta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        center.add(meta);
        center.add(Box.createVerticalStrut(10));

        progressBar.setStringPainted(false);
        progressBar.setForeground(ACCENT);
        progressBar.setBackground(PANEL_LIGHT);
        progressBar.setBorderPainted(false);
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5));
        progressBar.setPreferredSize(new Dimension(600, 5));
        center.add(progressBar);
        center.add(Box.createVerticalStrut(20));

        JPanel questionCard = new JPanel(new BorderLayout(12, 12));
        questionCard.setBackground(PANEL);
        questionCard.setBorder(new EmptyBorder(24, 26, 24, 26));
        questionCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        JLabel tag = label("MULTIPLE CHOICE", 10, ACCENT, true);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        questionLabel.setForeground(TEXT);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        questionCard.add(tag, BorderLayout.NORTH);
        questionCard.add(questionLabel, BorderLayout.CENTER);

        JPanel timePanel = new JPanel(new BorderLayout(12, 0));
        timePanel.setOpaque(false);
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        timerLabel.setForeground(TEXT);
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerBar.setStringPainted(false);
        timerBar.setForeground(GREEN);
        timerBar.setBackground(PANEL_LIGHT);
        timerBar.setBorderPainted(false);
        timerPanelPreferredSize();
        timePanel.add(label("TIME LEFT", 10, MUTED, true), BorderLayout.WEST);
        timePanel.add(timerBar, BorderLayout.CENTER);
        timePanel.add(timerLabel, BorderLayout.EAST);
        timePanel.setPreferredSize(new Dimension(600, 18));
        timePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

        center.add(questionCard);
        center.add(Box.createVerticalStrut(15));
        center.add(timePanel);
        center.add(Box.createVerticalStrut(18));

        optionsPanel.setOpaque(false);
        optionsPanel.setPreferredSize(new Dimension(600, 128));
        optionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 132));
        for (int i = 0; i < optionButtons.length; i++) {
            final int choice = i;
            JButton b = optionButton("Option " + (i + 1));
            b.addActionListener(e -> selectAnswer(choice));
            optionButtons[i] = b;
            optionsPanel.add(b);
        }
        center.add(optionsPanel);
        center.add(Box.createVerticalStrut(10));

        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        feedbackLabel.setForeground(MUTED);
        feedbackLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        center.add(feedbackLabel);
        center.add(Box.createVerticalStrut(12));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        JLabel note = label("Choose one option to lock in your answer.", 11, MUTED, false);
        bottom.add(note, BorderLayout.WEST);
        nextButton.setEnabled(false);
        nextButton.addActionListener(e -> nextQuestion());
        bottom.add(nextButton, BorderLayout.EAST);

        page.add(center, BorderLayout.CENTER);
        page.add(bottom, BorderLayout.SOUTH);
        return page;
    }

    private void timerPanelPreferredSize() {
        timerBar.setPreferredSize(new Dimension(400, 8));
        timerBar.setMinimumSize(new Dimension(80, 8));
        timerBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 8));
    }

    private JPanel buildResultScreen() {
        JPanel page = new JPanel(new GridBagLayout());
        page.setBackground(BG);
        // Content is populated after the quiz finishes.
        return page;
    }

    private void startQuiz() {
        String selected = (String) categoryBox.getSelectedItem();
        List<Question> pool = new ArrayList<>();
        if ("Mixed Quiz".equals(selected)) {
            List<Question> javaQuestions = new ArrayList<>();
            List<Question> computerQuestions = new ArrayList<>();
            for (Question q : questionBank) {
                if ("Java Fundamentals".equals(q.category)) javaQuestions.add(q);
                else computerQuestions.add(q);
            }
            for (int i = 0; i < 5; i++) {
                pool.add(javaQuestions.get(i));
                pool.add(computerQuestions.get(i));
            }
        } else {
            for (Question q : questionBank) if (selected.equals(q.category)) pool.add(q);
        }
        activeQuestions = new ArrayList<>();
        for (int i = 0; i < Math.min(10, pool.size()); i++) activeQuestions.add(pool.get(i));
        questionIndex = 0;
        score = 0;
        cardLayout.show(root, "quiz");
        showQuestion();
    }

    private void showQuestion() {
        if (questionIndex >= activeQuestions.size()) {
            finishQuiz();
            return;
        }
        answered = false;
        secondsLeft = QUESTION_SECONDS;
        Question q = activeQuestions.get(questionIndex);
        progressLabel.setText(String.format("QUESTION %02d OF %02d", questionIndex + 1, activeQuestions.size()));
        scoreLabel.setText("SCORE " + score);
        progressBar.setMaximum(activeQuestions.size());
        progressBar.setValue(questionIndex);
        questionLabel.setText("<html><div style='width:620px'>" + escapeHtml(q.prompt) + "</div></html>");
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText("  " + (char)('A' + i) + "     " + q.options[i]);
            optionButtons[i].setEnabled(true);
            optionButtons[i].setBackground(PANEL_LIGHT);
            optionButtons[i].setForeground(TEXT);
            optionButtons[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(58, 74, 107), 1),
                    new EmptyBorder(13, 12, 13, 12)));
        }
        feedbackLabel.setText(" ");
        nextButton.setEnabled(false);
        nextButton.setText(questionIndex == activeQuestions.size() - 1 ? "SEE RESULTS  >" : "NEXT QUESTION  >");
        updateTimerDisplay();
        countdown.restart();
    }

    private void selectAnswer(int choice) {
        if (answered) return;
        answered = true;
        countdown.stop();
        Question q = activeQuestions.get(questionIndex);
        boolean correct = choice == q.answerIndex;
        if (correct) score++;
        for (JButton b : optionButtons) b.setEnabled(false);
        optionButtons[q.answerIndex].setBackground(new Color(32, 108, 83));
        optionButtons[q.answerIndex].setBorder(BorderFactory.createLineBorder(GREEN, 2));
        if (!correct) {
            optionButtons[choice].setBackground(new Color(112, 49, 64));
            optionButtons[choice].setBorder(BorderFactory.createLineBorder(RED, 2));
        }
        feedbackLabel.setForeground(correct ? GREEN : RED);
        feedbackLabel.setText("<html><div style='width:760px'>" + escapeHtml((correct ? "Correct! " : "Not quite. ") + q.explanation) + "</div></html>");
        scoreLabel.setText("SCORE " + score);
        nextButton.setEnabled(true);
    }

    private void tick() {
        if (answered) return;
        secondsLeft--;
        updateTimerDisplay();
        if (secondsLeft <= 0) {
            answered = true;
            countdown.stop();
            Question q = activeQuestions.get(questionIndex);
            for (JButton b : optionButtons) b.setEnabled(false);
            optionButtons[q.answerIndex].setBackground(new Color(32, 108, 83));
            optionButtons[q.answerIndex].setBorder(BorderFactory.createLineBorder(GREEN, 2));
            feedbackLabel.setForeground(RED);
            feedbackLabel.setText("<html><div style='width:760px'>" + escapeHtml("Time is up. " + q.explanation) + "</div></html>");
            nextButton.setEnabled(true);
        }
    }

    private void updateTimerDisplay() {
        timerLabel.setText(secondsLeft + "s");
        timerBar.setValue(secondsLeft);
        timerBar.setForeground(secondsLeft <= 5 ? RED : GREEN);
    }

    private void nextQuestion() {
        questionIndex++;
        showQuestion();
    }

    private void finishQuiz() {
        countdown.stop();
        int total = activeQuestions.size();
        int percent = total == 0 ? 0 : (score * 100 / total);
        String message = percent >= 80 ? "Excellent work! You have a strong grasp of the topics." :
                percent >= 50 ? "Good effort! Review the explanations and try again." :
                "Keep practising. Every attempt is a chance to improve.";

        JPanel page = new JPanel(new GridBagLayout());
        page.setBackground(BG);
        JPanel card = new JPanel();
        card.setBackground(PANEL);
        card.setBorder(new EmptyBorder(36, 50, 36, 50));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(560, 420));

        JLabel eyebrow = label("QUIZ COMPLETED", 11, ACCENT, true);
        eyebrow.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(eyebrow);
        card.add(Box.createVerticalStrut(12));
        JLabel title = label("Your result", 32, TEXT, true);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(8));
        JLabel mark = label(score + " / " + total, 48, GREEN, true);
        mark.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(mark);
        card.add(Box.createVerticalStrut(4));
        JLabel percentage = label(percent + "% score", 15, MUTED, false);
        percentage.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(percentage);
        card.add(Box.createVerticalStrut(20));
        JLabel msg = label(message, 13, TEXT, false);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(msg);
        card.add(Box.createVerticalStrut(10));
        JLabel breakdown = label("Correct: " + score + "     Incorrect / unanswered: " + (total - score), 12, MUTED, false);
        breakdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(breakdown);
        card.add(Box.createVerticalStrut(24));
        JButton restart = primaryButton("PLAY AGAIN  ↻");
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);
        restart.addActionListener(e -> cardLayout.show(root, "welcome"));
        card.add(restart);
        card.add(Box.createVerticalStrut(12));
        JLabel footer = label("Keep learning. Keep improving.", 11, MUTED, false);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(footer);
        page.add(card);
        if (root.getComponentCount() > 2) root.remove(2);
        root.add(page, "result");
        root.revalidate();
        root.repaint();
        cardLayout.show(root, "result");
    }

    private JLabel label(String text, int size, Color color, boolean bold) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, size));
        l.setForeground(color);
        return l;
    }

    private JButton primaryButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBackground(ACCENT);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(15, 30, 15, 30));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(220, 48));
        return b;
    }

    private JButton optionButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.PLAIN, 13));
        b.setBackground(PANEL_LIGHT);
        b.setForeground(TEXT);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(58, 74, 107), 1),
                new EmptyBorder(13, 12, 13, 12)));
        return b;
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static class Question {
        final String category;
        final String prompt;
        final String[] options;
        final int answerIndex;
        final String explanation;
        Question(String category, String prompt, String[] options, int answerIndex, String explanation) {
            this.category = category;
            this.prompt = prompt;
            this.options = options;
            this.answerIndex = answerIndex;
            this.explanation = explanation;
        }
    }

    private List<Question> buildQuestionBank() {
        List<Question> qs = new ArrayList<>();
        qs.add(new Question("Java Fundamentals", "Which keyword is used to inherit a class in Java?", new String[]{"implements", "extends", "inherits", "superclass"}, 1, "The extends keyword creates an inheritance relationship between classes."));
        qs.add(new Question("Java Fundamentals", "Which method is the entry point of a standard Java application?", new String[]{"start()", "run()", "main()", "init()"}, 2, "The JVM begins a standard application by calling public static void main(String[] args)."));
        qs.add(new Question("Java Fundamentals", "Which collection does not allow duplicate elements?", new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"}, 2, "A Set represents a collection of unique elements."));
        qs.add(new Question("Java Fundamentals", "Which concept bundles data and methods into one unit?", new String[]{"Inheritance", "Encapsulation", "Compilation", "Iteration"}, 1, "Encapsulation combines state and behavior and controls access to an object's data."));
        qs.add(new Question("Java Fundamentals", "Which package contains Swing GUI components?", new String[]{"java.io", "java.net", "javax.swing", "java.sql"}, 2, "Swing components such as JFrame, JButton and JPanel are in javax.swing."));
        qs.add(new Question("Java Fundamentals", "Which keyword prevents a method from being overridden?", new String[]{"static", "private", "final", "const"}, 2, "A final method cannot be overridden by a subclass."));
        qs.add(new Question("Java Fundamentals", "Which block is commonly used to release resources after try/catch?", new String[]{"throw", "finally", "finalize", "throws"}, 1, "The finally block normally runs whether an exception occurs or not."));
        qs.add(new Question("Java Fundamentals", "Which type stores true or false?", new String[]{"int", "char", "boolean", "String"}, 2, "The primitive boolean type represents true or false."));
        qs.add(new Question("Java Fundamentals", "Which interface is used to receive button-click events in Swing?", new String[]{"Runnable", "ActionListener", "Serializable", "Comparable"}, 1, "ActionListener handles action events generated by buttons and similar controls."));
        qs.add(new Question("Java Fundamentals", "Which feature allows the same method name with different parameter lists?", new String[]{"Overloading", "Overriding", "Casting", "Packaging"}, 0, "Method overloading uses the same name with different parameter lists."));

        qs.add(new Question("Computer Basics", "Which component is often called the brain of the computer?", new String[]{"RAM", "CPU", "SSD", "Monitor"}, 1, "The CPU executes instructions and coordinates processing."));
        qs.add(new Question("Computer Basics", "Which memory loses its contents when power is removed?", new String[]{"ROM", "SSD", "RAM", "Blu-ray"}, 2, "RAM is volatile working memory."));
        qs.add(new Question("Computer Basics", "What does URL stand for?", new String[]{"Uniform Resource Locator", "Universal Record Link", "Unified Routing List", "User Resource Login"}, 0, "A URL identifies the address of a resource on a network."));
        qs.add(new Question("Computer Basics", "Which protocol is primarily used to transfer web pages?", new String[]{"FTP", "HTTP", "SMTP", "SSH"}, 1, "HTTP is the application protocol used for web communication."));
        qs.add(new Question("Computer Basics", "Which number system uses only 0 and 1?", new String[]{"Decimal", "Octal", "Binary", "Hexadecimal"}, 2, "Binary is base 2 and uses the digits 0 and 1."));
        qs.add(new Question("Computer Basics", "Which device forwards packets between networks?", new String[]{"Keyboard", "Router", "Printer", "Scanner"}, 1, "A router forwards packets between different IP networks."));
        qs.add(new Question("Computer Basics", "Which of these is an operating system?", new String[]{"Oracle", "Linux", "HTML", "Java"}, 1, "Linux is an operating system kernel commonly distributed as a complete OS."));
        qs.add(new Question("Computer Basics", "Which unit is commonly used to measure processor clock rate?", new String[]{"Pixels", "Hertz", "Litres", "Lumens"}, 1, "Clock frequency is measured in hertz, often gigahertz for CPUs."));
        qs.add(new Question("Computer Basics", "What does the 'S' in HTTPS indicate?", new String[]{"Simple", "Secure", "Server", "System"}, 1, "HTTPS uses TLS to protect data exchanged with a web server."));
        qs.add(new Question("Computer Basics", "Which storage device has no moving mechanical parts?", new String[]{"HDD", "Floppy disk", "SSD", "DVD drive"}, 2, "A solid-state drive uses flash memory rather than spinning platters."));
        return qs;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
            catch (Exception ignored) { }
            QuizMaster app = new QuizMaster();
            app.setVisible(true);
        });
    }
}
