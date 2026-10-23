# FSJP_QuizMaster_Project
Quiz Master: Interactive Quiz Application using Java Swing

Quiz Master
Java desktop application · Interactive MCQ quiz

The user selects a category, answers 10 multiple-choice questions, gets 15 seconds per question, receives instant feedback, and sees their final score.



3 categories

Mixed Quiz, Java Fundamentals, Computer Basics


10 questions

Four answer options per question


15 seconds

Countdown for each question


Final results

Score, percentage and feedback




The easiest way to understand the application is to imagine using it:
1. Open the program.
2. Select a quiz category.
3. Click START QUIZ.
4. Read the question and select an answer before the timer expires.
5. See whether your answer was correct and read the explanation.
6. Click NEXT QUESTION and repeat until all 10 questions are finished.
7. View your result and choose PLAY AGAIN to start another attempt.
That is the complete purpose of the project.

Part 1 — Page-by-page explanation of the PDF
Page 1 — Cover page
The first physical page is your academic cover page.

It contains the college and department, course name, project title, student details, and academic submission date.

Your project title is:

Quiz Master: Interactive Quiz Application using Java Swing

Let's understand the title.
- Quiz Master: The name of your application.
- Interactive: The user does not just read information; they click buttons, choose answers, and receive immediate feedback.
- Quiz Application: The program presents questions and checks responses.
- Java Swing: The Java GUI library used to build the application's windows, buttons, labels, dropdowns, and progress indicators.
The course heading, Full Stack Java Programming, identifies the subject for which the project is submitted.

The cover page also contains your student details: Shirish Satish Shinde, Roll No. 82, SE CMPN.

What you should understand: The cover page identifies the project and establishes that it is an academic term-work submission. It does not explain the technical implementation.
Page 2 — Introduction
This page explains what the project does, its objectives, and its scope.
1.1 About the Project
The report states that Quiz Master is a desktop application built using Java Swing and AWT. FSJP_Quiz_Master_Mini_Project_Report final.pdf


Let's break down its main features.

A. Quiz categories

The user can select from three categories:
- Java Fundamentals — basic Java programming concepts.
- Computer Basics — general computer and networking fundamentals.
- Mixed Quiz — a combination of the two categories.
The category selection determines which questions the application displays.

B. Multiple-choice questions

Each question has four possible answers, generally represented by A, B, C, and D. Only one is treated as the correct answer.

For example:

Which keyword is used to inherit a class in Java?

A. implements

B. extends

C. inherits

D. superclass

The correct answer is B, extends.

C. Fifteen-second timer

Each question has a countdown. When it reaches zero, the program locks the question and reveals the correct answer and explanation. The user can then continue.

D. Instant feedback

When the user selects an answer, the app checks it against the stored correct answer.
- A correct response increases the score.
- An incorrect response does not increase the score.
- The correct answer is highlighted.
- An explanation is displayed.
E. Final result

After 10 questions, the application displays the score, percentage, a performance message, and a replay option.
1.2 Objectives
The objectives are the goals the project is intended to achieve. They include:
Objective	Meaning in your project
Object-oriented programming	Use a class and objects to organize the application and quiz questions.
GUI development	Build a visual interface using Swing components.
Event handling	React when users click answer or navigation buttons.
Timer implementation	Count down from 15 seconds for each question.
Score calculation	Count correct answers and calculate the percentage.
Validation	Ensure a question is answered at most once.
Portability	Run using a standard JDK without extra libraries.

The distinction is important: an objective describes what you want the project to accomplish; an implemented feature is how you actually accomplish it.
1.3 Scope
Scope means what the current version includes and excludes.

The current version includes category selection, timed questions, feedback, explanations, scoring, and replay.

It does not include user accounts, an online multiplayer mode, a database, a question-authoring portal, or permanent score history. Those are listed as possible future enhancements.

In other words, it is a standalone, single-user application rather than an online testing platform.

Page 3 — Analysis
Analysis means understanding the problem, identifying the requirements, and deciding how the system should behave before examining the implementation.
2.1 Problem Statement
The problem statement explains the task the program solves.

A student needs a simple quiz tool that can present MCQs, impose a time limit, evaluate answers, provide feedback, track marks, and display the final performance.

The software must also be easy to use and capable of running on a computer with Java installed.

Think of the problem statement as the answer to this question:

“Why are we building this project?”

We are building it to provide an interactive, timed quiz experience.
2.2 Functional Requirements
A functional requirement describes something the system must do.

Your PDF lists these core requirements:
Requirement	How it appears in the application
Welcome screen	Shows the title and category dropdown.
Category selection	Lets the user choose one of three categories.
Ten questions	Presents a 10-question quiz attempt.
Four options	Displays four answer buttons per question.
Countdown	Starts the 15-second timer.
Answer submission	Allows the user to select an answer.
Answer locking	Prevents another answer after submission or timeout.
Feedback	Highlights the correct answer and gives an explanation.
Progress and score	Shows the question number, progress bar, and score.
Results	Shows marks, percentage, and performance feedback.
Replay	Returns to the welcome screen for another quiz.

For example, after the user selects B, the other answer buttons become disabled. This demonstrates the functional requirement that a question cannot be answered more than once.
2.3 Non-Functional Requirements
A non-functional requirement describes how well the system should work, rather than a particular user-facing feature.

The PDF identifies five important qualities. FSJP_Quiz_Master_Mini_Project_Report final.pdf


Usability: The labels and buttons should be easy to understand. The user should not need complicated instructions.

Performance: Clicking a button or updating the timer should not cause noticeable lag.

Portability: The program uses standard Java libraries and can run on supported Windows, Linux, or macOS systems with a suitable JDK.

Reliability: A submitted answer must be counted only once, and the timer must stop when an answer is handled.

Maintainability: The program uses named methods and a Question class so that its responsibilities are easier to understand and update.

A final requirement is minimal dependencies: the application does not require a third-party library, internet connection, or database server.
Remember this distinction
- Functional requirement: What does the application do?
- Non-functional requirement: How should the application operate?
For example, “display four answer buttons” is functional. “Respond to clicks without noticeable delay” is non-functional.

Page 4 — Quiz Logic Analysis
This page explains the internal rules of the quiz before you reach the source code.
FSJP_Quiz_Master_Mini_Project_Report final.pdf
2.4 Quiz Logic Analysis
1. Quiz selection

The selected category determines the question set. The Mixed Quiz combines five Java questions and five Computer Basics questions.

In the current implementation, the questions are taken in the order in which they were stored in the program; the quiz does not randomize their order.

2. Question model

Each question stores five pieces of information:
- Its category.
- The question text.
- Four answer choices.
- The index of the correct answer.
- An explanation of the answer.
For example, a question about Java inheritance stores extends as the correct option and an explanation of what inheritance means.

3. Countdown

The timer updates every 1,000 milliseconds, which equals one second.

The remaining-time value goes from 15 to 14 to 13, and so on, until it reaches zero or the user answers.

4. Answer checking

The program compares the selected option's index with the correct answer index stored in the question.

If they match, the response is correct.

5. Feedback

The correct option is shown in green. When an incorrect option is selected, that choice is also distinguished in red. The program displays an explanation.

6. Scoring

One mark is awarded for each correct response. Incorrect and unanswered questions do not increase the score.

7. Progress

The question number, progress bar, and score are updated as the quiz advances.

8. Results

Once the user has completed all 10 questions, the program calculates the percentage and displays a performance message.

9. Replay

The user returns to the welcome screen. Starting a new quiz resets the score and question progress.
2.5 Structure of the Program
This page explains the Java methods you will later see in the code.
Method or component	Simple meaning
QuizMaster()	Initializes the main application window.
buildWelcomeScreen()	Creates the welcome screen and category selector.
buildQuizScreen()	Creates the question screen, options, timer, and progress indicators.
startQuiz()	Chooses the question set and initializes a quiz attempt.
showQuestion()	Displays the current question and resets its timer and buttons.
selectAnswer(int)	Checks an answer, updates marks, and displays feedback.
tick()	Updates the countdown and handles timeout.
updateTimerDisplay()	Updates the timer label and progress bar.
nextQuestion()	Advances to the next question.
finishQuiz()	Calculates and displays the final result.
Question	Stores a single question's data.
main()	Starts the application.

You don't need to memorize the complete code at this stage. First, understand the responsibility of each method. Then, when you read the code, you will know why that method exists.

Page 5 — System Specification
This page explains the computer and software needed to run the project, plus the controls used to operate it.
3.1 Hardware Requirements
The PDF recommends an Intel Core i3 or equivalent processor, 4 GB RAM, around 100 MB of free space for project files and JDK tools, a display resolution of 1024 × 768 or higher, and a keyboard and mouse or touchpad.

These are the report's recommended specifications, not a measurement of the program's actual resource consumption.
3.2 Software Requirements
The listed requirements are:
- Operating system: Windows 10/11, Linux, or macOS.
- Language: Java.
- JDK: version 17 or later recommended.
- Libraries: javax.swing, java.awt, java.awt.event, and java.util.
- Editor: Visual Studio Code, IntelliJ IDEA, Eclipse, or another text editor.
- Compiler/runtime: javac and java.
What is a JDK?

The Java Development Kit provides the tools needed to develop Java programs, including the compiler.
- javac converts Java source code into bytecode.
- java runs the compiled application on the Java Virtual Machine.
3.3 Controls and Workflow
The application uses these controls:
Control	Action
Category dropdown	Selects the quiz category.
START QUIZ	Starts a new quiz with 10 questions.
Answer buttons A–D	Selects an answer.
NEXT QUESTION	Moves to the next question after feedback.
Timer reaches zero	Locks the question and displays its explanation.
PLAY AGAIN	Returns to the welcome screen.

The main workflow can be represented simply as:
Welcome Screen

Select category

Start Quiz

Load questions and reset score

Display Question

Start countdown and show four options

Answer or Timeout

Lock question and show feedback

Next Question

Repeat until all 10 questions are completed

Result Screen

Display marks, percentage, and replay option


This is the life cycle of your application from the user's point of view.

Page 6 — Compiling, running, and testing
3.4 Compile and Run
The source file must be saved as QuizMaster.java.

Open a terminal in the folder where the source file is saved and enter:
javac QuizMaster.java
java QuizMaster


The first command compiles the source. The second launches the application.

The filename matters because the public Java class is named QuizMaster. In Java, a public top-level class must be saved in a file with the matching name.

If compilation succeeds, the class file is generated and the app can be run.
3.5 Basic Test Cases
Testing means checking that the software behaves as expected under different conditions.
Test	Expected result
Launch the program	The welcome screen appears.
Select a correct answer	The correct choice is highlighted and the score increases.
Select an incorrect answer	The correct and selected options are distinguished; the score does not increase.
Wait 15 seconds	The question locks and feedback appears.
Complete all 10 questions	The final score, percentage, and performance message appear.
Play again	The welcome screen appears, ready for a new attempt with a reset score.

This is called basic functional testing. Each test checks whether a particular feature works.

One useful testing habit is to test both the normal case and the boundary case. For example, choose an answer before the timer ends, and then separately test what happens when the timer reaches zero.

Part 2 — Understanding the Java source code
The source code begins on PDF page 8, which is numbered Page 7 inside the report. It continues through PDF page 13, numbered Page 12.

The best way to understand these pages is to divide the code into small sections rather than trying to memorize it from top to bottom.
Page 7 of the report — Imports, class, variables, and constructor
4.1 Import statements
The code begins with:
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


An import statement makes classes from other Java packages available to your code.

Let's understand them individually.
Import	Purpose
javax.swing.*	Provides GUI components such as JFrame, JPanel, JLabel, JButton, JComboBox, and JProgressBar.
EmptyBorder	Adds spacing around components.
java.awt.*	Provides colors, fonts, layouts, dimensions, and other GUI utilities.
java.awt.event.*	Supports handling user actions and events.
ArrayList	Provides a list whose size can change.
List	Provides the interface for storing and accessing an ordered collection.
Arrays	Provides array-related utility methods.

A small observation: Arrays is imported in the PDF but is not needed by the implementation shown. An unused import generally does not prevent Java from compiling the program.
4.2 What is Java Swing?
Swing is Java's graphical user interface (GUI) toolkit.

Without a GUI, a simple quiz could ask questions and accept answers through the terminal. Swing lets you show a proper application window containing buttons, labels, a category dropdown, a timer, and progress bars.

The project uses several important Swing components:

JFrame — main window

The outer window that holds the application. It provides the window title, size, and close behavior.




JPanel — container

A panel groups related components, such as the question text, answer buttons, or welcome screen content.




JLabel — text display

Displays the question, timer value, score, instructions, and feedback.




JButton — clickable button

Starts a quiz, submits an answer, advances a question, or allows the user to play again.




JComboBox — dropdown

Allows the user to select Mixed Quiz, Java Fundamentals, or Computer Basics.




JProgressBar — visual progress

Displays the remaining time and how far the user has advanced through the questions.



4.3 The main class
The code declares:
public class QuizMaster extends JFrame {


Break this line into parts:
- public means the class is accessible where Java's access rules permit.
- class QuizMaster declares a class named QuizMaster.
- extends JFrame means QuizMaster inherits from JFrame.
Because it extends JFrame, the QuizMaster object itself is the main application window.

This is an example of inheritance, one of the core concepts of object-oriented programming.
4.4 Colours and constants
The code defines colour values such as:
private static final Color BG =
    new Color(13, 20, 38);

private static final Color ACCENT =
    new Color(91, 141, 255);

private static final Color GREEN =
    new Color(54, 190, 130);

private static final Color RED =
    new Color(235, 95, 111);


Each colour is represented using RGB values: red, green, and blue components, each ranging from 0 to 255.

These constants establish a consistent visual design:
- BG is the dark background.
- ACCENT is the blue accent used for interactive elements.
- GREEN indicates a correct answer.
- RED indicates an incorrect response or a warning.
The declaration private static final means the field belongs to the class and is not intended to be reassigned after initialization. This makes the design easier to maintain.

If you wanted to change the application's accent colour later, you could update the corresponding constant instead of searching for repeated RGB values throughout the program.
4.5 Variables that store the quiz state
The program defines fields including:
private int questionIndex = 0;
private int score = 0;
private int secondsLeft = QUESTION_SECONDS;
private boolean answered = false;
private Timer countdown;


These are essential to the operation of the quiz.
Variable	What it remembers
questionIndex	Which question is currently being shown.
score	How many questions have been answered correctly.
secondsLeft	How much time remains for the current question.
answered	Whether the current question has already been handled.
countdown	The Swing timer that generates countdown events.
activeQuestions	The questions selected for the current quiz attempt.
questionBank	The stored collection of available questions.

Notice that questionIndex begins at zero. Java lists and arrays start from index 0, whereas the screen displays human-friendly numbers starting from Question 1.

Therefore:
- questionIndex = 0 means Question 1.
- questionIndex = 1 means Question 2.
- questionIndex = 9 means Question 10.
The code adds one when displaying the current question number to the user.
4.6 Why use CardLayout?
The program includes:
private final CardLayout cardLayout =
    new CardLayout();

private final JPanel root =
    new JPanel(cardLayout);


CardLayout lets the application switch between different screen panels in the same main window.

Imagine three cards:
1. Welcome screen.
2. Quiz screen.
3. Result screen.
When the user starts the quiz, the welcome card is hidden and the quiz card appears. At the end, the result card is shown.

This avoids opening a new window for every stage and keeps the application's navigation organized.
4.7 The constructor
The constructor is named QuizMaster() and runs when a new QuizMaster object is created.

In the report, the constructor:
- Sets the window title.
- Establishes the closing behavior, size, and screen position.
- Initializes the welcome, quiz, and result panels.
- Adds these panels to the main container.
- Creates the countdown timer.
- Shows the welcome screen initially.
The timer is created as:
countdown = new Timer(1000, e -> tick());


The value 1000 means 1,000 milliseconds, or approximately one second between timer events.

The expression e -> tick() is a Java lambda expression. It tells the timer to call the tick() method when its event occurs.

The constructor connects the main parts of the application, but it does not yet mean a quiz has started. Initially, the user sees the welcome screen.

Page 8 of the report — Welcome screen and quiz screen
This part of the code builds the visual interface.
4.8 buildWelcomeScreen()
The method:
private JPanel buildWelcomeScreen() {


creates the first screen.

It adds the project logo, title, short description, category selector, instructions, and start button.

The category selector is declared as:
new JComboBox<>(
    new String[] {
        "Mixed Quiz",
        "Java Fundamentals",
        "Computer Basics"
    }
);


This creates a dropdown populated with the three choices.

The start button is connected to the following action:
start.addActionListener(e -> startQuiz());


In simple language:

When the user clicks START QUIZ, Java calls startQuiz().

This is event-driven programming. Instead of running the next step automatically, the application waits for a user action.
4.9 buildQuizScreen()
This method assembles the main question screen.

It includes:
- A header containing the application name and course label.
- A question counter and current score.
- A progress bar.
- A card displaying the question.
- A countdown timer and timer bar.
- Four answer buttons.
- A feedback label.
- A NEXT QUESTION button.
The four answer buttons are created inside a loop:
for (int i = 0;
     i < optionButtons.length;
     i++) {

    final int choice = i;

    JButton b = optionButton("Option " + (i + 1));

    b.addActionListener(
        e -> selectAnswer(choice)
    );

    optionButtons[i] = b;
    optionsPanel.add(b);
}


Let’s understand the logic.

The array optionButtons has four positions. The loop runs four times, creating one button per option.

The loop variable i takes the values 0, 1, 2, and 3. Each button receives an event listener that calls selectAnswer() with its corresponding option index.

The choice variable preserves the index assigned to that particular button.

As a result, clicking the first button submits index 0; clicking the second submits index 1; and so on.

This is why the application can identify exactly which answer was selected.
Why does NEXT QUESTION start disabled?
Before the user answers, the next-question button is disabled.

The user is expected to attempt the question first. Once an answer is submitted or the timer expires, the code enables the next-question button.

This is a simple form of input control. It helps the user follow the intended workflow rather than skipping questions without handling them.

Page 9 of the report — Starting a quiz and displaying a question
This section is where the quiz logic begins to work.
4.10 startQuiz()
The method first reads the category chosen in the dropdown:
String selected =
    (String) categoryBox.getSelectedItem();


It then prepares a list of questions for that quiz attempt.

For an individual category, the code selects questions whose stored category matches the user's choice.

For the Mixed Quiz, it separates the Java questions from the Computer Basics questions and takes five from each, alternating between the two categories.

The selected questions are copied into activeQuestions.

Finally, the method resets the quiz state:
questionIndex = 0;
score = 0;


It displays the quiz screen and calls showQuestion().

This means starting a quiz always begins with Question 1 and a score of zero.
4.11 showQuestion()
This method displays the current question.

The most important steps are:

Step 1 — Check whether the quiz is complete.

If the question index has reached the end of the selected list, finishQuiz() is called.

Step 2 — Reset question-specific state.
answered = false;
secondsLeft = QUESTION_SECONDS;


This ensures that the question begins unanswered and has a fresh 15-second countdown.

Step 3 — Update the progress indicators.

The method updates the question number, score, and progress bar.

Step 4 — Display the current question.

The question text is taken from the current Question object and placed in the question label.

Step 5 — Fill the four answer buttons.

Each button is assigned the relevant option text. All four are enabled, and their previous colours and borders are reset.

Step 6 — Reset feedback and navigation.

The feedback label is cleared, and NEXT QUESTION is disabled until the question has been handled.

Step 7 — Restart the countdown.
countdown.restart();


The timer starts again for the newly displayed question.
Why is resetting so important?
Suppose the user answers Question 1 correctly. The correct button becomes green, the score becomes 1, and the timer stops.

If Question 2 were displayed without resetting the interface, the previous question's green colour, disabled buttons, or feedback text could remain visible.

showQuestion() prepares the interface for a completely new question.

Page 10 of the report — Answer checking and countdown logic
This is one of the most important parts of the entire project.
4.12 selectAnswer(int choice)
This method processes an answer selected by the user.

Its first check is:
if (answered) return;


This means: if the current question has already been answered or locked, do nothing.

Next, the program marks the question as handled and stops the countdown.

It then retrieves the current question and compares the chosen index with the correct answer index:
boolean correct =
    choice == q.answerIndex;


If the indexes match, the score increases:
if (correct) score++;


Example: If the correct index is 1 and the user selects index 1, the answer is correct and the score increases by one.

If the user selects index 3, the answer is incorrect and the score remains unchanged.

After checking the answer, the program disables all four buttons. It highlights the correct option in green. If the selected answer was wrong, the selected option is also highlighted in red.

Finally, it shows feedback and enables NEXT QUESTION.

The method is responsible for five things:
1. Preventing duplicate submissions.
2. Stopping the timer.
3. Checking correctness and updating the score.
4. Locking the answer buttons and highlighting the relevant choices.
5. Displaying feedback and enabling navigation.
4.13 tick()
The countdown is handled by the tick() method.

The timer invokes this method approximately once per second.

Conceptually, the logic is:
Has the question already been handled?
    Yes -> do nothing.
    No  -> subtract one second.

Update the timer display.

Has the timer reached zero?
    Yes -> lock the question,
           reveal the correct choice,
           display feedback,
           enable Next Question.


In the source code, the countdown is reduced through:
secondsLeft--;


When secondsLeft becomes zero or less, the program sets answered to true and stops the timer.

It disables the answer buttons and highlights the correct option. The feedback says that time is up and provides the explanation.

A timeout does not increase the score.

This ensures that the user cannot wait for the timer to expire and then submit a response afterward.
4.14 updateTimerDisplay()
This method updates the visible timer label and progress bar.

It also changes the timer bar's colour depending on the remaining time:
timerBar.setForeground(
    secondsLeft <= 5 ? RED : GREEN
);


The ? : syntax is the ternary operator. It is a compact way to choose between two values.

Here, the bar is red if five seconds or fewer remain; otherwise, it is green.

The timer therefore provides both numerical information and a visual warning.
4.15 nextQuestion()
This method is deliberately simple:
private void nextQuestion() {
    questionIndex++;
    showQuestion();
}


It increases the question index by one and calls showQuestion().

The display method then either loads the next question or detects that the quiz has finished.

This division is useful: one method handles navigation, while the other is responsible for setting up and displaying each question.

Page 11 of the report — Results, styling helpers, and the Question class
4.16 finishQuiz()
When all the questions have been completed, finishQuiz() stops the timer and calculates the final result.

The score percentage is calculated using:
int percent =
    total == 0 ? 0 : (score * 100 / total);


Here:
- score is the number of correct answers.
- total is the number of questions in the quiz.
- percent is the percentage score.
The first part prevents division by zero if there are no questions.

For this project, there are 10 questions. Consider these examples:
Correct answers	Calculation	Percentage
10	\(10 \times 100 \div 10\)	100%
8	\(8 \times 100 \div 10\)	80%
6	\(6 \times 100 \div 10\)	60%
4	\(4 \times 100 \div 10\)	40%

The program then chooses a message based on the percentage.
- 80% or more: “Excellent work!”
- 50% to below 80%: “Good effort!”
- Below 50%: Encourages the user to keep practising.
The result screen also displays the number of correct answers and the number of incorrect or unanswered questions. It adds the PLAY AGAIN button.

The result panel is constructed dynamically when the quiz finishes. The method replaces the initial empty result panel with the populated result screen, refreshes the component layout, and shows the result card.
4.17 Styling helper methods
The code includes helper methods such as:
private JLabel label(
    String text, int size,
    Color color, boolean bold
)


This method creates a label with a specified text, font size, colour, and boldness.

The benefit is consistency. Instead of repeatedly writing the same font and colour setup throughout the source code, the program can use one reusable method.

Similarly, primaryButton() creates styled buttons such as START QUIZ and PLAY AGAIN, while optionButton() creates the answer buttons.

These are examples of code reuse and modular design.
4.18 escapeHtml()
The report includes this helper:
private String escapeHtml(String text) {
    return text.replace("&", "&amp;")
               .replace("<", "&lt;")
               .replace(">", "&gt;");
}


Swing labels can display a subset of HTML when the text begins with an HTML marker such as <html>.

Because the question and explanation text is placed inside HTML-formatted labels, certain characters need to be escaped so that they are displayed as text rather than interpreted as HTML markup.

The method replaces special characters with their HTML-safe representations.

This helps ensure that question text and explanations appear as intended.
4.19 The Question class
The project defines an inner class called Question:
private static class Question {
    final String category;
    final String prompt;
    final String[] options;
    final int answerIndex;
    final String explanation;
}


This class represents one quiz question.

Think of it as a record card containing everything necessary to ask, evaluate, and explain one MCQ.

For example, one question object could contain the following information:
EXAMPLE QUESTION OBJECT
Java inheritance
Category: Java Fundamentals

Question: Which keyword is used to inherit a class in Java?

Options: implements, extends, inherits, superclass

Correct answer index: 1 — the second option, extends.

Explanation: The extends keyword establishes an inheritance relationship between classes.

The index is zero-based, so index 0 refers to the first option, index 1 to the second, index 2 to the third, and index 3 to the fourth.

The constructor of Question assigns the supplied values to the fields. The fields are declared final, meaning they cannot be reassigned after initialization.

This object-oriented design keeps question data together rather than spreading the prompt, answers, category, and explanation across unrelated variables.

Page 12 of the report — Question bank and main()
4.20 buildQuestionBank()
The method builds the application's stored collection of questions.

It creates a list:
List<Question> qs =
    new ArrayList<>();


Then it adds question objects to that list.

The PDF contains 20 questions in total:
- 10 Java Fundamentals questions.
- 10 Computer Basics questions.
The Java questions cover concepts such as inheritance, the main() method, collections, encapsulation, Swing, the final keyword, exception handling, Boolean values, event listeners, and method overloading.

The Computer Basics questions cover the CPU, RAM, URLs, HTTP, binary numbers, routers, operating systems, processor frequency, HTTPS, and SSD storage.

Each question is created using a statement that supplies the category, prompt, four options, the correct answer index, and the explanation.

For example, a line such as:
qs.add(new Question(
    "Java Fundamentals",
    "Which keyword is used to inherit a class in Java?",
    new String[]{
        "implements",
        "extends",
        "inherits",
        "superclass"
    },
    1,
    "The extends keyword creates an inheritance relationship between classes."
));


creates one question and adds it to the collection.

At the end, the method returns the list of questions.

Important detail: The source code contains 20 stored questions, but each quiz attempt shows 10. The category selection and quiz preparation logic decide which 10 are used.
4.21 main(String[] args)
The main() method is the standard entry point for launching a Java application.

The report uses:
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        QuizMaster app = new QuizMaster();
        app.setVisible(true);
    });
}


Let's interpret this step by step.
1. The Java runtime begins execution at main().
2. SwingUtilities.invokeLater() schedules the GUI initialization on Swing's event-dispatch thread.
3. The program attempts to set the cross-platform Swing look and feel.
4. It creates a new QuizMaster instance.
5. setVisible(true) makes the application window visible.
Swing GUI construction and event handling are normally managed on the event-dispatch thread. This helps the user interface respond in an orderly manner to timer events and button actions.

The empty catch block means that if changing the look and feel fails, the exception is ignored and the program continues to create the application using the available look and feel.

The main() method is therefore responsible for starting the GUI, not for conducting the quiz itself. The quiz methods handle the actual game logic.

Part 3 — Screenshots, results, and conclusion
Page 13 of the report — Compilation and application screenshots
This page begins Section 5, titled Screenshots of Input and Output.

Its purpose is to demonstrate what happens when the program is executed, rather than merely showing the source code.
Figure 5.1 — Compilation and execution
The report shows the two commands:
javac QuizMaster.java
java QuizMaster


The first compiles the source code, and the second launches the application.

These commands form the basic execution procedure for the project.
Figure 5.2 — Welcome screen
PDF page 14 · Figure 5.2







dev.to


The welcome screen is the starting point of the application.

It contains the Quiz Master title, an introduction, a dropdown to select the category, a short description of the 10-question and 15-second format, and the START QUIZ button.

The category dropdown matters because the questions shown next depend on the choice made here.

What this screenshot proves: The user-facing interface has been built and the quiz can be started from a GUI.
Figure 5.3 — Quiz screen
PDF page 14 · Figure 5.3







blog.csdn.net


The quiz screen displays a sample Java question, four answer buttons, a timer, question progress, and the current score.

For the example question about Java inheritance, the answer extends is correct.

When the user clicks an answer, the application checks the selected option's index against the correct answer index in the Question object.

What this screenshot proves: The program displays questions and options, tracks time and score, and provides an interface for submitting answers.
Figure 5.4 — Answer feedback
PDF page 15 · Figure 5.4







stackoverflow.com


This screenshot shows the interface after a correct answer.

The correct button is green, the score has updated, the other buttons are locked, and feedback appears beneath the options.

The NEXT QUESTION button allows the user to continue.

This visual behavior is implemented mainly by selectAnswer().
Figure 5.5 — Result screen
PDF page 15 · Figure 5.5







dev.to


The result screenshot shows an example of a perfect score: 10 out of 10, or 100%.

It also displays the performance message and PLAY AGAIN button.

This screenshot is an example output from a successful attempt, not a promise that every user will score 10 out of 10.

What this screenshot proves: The application can finish a quiz, calculate a percentage, present a final summary, and offer replay.

Page 16 of the PDF — Conclusion and future scope
This is the final physical page and the last numbered report page.
6.1 Conclusion
The report concludes that Quiz Master demonstrates Java Swing and AWT through an interactive desktop application. It brings together GUI development, event handling, question data, countdown timing, answer validation, score calculation, and results presentation. FSJP_Quiz_Master_Mini_Project_Report final.pdf


In everyday language, the project demonstrates how separate Java concepts can work together to build a complete small application.

Instead of writing isolated programs to practise individual concepts, you have combined them into one working application.
6.2 Future Enhancements
The report proposes several ways to develop the system further.
Enhancement	What it would add
Database integration	Store questions and previous scores using MySQL or SQLite with JDBC.
Student login	Give each student an account and maintain individual results.
Instructor interface	Let an instructor add, edit, or delete questions without editing source code.
More categories and difficulty levels	Expand the question bank and allow easier or harder quizzes.
Randomization	Shuffle question order or answer choices.
Review page	Show the student's chosen answer and the correct answer for each question.
Sound and accessibility	Add audio feedback, accessibility options, and theme selection.
Web version	Expand the application with a Java backend and browser-based frontend.

These are proposed enhancements, not features already implemented in the current program. FSJP_Quiz_Master_Mini_Project_Report final.pdf


For instance, the current program has a built-in question list. Database integration would allow questions to be stored and retrieved from a database instead of existing only inside the source code.

Similarly, adding a student login would require user identity management, and a web version would require a web architecture that the current Swing application does not have.

Part 4 — How all the Java concepts connect
By now, you have read the report from beginning to end. Let's connect its main programming concepts so you can understand the system as a whole.
The application's internal working
1. Question bank

buildQuestionBank() creates and stores the questions as Question objects.


2. Quiz selection

startQuiz() selects 10 questions and resets the score and progress.


3. Display and countdown

showQuestion() displays one question; the Swing Timer triggers tick().


Correct answer

Increase the score by one and highlight the correct option.


Wrong or late answer

Do not add a mark; lock the question and display feedback.




4. Results

finishQuiz() calculates the percentage, displays the performance message, and offers replay.


This is the central programming idea: the data, the interface, and the event-handling logic work together.
- The Question class stores the quiz data.
- Swing components display the data and accept user input.
- Event listeners respond to clicks.
- The timer handles time limits.
- Methods manage the quiz state and navigate between questions.

Part 5 — Important concepts you should be able to explain
These are the concepts most directly connected to the source code.
Concept	Easy explanation	Where it is used
Class	A blueprint that defines data and behavior.	QuizMaster, Question
Object	An instance of a class.	The application and individual question objects
Inheritance	A class acquires properties and behavior from another class.	QuizMaster extends JFrame
Encapsulation	Group related data and behavior together and control access to fields.	Question and the private fields in QuizMaster
Array	Stores a fixed number of elements.	Four answer buttons and the options array
ArrayList	Stores a collection that can grow or shrink.	Question lists
List	Defines operations for an ordered collection.	List<Question>
Event handling	Responds to user or timer events.	Button listeners and countdown callbacks
ActionListener	Receives action events such as button clicks.	Answer buttons and navigation controls
Swing Timer	Triggers actions at a specified interval.	The 15-second countdown
Conditional statements	Choose what to do based on a condition.	Correctness checks, timeouts, and score messages
CardLayout	Switches between screen panels in one container.	Welcome, quiz, and result screens
Exception handling	Deals with conditions that may cause an exception.	The look-and-feel setup in main()
Helper methods	Reusable methods that simplify repeated tasks.	Label and button styling methods

There is one important distinction worth remembering: the application uses a timer, but it does not use a continuously running animation loop like the 2D car racing game in your reference PDF. Here, the timer triggers the countdown once every second.
A small example: how score calculation works
Suppose the correct answer is stored at index 2, and the user clicks the third answer button.

The clicked button passes the value 2 to selectAnswer(2).

The method compares:
boolean correct =
    choice == q.answerIndex;


Because 2 == 2, the result is true.

The program then executes:
if (correct) score++;


If the original score was 4, it becomes 5.

The next question does not inherit that answer's correctness. It is evaluated independently using its own Question object and correct answer index.

This is an example of using conditional logic and object data together.

Part 6 — What to say in your viva
Here is a short explanation you can practise until you can say it without reading.
One-minute project introduction
Good morning, ma’am. My mini-project is titled Quiz Master: Interactive Quiz Application using Java Swing.

The objective of this project is to develop a graphical quiz application in which users can select a category and answer multiple-choice questions within a time limit.

The application is developed using Java Swing and AWT. It uses GUI components such as JFrame, JPanel, JLabel, JButton, JComboBox, and JProgressBar. The questions are represented using a custom Question class, and a Swing Timer handles the 15-second countdown.

The system provides three categories, ten questions per attempt, instant answer feedback, score calculation, a final result screen, and a Play Again option. It uses standard Java libraries and does not require an external database in its current version.

Through this project, I have applied object-oriented programming, event handling, conditional statements, collections, timers, and graphical user interface development. Future enhancements can include database integration, student accounts, and a web-based version.
