package controller;

import model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Quiz quiz = new Quiz();
        if(session.getAttribute("quiz") == null) {
            session.setAttribute("quiz", quiz);
        } else {
            // Reset quiz when click from index.html
            String isNew = req.getParameter("is_new");
            if (isNew != null && isNew.trim() != "") {
                session.setAttribute("quiz", quiz);
            } else {
                quiz = ((Quiz) session.getAttribute("quiz"));
                if(req.getParameter("answer") != null && req.getParameter("answer").trim() != "") {
                    String question = req.getParameter("question");
                    Integer answer = Integer.parseInt(req.getParameter("answer"));
                    quiz.setAnswerResult(question, answer);
                }
            }
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>The Number Quiz</h1>");
        out.println("<form action'/quiz' method='get'>");
        out.println("<p>Your current score is " + quiz.getScores() + "</p>");
        if(quiz.getAnswerTimes() < 5) {
            out.println("<p>Guess the next number in the sequence.</p>");
            Random random = new Random();
            int index = random.nextInt(quiz.getQuestions().length);
            String randomQuestion = quiz.getQuestions()[index];
            out.println("<p>" + randomQuestion +"</p>");
            out.println("<p><input type='hidden' name='question' readonly value='" + randomQuestion +"' /></p>");
            out.println("<p>Your answer: <input type='number' required name='answer' /></p>");
            out.println("<p><input type='submit' /></p>");
        } else {
            out.print("<p>You have completed the Number Quiz, with a score of " + quiz.getScores() + " out of 5.</p>");
            out.print("<p><a href='quiz?is_new=true'>Play again?</a></p>");
        }
        out.println("</form>");
    }
}
