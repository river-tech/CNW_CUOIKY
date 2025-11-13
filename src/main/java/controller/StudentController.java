package controller;

import model.bean.StudentBean;
import model.bo.StudentBO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentController extends HttpServlet {
    private final StudentBO studentBO = new StudentBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String faculty = request.getParameter("faculty");
        List<StudentBean> students;
        if ((keyword != null && !keyword.isEmpty()) || (faculty != null && !faculty.isEmpty())) {
            students = studentBO.search(keyword, faculty);
        } else {
            students = studentBO.listAll();
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("views/students.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            StudentBean student = new StudentBean();
            student.setMasv(request.getParameter("masv"));
            student.setHoten(request.getParameter("hoten"));
            student.setGioitinh(request.getParameter("gioitinh"));
            student.setKhoa(request.getParameter("khoa"));
            if (studentBO.existsByMasv(student.getMasv())) {
                request.setAttribute("error", "Mã sinh viên đã tồn tại.");
                request.setAttribute("studentForm", student);
                request.getRequestDispatcher("views/add.jsp").forward(request, response);
                return;
            }
            boolean created = studentBO.add(student);
            if (!created) {
                request.setAttribute("error", "Không thể tạo sinh viên. Thử lại sau.");
                request.setAttribute("studentForm", student);
                request.getRequestDispatcher("views/add.jsp").forward(request, response);
                return;
            }
        } else if ("update".equals(action)) {
            StudentBean student = new StudentBean();
            student.setId(Integer.parseInt(request.getParameter("id")));
            student.setMasv(request.getParameter("masv"));
            student.setHoten(request.getParameter("hoten"));
            student.setGioitinh(request.getParameter("gioitinh"));
            student.setKhoa(request.getParameter("khoa"));
            studentBO.update(student);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            studentBO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/students");
    }
}
